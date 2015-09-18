package io.forward.yaml

abstract class YValue {
  /**
   * Return `true` if this YAML value is `null`, otherwise, `false`.
   */
  def isNull: Boolean =
    this == YNull

  /**
   * The name of the type of the YAML value.
   */
  def name: String =
    this match {
      case YNull       => "Null"
      case YBoolean(_) => "Boolean"
      case YString(_)  => "String"
      case YObj(_)     => "Object"
      case YSeq(_)     => "Seq"
    }
}

/**
 * Object
 *
 * @param value a map of string -> y value
 */
case class YObj(value: Map[String, YValue]) extends YValue

/**
 * Strings
 *
 * @param value A string value
 */
case class YString(value: String) extends YValue

/**
 * Booleans
 *
 */
sealed abstract class YBoolean extends YValue {
  def value: Boolean
}

object YBoolean {
  def apply(x: Boolean): YBoolean = if (x) YTrue else YFalse
  def unapply(x: YBoolean): Option[Boolean] = Some(x.value)
}

case object YTrue  extends YBoolean  { def value = true  }

case object YFalse extends YBoolean  { def value = false }

/**
 * Sequence
 *
 * @param value A vector of y values
 */
case class YSeq(value: Vector[YValue]) extends YValue {
  def map[B](f: YValue => B) = this.value map f

  def isEmpty: Boolean = this.value.isEmpty
}

/**
 * Null
 *
 */
case object YNull extends YValue



