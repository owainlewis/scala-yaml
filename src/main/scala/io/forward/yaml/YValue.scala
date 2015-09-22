package io.forward.yaml

import scala.collection.JavaConversions._

abstract class YValue {

  /** Arrow traverse */
  def >>>(v: String): Option[YValue]

  def isNull: Boolean =
    this == YNull

  def name: String =
    this match {
      case YNull       => "Null"
      case YBoolean(_) => "Boolean"
      case YNumber(_)  => "Number"
      case YSet(_) => "Set"
      case YString(_)  => "String"
      case YObj(_)     => "Object"
      case YSeq(_)     => "Seq"
    }

  def asSnakeYAML: Object
}

case class YObj(value: Map[String, YValue]) extends YValue {

  def asSnakeYAML = null // TODO

  def >>>(v: String): Option[YValue] = value.get(v)
}

case class YNumber[A](value: A)(val num: Numeric[A]) extends YValue {

  def asSnakeYAML = null // TODO

  def >>>(v: String) = None
}

case class YString(value: String) extends YValue {
  def asSnakeYAML = null

  def >>>(v: String) = None
}

sealed abstract class YBoolean extends YValue {
  def value: Boolean

  def asSnakeYAML = null

  def >>>(v: String) = None
}

object YBoolean extends YValue {
  def apply(x: Boolean): YBoolean = if (x) YTrue else YFalse
  def unapply(x: YBoolean): Option[Boolean] = Some(x.value)

  def asSnakeYAML = this

  def >>>(v: String) = None
}

case object YTrue  extends YBoolean  { def value = true  }

case object YFalse extends YBoolean  { def value = false }

case class YSeq(elements: Vector[YValue]) extends YValue {

  def map[B](f: YValue => B) = this.elements map f

  def isEmpty: Boolean = this.elements.isEmpty

  def asSnakeYAML = seqAsJavaList(elements.map(_.asSnakeYAML))

  def >>>(v: String) = None
}

object YSeq{
  def apply(elements: YValue*) = new YSeq(elements.toVector)
}

case class YSet(elements: Set[YValue]) extends YValue {

  def map[B](f: YValue => B) = this.elements map f

  def isEmpty: Boolean = this.elements.isEmpty

  def asSnakeYAML = setAsJavaSet(elements.map(_.asSnakeYAML))

  def >>>(v: String) = None
}

object YSet {
  def apply(elements: YValue*) =
    new YSet(elements.toSet)
}

case object YNull extends YValue {
  def asSnakeYAML = null

  def >>>(v: String) = None
}
