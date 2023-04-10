case class ExtendedCandleStick(
                                open: BigDecimal,
                                high: BigDecimal,
                                low: BigDecimal,
                                close: BigDecimal,
                                endTime: Long,
                                indicators: Array[Indicator[BigDecimal]]
                              ) extends CandleStickWithIndicators {
}

object ExtendedCandleStick {
  def fromArrayOfSimpleCandleStick(array: Array[SimpleCandleStick]): ExtendedCandleStick = {


//    if (array.length < 1)
//      (SimpleCandleStick(lastClose, lastClose, lastClose, lastClose, candleStickEndTime), 0)
//    else {
//      val ps = array.map(c => c.p)
//      val endTime = candleStickEndTime  
//      val open = array.head.p
//      val close = array.last.p
//      val high = ps.max
//      val low = ps.min
//      (SimpleCandleStick(open, high, low, close, endTime), ps.length)
//    }
    ExtendedCandleStick(0,0,0,0,0, Array.empty)
  }
}