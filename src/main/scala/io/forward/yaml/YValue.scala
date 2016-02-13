package io.forward.yaml

abstract class YValue {

  def isNull: Boolean = this == YNull

  def isNumeric: Boolean = this match {
    case YInt(_) | YLong(_) | YBigInt(_) | YDouble(_) => true
    case _ => false
  }
}

case class YObj(value: Map[String, YValue]) extends YValue

case class YInt(value: Int) extends YValue

case class YLong(value: Long) extends YValue

case class YBigInt(value: BigInt) extends YValue

case class YDouble(value: Double) extends YValue

case class YString(value: String) extends YValue

case class YDate(value: java.util.Date) extends YValue

sealed abstract class YBoolean extends YValue {
  def value: Boolean
}

object YBoolean extends YValue {
  def apply(x: Boolean): YBoolean = if (x) YTrue else YFalse
  def unapply(x: YBoolean): Option[Boolean] = Some(x.value)
}

case object YTrue  extends YBoolean  { def value = true  }

case object YFalse extends YBoolean  { def value = false }

case class YSeq(elements: Vector[YValue]) extends YValue {
  def map[B](f: YValue => B) = this.elements map f
  def isEmpty: Boolean = this.elements.isEmpty
}

object YSeq{
  def apply(elements: YValue*) = new YSeq(elements.toVector)
}

case class YSet(elements: Set[YValue]) extends YValue {
  def map[B](f: YValue => B) = this.elements map f
  def isEmpty: Boolean = this.elements.isEmpty
}

object YSet {
  def apply(elements: YValue*) =
    new YSet(elements.toSet)
}

case object YNull extends YValue
