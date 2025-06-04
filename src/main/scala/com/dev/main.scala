package com.dev

import doodle.core.*
import doodle.java2d.*
import doodle.image.*
import doodle.image.syntax.all.*
import doodle.core.*
import doodle.syntax.all.*
import doodle.image.*
import cats.effect.unsafe.implicits.global
import com.dev.cli.{InputFile, OutputFile}
import com.dev.simulation.utility.Direction.{E, N, S, W}
import com.dev.simulation.utility.LaneDirection.{forward, left, right}
import com.dev.simulation.utility.Light.green
import com.dev.simulation.mutable.{Junction, Lane, Road, Simulation}
import com.dev.simulation.solve.{CliquesSolver, VertexIndex}
import com.dev.simulation.utility.TrafficLight

import scala.collection.immutable.Queue
import scala.collection.mutable



@main def main(inputFilePath: String, outputFilePath: String): Unit = {
  println(inputFilePath)


  val road1 = new Road(mutable.ArrayDeque(
    Lane(Set(TrafficLight(Set(left), green))),
    Lane(Set(TrafficLight(Set(forward), green))),
    Lane(Set(TrafficLight(Set(right), green)))))

  val road2 = new Road(mutable.ArrayDeque(
    Lane(Set(TrafficLight(Set(left), green))),
    Lane(Set(TrafficLight(Set(forward), green))),
    Lane(Set(TrafficLight(Set(right), green)))))

  val road3 = new Road(mutable.ArrayDeque(
    Lane(Set(TrafficLight(Set(left), green))),
    Lane(Set(TrafficLight(Set(forward), green))),
    Lane(Set(TrafficLight(Set(right), green)))))

  val road4 = new Road(mutable.ArrayDeque(
    Lane(Set(TrafficLight(Set(left), green))),
    Lane(Set(TrafficLight(Set(forward), green))),
    Lane(Set(TrafficLight(Set(right), green)))))

  val cliques = List(
      Set(0, 1, 2, 5, 8, 11),
      Set(0, 2, 4, 5, 8, 11),
      Set(0, 2, 5, 6, 8, 11),
      Set(1, 2, 5, 7, 8, 11),
      Set(1, 2, 5, 8, 9, 11),
      Set(2, 3, 4, 5, 8, 11),
      Set(2, 3, 5, 7, 8, 11),
      Set(2, 3, 5, 8, 9, 11),
      Set(2, 5, 6, 7, 8, 11),
      Set(2, 4, 5, 8, 10, 11),
      Set(2, 5, 6, 8, 10, 11),
      Set(2, 5, 8, 9, 10, 11))

  val junc: Junction = new Junction(road1, road2, road3, road4)

  val cs: List[Set[VertexIndex]] = cliques.map(s => s.flatMap(junc.getVertexIndexFromInt))
  cs.foreach(println(_))

  val sim = new Simulation(junc, CliquesSolver(cs))

  for
    commands <- InputFile.parse(inputFilePath)
  yield {
    val cmds = commands.flatten
    sim.junction.getAllVertices.foreach(println(_))

    cmds.foreach(_.executeOn(sim))
    sim.show()
    sim.left.foreach(println(_))
    sim.logs.foreach(println(_))
    sim.show()

    OutputFile.save(outputFilePath, sim.left.reverse)
  }
}
