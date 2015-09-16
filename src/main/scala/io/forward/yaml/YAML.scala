package io.forward.yaml

abstract class YAML {
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
      case YNull      => "Null"
      case YBool(_)   => "Boolean"
      case YString(_) => "String"
      case YObj(_)    => "Object"
      case YSeq(_)    => "Seq"
    }
}

case class YObj(value: Map[String, YAML]) extends YAML

case class YString(value: String) extends YAML {

}

case class YBool(value: Boolean) extends YAML {

}

case class YSeq(value: Vector[YAML]) extends YAML {

  def map[B](f: YAML => B) = this.value map f

  def isEmpty: Boolean = this.value.isEmpty

}

case object YNull extends YAML



