
import cats.effect.{IO, IOApp, Resource}
import fs2.kafka._
import io.circe.generic.auto._
import io.circe.syntax._
import io.github.paoloboni.binance.BinanceClient
import io.github.paoloboni.binance.common.SpotConfig
import io.github.paoloboni.binance.common.response.TradeStream
import io.github.paoloboni.binance.spot.SpotApi
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.mutable
import scala.language.postfixOps

object Producer extends IOApp.Simple {
  private val logger: Logger = LoggerFactory.getLogger(getClass)
  private val tradesBuffer: mutable.Map[Long, TradeStream] = mutable.Map()
  private var lastClose: BigDecimal = BigDecimal(0)

  private val producerSettings: ProducerSettings[IO, String, String] = ProducerSettings(
    keySerializer = Serializer[IO, String],
    valueSerializer = Serializer[IO, String]
  ).withBootstrapServers(Config.kafkaUrl)

  private val binanceClient: Resource[IO, SpotApi[IO]] = BinanceClient
    .createSpotClient[IO](SpotConfig.Default(
      apiKey = "***",
      apiSecret = "***"
    ))

  private val binanceResourceStream: fs2.Stream[IO, SpotApi[IO]] = fs2.Stream.resource(binanceClient)

  private val sourceStream$: fs2.Stream[IO, TradeStream] = binanceResourceStream
    .flatMap(spotApi => spotApi.tradeStreams(Config.pair))
    .evalTap((tradeEvent: TradeStream) => IO(tradesBuffer.update(tradeEvent.T, tradeEvent)))

  private val targetStream$: fs2.Stream[IO, IO[ProducerResult[Int, String, String]]] = KafkaProducer.stream(producerSettings)
    .switchMap(producer => fs2.Stream
      .awakeEvery[IO](Config.bufferTime)
      .flatMap(_ => fs2.Stream(tradesBuffer.toArray.map(e => e._2)))
      .evalTap(elapsed => IO(tradesBuffer.clear()))
      .map { bufferedTrades: Array[TradeStream] =>
        val candleStickEndTime: Long = System.currentTimeMillis
        val candleStick: SimpleCandleStick = SimpleCandleStick.fromArray(bufferedTrades, candleStickEndTime, lastClose)
        val key = s"${Config.pair}-$candleStickEndTime"
        lastClose = candleStick.close
        val record = ProducerRecord(Config.topicName, key, candleStick.asJson.noSpaces)
        logger.info(s"topic: ${Config.topicName}, $key, ${bufferedTrades.length} trades aggregated")
        record
      }
      .map(record => ProducerRecords.one(record, 0))
      .evalMap(producer.produce)
    )

  val run: IO[Unit] = sourceStream$.merge(targetStream$).compile.drain

}
