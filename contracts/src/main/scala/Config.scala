import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.language.postfixOps

object Config {
  val pair = "ethbtc"
  val kafkaUrl: String = System.getenv("KAFKA_URL")
  val bufferTime: FiniteDuration = 1 seconds
  val period: String = bufferTime.toString.split(" ").mkString("-")
  val topicName: String = List(period, pair).mkString("-")
}
