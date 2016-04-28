package io.forward.yaml.core

/**
  * A data type representing possible YAML values
  *
  * @author Owain Lewis
  */
sealed abstract class Yaml extends Product with Serializable

case class YObj(value: Map[String, Yaml]) extends Yaml

case class YInt(value: Int) extends Yaml

case class YLong(value: Long) extends Yaml

case class YBigInt(value: BigInt) extends Yaml

case class YDouble(value: Double) extends Yaml

case class YString(value: String) extends Yaml

case class YDate(value: java.util.Date) extends Yaml

case class YBoolean(value: Boolean) extends Yaml

case class YSeq[A <: Yaml](elements: Vector[A]) extends Yaml {

  def map[B <: Yaml](f: A => B): YSeq[B] = new YSeq(this.elements map f)

  def isEmpty: Boolean = this.elements.isEmpty
}

case class YSet[A <: Yaml](elements: Set[A]) extends Yaml {

  def map[B <: Yaml](f: Yaml => B): YSet[B] =
    new YSet(this.elements map f)

  def isEmpty: Boolean = this.elements.isEmpty
}

case object YNull extends Yaml