package io.forward.yaml

trait YamlReader[A] {
  def read(yaml: YValue): A
}

object YamlReader {
  implicit def func2Reader[A](f: YValue => A): YamlReader[A] =
    new YamlReader[A] {
      def read(yaml: YValue) = f(yaml)
    }
}

trait YamlWriter[A] {
  def write(obj: A): YValue
}

object YamlWriter {
  implicit def func2Writer[A](f: A => YValue): YamlWriter[A] =
    new YamlWriter[A] {
      def write(obj: A) = f(obj)
    }
}

trait YamlFormat[A] extends YamlReader[A] with YamlWriter[A]
