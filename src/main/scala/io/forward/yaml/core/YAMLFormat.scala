package io.forward.yaml.core

trait YamlReader[A] {

  def readYaml(yaml: Yaml): A
}

trait YamlWriter[A] {

  def writeYaml(obj: A): Yaml
}

trait YamlFormat[A] extends YamlReader[A] with YamlWriter[A]
