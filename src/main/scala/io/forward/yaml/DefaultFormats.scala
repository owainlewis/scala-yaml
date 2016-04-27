package io.forward.yaml

trait DefaultFormats {

  implicit object IntYamlFormat extends YamlFormat[Int] {
    def write(x: Int) = YInt(x)
    def read(value: YValue) = value match {
      case YInt(x) => x
      case x => deserializeException(s"""Expected Int as YInt, but got $x""")
    }
  }
}
