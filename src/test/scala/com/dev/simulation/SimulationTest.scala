package com.dev.simulation

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor2}
import com.dev.cli.{InputFile, OutputFile}
import com.dev.simulation.Junctions
import com.dev.simulation.mutable.Simulation
import com.dev.simulation.solve.CliquesSolver
import upickle.default as upickle

import scala.util.{Try, Using}


class SimulationTest extends AnyFunSuite, TableDrivenPropertyChecks {
  val testCases: TableFor2[String, String] = Table(
    ("inputFilePath", "expectedOutputFilePath"),
    ("./src/test/resources/input.json", "./src/test/resources/output.json")
  )

  forAll(testCases) { (inputFile, expectedOutputFile) => runTest(inputFile, expectedOutputFile) }

  def runTest(inputFilePath: String, expectedOutputFilePath: String): Unit = {

    val outPath: os.Path = os.temp.dir() / "testout.json"

    val (j, cs) = Junctions.x4_LiFiR
    val sim = new Simulation(j, CliquesSolver(cs))

    for
      commands <- InputFile.parse(inputFilePath)
    do
      val cmds = commands.flatten
      cmds.foreach(_.executeOn(sim))
      OutputFile.save(outPath.toString, sim.left.reverse.map(_.toSet))

    val result = for
      expectedStr <- Using(scala.io.Source.fromFile(expectedOutputFilePath)) { source => source.mkString }.toEither.left.map(_.getMessage)
      expectedObj <- Try(upickle.read[OutputFile.OutputFileFormat](expectedStr)).toEither.left.map(_.getMessage)

      simulationStr <- Using(scala.io.Source.fromFile(outPath.toString)) { source => source.mkString }.toEither.left.map(_.getMessage)
      simulationObj <- Try(upickle.read[OutputFile.OutputFileFormat](simulationStr)).toEither.left.map(_.getMessage)
    yield simulationObj == expectedObj
    val errMsg = result.left.getOrElse("Empty error")


    assert(result.isRight, s"Test failed with error: $errMsg")
    assert(result.contains(true), s"output and expected output were not the same")
  }
}
