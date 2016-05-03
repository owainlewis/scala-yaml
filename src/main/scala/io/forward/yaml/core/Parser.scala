package io.forward.yaml.core

import javax.swing.JViewport

import org.yaml.snakeyaml.{Yaml => JYaml}
import scala.collection.JavaConverters._

object Parser {

  private val jYaml = new JYaml()

  /**
    * Parse a string of YAML into a YValue
    *
    * @param yaml An input YAML string
    */
  def parseAsYAML(yaml: String): Yaml = asYAML(load(yaml))

  def asYAML(obj: Object): Yaml = obj match {
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

  def toSnakeYaml(obj: Yaml): Object = obj match {
    case YInt(value) =>
      new java.lang.Integer(value)

    case YBoolean(value) =>
      new java.lang.Boolean(value)

    case YString(value) =>
      new java.lang.String(value)

    case YLong(value)=>
      new java.lang.Long(value)

    case YDouble(value) =>
      new java.lang.Double(value)

    case YBigInt(value) => value

    case YDate(value)=> value

    case YNull => null

    case YSet(value) =>
      value.foldLeft(new java.util.HashSet[Object]()) { case (xs, x) =>
        xs.add(x)
        xs
      }
    case YObj(value) =>
      value.foldLeft(new java.util.HashMap[String, Object]()) { case (xs, (k,v)) =>
        xs.put(k, toSnakeYaml(v))
        xs
      }
    case YSeq(value) =>
      value.foldLeft(new java.util.ArrayList[Object]) { case (xs, x) =>
        xs.add(toSnakeYaml(x))
        xs
      }
  }

  def load(input: String): Object = jYaml.load(input)

  def dump(input: Yaml): String = jYaml.dump(toSnakeYaml(input))
}
