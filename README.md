# Scala-yaml (WIP)

A YAML library for Scala

![](https://api.travis-ci.org/owainlewis/scala-yaml.svg)

## Motivation

There isn't much support for parsing YAML in Scala. This library aims to give a nice simple DSL for working with and
traversing YAML in Scala. Interally it's based on the snake YAML Java library.

## Use

Parse a yaml string

```scala
scala> import io.forward.yaml._
import io.forward.yaml._

scala> """["foo", "bar"]""".parseYAML
res0: io.forward.yaml.YValue = YSeq(["foo", "bar"])
```

Parsing Case Classes

Still work to do in this area but it works

```scala

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

```


