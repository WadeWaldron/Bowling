import sbt._
import sbt.Keys._

object Projects extends Build {
  val scala = "2.10.2"

  val root = Project("Bowling",file("."), settings = Defaults.defaultSettings ++ Seq(
	scalaVersion := scala,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.0.M5b" % "test",
      "org.mockito"  % "mockito-all" % "1.9.5" % "test"
    )))
}