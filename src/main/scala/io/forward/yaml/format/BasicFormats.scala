package io.forward.yaml.format

import java.util.Date

import io.forward.yaml._
import io.forward.yaml.core._

trait BasicFormats {

  implicit object BigIntYamlFormat extends YamlFormat[BigInt] {
    def dump(obj: BigInt): Yaml = YBigInt(obj)
    def load(yaml: Yaml): BigInt = yaml match {
      case YBigInt(value) => identity(value)
      case x => deserializeException(s"Expected BigInt but got $x")
    }
  }

  implicit object BooleanYamlFormat extends YamlFormat[Boolean] {
    def dump(obj: Boolean): Yaml = YBoolean(obj)
    def load(yaml: Yaml): Boolean = yaml match {
      case YBoolean(value) => identity(value)
      case x => deserializeException(s"Expected Boolean but got $x")
    }
  }

  implicit object DateYamlFormat extends YamlFormat[java.util.Date] {
    def dump(obj: Date): Yaml = ???
    def load(yaml: Yaml): Date = ???
  }

  implicit object DoubleYamlFormat extends YamlFormat[Double] {
    def dump(obj: Double): Yaml = YDouble(obj)
    def load(yaml: Yaml): Double = yaml match {
      case YDouble(value) => identity(value)
      case x => deserializeException("Expected Double but got $x")
    }
  }

  implicit object IntYamlFormat extends YamlFormat[Int] {
    def dump(obj: Int) = YInt(obj)
    def load(yaml: Yaml) = yaml match {
      case YInt(value) => identity(value)
      case x => deserializeException(s"Expected Int but got $x")
    }
  }

  implicit object LongYamlFormat extends YamlFormat[Long] {
    def dump(obj: Long): Yaml = YLong(obj)

    def load(yaml: Yaml): Long = yaml match {
      case YLong(value) => identity(value)
      case x => deserializeException(s"Expected Long but got $x")
    }
  }

  implicit object StringYamlFormat extends YamlFormat[String] {
    def dump(obj: String): Yaml = YString(obj)
    def load(yaml: Yaml): String =  yaml match {
      case YString(value) => identity(value)
      case x => deserializeException(s"Expected String but got $x")
    }
  }
}

object BasicFormats extends BasicFormats

object Example {

  case class Person(name: String, age: Int)

  object Person extends BasicFormats {

    implicit val format = new YamlFormat[Person] {

      def dump(obj: Person): Yaml = YObj(Map("name" -> YString(obj.name), "age" -> YInt(obj.age)))

      def load(yaml: Yaml): Person = yaml match {
        case YObj(fields) =>
          val p = for {
            name <- fields.get("name")
            age <- fields.get("age")
          } yield (name, age)
          p match {
            case Some((YString(name), YInt(age))) => Person(name, age)
            case _ => deserializeException("Could not derive person")
          }
        case _ => deserializeException("Could not derive person")
      }
    }

    def fromYaml(input: String) = {
      implicitly[YamlFormat[Person]].load(Parser.parseAsYAML(input))
    }
  }
}
