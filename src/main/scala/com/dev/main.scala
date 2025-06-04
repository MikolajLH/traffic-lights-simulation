package com.dev

import doodle.core.*
import doodle.java2d.*
import doodle.image.*
import doodle.image.syntax.all.*
import doodle.core.*
import doodle.syntax.all.*
import doodle.image.*
import cats.effect.unsafe.implicits.global
import com.dev.cli.InputFile
import com.dev.simulation.Direction.{E, N, S, W}
import com.dev.simulation.LaneDirection.{forward, left, right}
import com.dev.simulation.Light.green
import com.dev.simulation.TrafficLight
import com.dev.simulation.mutable.{Junction, Lane, Road, Simulation}

import scala.collection.immutable.Queue
import scala.collection.mutable



@main def main(inputFilePath: String): Unit = {
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

  val sim = new Simulation(new Junction(road1, road2, road3, road4))

  for
    commands <- InputFile.parse(inputFilePath)
  yield {
    val cmds = commands.flatten
    cmds.foreach(_.executeOn(sim))
    sim.show()
    println("XXX")
    sim.left.foreach(println(_))
    println("XXX")
    sim.logs.foreach(println(_))
    sim.show()
  }
}
