# Scala-yaml (WIP)

A YAML library for Scala

## Motivation

There isn't much support for parsing YAML in Scala. This library aims to give a nice simple DSL for working with and
traversing YAML in Scala. Interally it's based on the snake YAML Java library.

## Use

Parse a yaml string

```scala

scala> import io.forward.yaml.Parser
scala> Parser.parseAsYAML("['foo', 'bar', 'baz']")

// io.forward.yaml.YValue = YSeq(Vector(YString(foo), YString(bar), YString(baz)))

```
