package io.forward.yaml

import org.yaml.snakeyaml._
import spray.json._

import scala.collection.JavaConverters._

object Parser {
  def parseAsYAML(yaml: String): YValue = asYAML(load(yaml))

  def asYAML(obj: Object): YValue = obj match {
    case x: java.util.Map[Object @unchecked, Object @unchecked] =>
      YObj(x.asScala.toMap map { case (k, v) => (k.toString, asYAML(v)) })
    case x: java.util.List[Object @unchecked] =>
      YSeq(x.asScala.map(asYAML).toVector)
    case x: java.util.Set[Object @unchecked] =>
      YSet(x.asScala.map(asYAML).toSet)
    case i: java.lang.Integer =>
      YInt(i)
    case i: java.lang.Long =>
      YLong(i)
    case i: java.math.BigInteger =>
      YBigInt(i)
    case i: java.lang.Double =>
      YDouble(i)
    case s: java.lang.String =>
      YString(s)
    case d: java.util.Date =>
      YDate(d)
    case b: java.lang.Boolean =>
      YBoolean(b)
    case _ =>
      YNull
  }

  def load(input: String): Object = new Yaml().load(input)
}
