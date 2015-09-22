package io.forward.yaml.internal

import org.scalatest._
import spray.json.JsObject

import scala.io.Source

class ParserSpec extends WordSpec with Matchers {

  "The Parser" should {

    "read a simple YAML file"in {

      val contents = Source.fromFile("src/test/scala/resources/A.yaml").getLines().mkString("\n")

      val yaml = Parser.parseAsYAML(contents)

      println(yaml)
    }
  }
}
