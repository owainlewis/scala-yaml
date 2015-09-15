name := "scala-yaml"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.yaml" % "snakeyaml" % "1.16",
  "io.spray" %%  "spray-json" % "1.3.2",
  "org.scalaz" %% "scalaz-core" % "7.1.3",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)