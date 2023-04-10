trait Ohlc[T] {
  def high: T
  def low: T
  def close: T
  def open: T
}

trait Time[T] {
  def endTime: T
}

trait Indicator[T] {
  def sma: T
  def smaDeviation: T
  def squaredSmaDeviation: T
  def standardDeviation: T
  def dispersion: T
  def middleBB: T
  def lowerBB: T
  def upperBB: T
}

trait Indicators[T] {
  def indicators: Array[T]
}

trait Candlestick extends Ohlc[BigDecimal] with Time[Long]

trait CandleStickWithIndicators extends Ohlc[BigDecimal] with Indicators[Indicator[BigDecimal]] with Time[Long]


