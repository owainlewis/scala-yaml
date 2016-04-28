package io.forward.yaml.core

trait YamlReader[A] {

  def load(yaml: Yaml): A
}

trait YamlWriter[A] {

  def dump(obj: A): Yaml
}

trait YamlFormat[A] extends YamlReader[A] with YamlWriter[A]
