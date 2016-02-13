package io.forward.yaml.internal

import io.forward.yaml._
import org.yaml.snakeyaml._
import scala.collection.JavaConverters._
import spray.json._

object Parser {
  implicit class PimpedString(val string: String) extends AnyVal {
    def parseYaml: YValue = parseAsYAML(string)
  }

  val parseAsYAML: (String) => YValue  = load _ andThen asYAML

  val parseAsJSON: (String) => JsValue = load _ andThen asJValue

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

  /**
   * Map to Spray JSON internal AST
   *
   * @param obj A Snake YAML Java object
   */
  def asJValue(obj: Object): JsValue = obj match {
    case x: java.util.Map[Object @unchecked, Object @unchecked] =>
      JsObject(x.asScala.map {  case (k, v) => k.toString -> asJValue(v) }.toMap)
    case x: java.util.List[Object @unchecked] =>
      JsArray(x.asScala.map(asJValue).toVector)
    case x: java.util.Set[Object @unchecked] =>
      JsArray(x.asScala.map(asJValue).toVector)
    case i: java.lang.Integer =>
      JsNumber(BigDecimal(i))
    case i: java.lang.Long =>
      JsNumber(BigDecimal(i))
    case i: java.math.BigInteger =>
      JsNumber(BigDecimal(i))
    case i: java.lang.Double =>
      JsNumber(BigDecimal(i))
    case s: java.lang.String =>
      JsString(s)
    case d: java.util.Date =>
      JsString(d.toString)
    case b: java.lang.Boolean =>
      JsBoolean(b)
    case _ => JsNull
  }

  def load(input: String): Object = new Yaml().load(input)
}
