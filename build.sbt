ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.7.0"

libraryDependencies += "com.lihaoyi" %% "upickle" % "4.2.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19"
libraryDependencies += "org.creativescala" %% "doodle" % "0.30.0"
libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.11.5-M9"

lazy val root = (project in file("."))
  .settings(
    name := "traffic-lights-simulation"
  )
