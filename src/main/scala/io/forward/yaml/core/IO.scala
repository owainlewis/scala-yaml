package io.forward.yaml.core

import scala.util.Try

object IO {

  def fromFile(filePath: String): Try[Yaml] = {
    val contents = Try {
      scala.io.Source.fromFile(filePath).getLines().mkString
    }
    contents.map(Parser.parseAsYAML)
  }
}
