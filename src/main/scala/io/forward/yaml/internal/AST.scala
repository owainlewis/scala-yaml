package io.forward.yaml.internal

import scalaz.Scalaz._
import scalaz._

abstract class AST

case class YString(value: String) extends AST

case class YBoolean(value: Boolean) extends AST

case class YSeq(value: Vector[AST]) extends AST

case object YNull extends AST

object AST {

  //implicit val astFunctor = new Functor[AST] {
  //  override def map[A, B](fa: AST[A])(f: (A) => B): AST[B] = ???
  //}
}


