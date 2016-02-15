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
res0: io.forward.yaml.YValue = YString(["foo", "bar"])
```
