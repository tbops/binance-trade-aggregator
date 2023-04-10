import fs2.Chunk
import io.github.paoloboni.binance.common.response.TradeStream

case class SimpleCandleStick(
                              open: BigDecimal,
                              high: BigDecimal,
                              low: BigDecimal,
                              close: BigDecimal,
                              endTime: Long,
                            ) extends Candlestick {
}

object SimpleCandleStick {

  def fromChunks(chunks: Chunk[TradeStream]): (SimpleCandleStick, Int) = {
    val ps = chunks.map(c => c.p).toArray
    val endTime = chunks.last.get.T
    val open = chunks.head.get.p
    val close = chunks.last.get.p
    val high = ps.max
    val low = ps.min
    (SimpleCandleStick(open, high, low, close, endTime), ps.length)
  }

  def fromArray(array: Array[TradeStream], candleStickEndTime: Long, lastClose: BigDecimal): SimpleCandleStick = {
    if (array.length < 1)
      SimpleCandleStick(lastClose, lastClose, lastClose, lastClose, candleStickEndTime)
    else {
      val ps = array.map(c => c.p)
      val endTime = candleStickEndTime
      val open = array.head.p
      val close = array.last.p
      val high = ps.max
      val low = ps.min
      SimpleCandleStick(open, high, low, close, endTime)
    }
  }

}

