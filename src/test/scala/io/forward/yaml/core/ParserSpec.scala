package io.forward.yaml.core

import org.scalatest._

import scala.io.Source

class ParserSpec extends WordSpec with Matchers {

  val loadFixture = (name: String) =>
    Source.fromFile(s"src/test/scala/resources/$name").mkString

  val parseYAML = (contents: String) =>
    Parser.parseAsYAML(contents)

  val loadAsYAML = parseYAML compose loadFixture

  "The Parser" should {

    "parse a YAML string" in {
      val actual = Parser.asYAML(Parser.load("['Apple', 'Orange', 'Strawberry', 'Mango']"))
      val expected = YSeq(Vector(YString("Apple"), YString("Orange"), YString("Strawberry"), YString("Mango")))
      actual shouldBe expected
    }

    "parse a YAML string using the parseAsYAML method" in {
      val actual = Parser.parseAsYAML("['Apple', 'Orange', 'Strawberry', 'Mango']")
      val expected = YSeq(Vector(YString("Apple"), YString("Orange"), YString("Strawberry"), YString("Mango")))
      actual shouldBe expected
    }

    "read a simple YAML file"in {
      val yaml = loadAsYAML("A.yaml")
      yaml shouldBe YSeq(Vector(YString("Casablanca"), YString("North by Northwest"), YString("The Man Who Wasn't There")))
    }

    "map from a YAML AST to a Snake YAML AST" in {
      val yamlString = loadFixture("A.yaml")
      val yValue = Parser.parseAsYAML(yamlString)
    }
  }
}
