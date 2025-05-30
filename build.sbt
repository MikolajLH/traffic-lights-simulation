ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.7.0"

libraryDependencies += "com.lihaoyi" %% "upickle" % "4.2.1"

lazy val root = (project in file("."))
  .settings(
    name := "traffic-lights-simulation"
  )
