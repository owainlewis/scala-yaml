package io.forward

package object yaml {

  def deserializeException(msg: String, cause: Throwable = null) =
    throw new DeserializationException(msg, cause)

  case class DeserializationException(msg: String, cause: Throwable) extends RuntimeException(msg, cause)

  implicit def pimpString(string: String) = 
    new PimpedString(string)

  class PimpedString(string: String) {
    def parseYAML: YValue = Parser.parseAsYAML(string)
  }
}
