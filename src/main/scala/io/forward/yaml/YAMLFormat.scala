package io.forward.yaml

trait YamlReader[A] {
  def read(yaml: YValue): A
}

trait YamlWriter[A] {
  def write(obj: A): YValue
}

trait YamlFormat[A] extends YamlReader[A] with YamlWriter[A]
