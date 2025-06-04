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
//import com.dev.simulation.junction.TrafficLightsDirection.{forward, left, leftArrow, right}
import com.dev.simulation.junction.{Crossing, Junction, Lane, RoadBuilder}
import com.dev.graphics.{Primitives, given}
import com.dev.simulation.Direction.N
import com.dev.simulation.LaneDirection.{left, forward, right}
import com.dev.simulation.algorithms.Algorithm
import com.dev.simulation.commands.Step
import com.dev.simulation.{LaneDirection, Vehicle, TrafficLight, Light}
import com.dev.simulation.state.{JunctionVehicles, JunctionTrafficLights, LaneVehicles, RoadVehicles, SimulationState, LaneTrafficLights, RoadTrafficLights}

import scala.collection.immutable.Queue




@main def main(inputFilePath: String): Unit = {


  println(inputFilePath)

//  val roadResult = RoadBuilder()
//    .push(Crossing())
//    .flatMap(_.push(Lane(Set(left))))
//    .flatMap(_.push(Crossing()))
//    .flatMap(_.push(Lane(Set(forward,left, leftArrow))))
//    .flatMap(_.push(Crossing()))
//    .flatMap(_.build)
//
//  val simpleRoadResult = RoadBuilder()
//    .push(Lane(Set(right)))
//    .flatMap(_.push(Lane(Set(forward))))
//    .flatMap(_.push(Lane(Set(left))))
//    .flatMap(_.build)

  val rvs = RoadVehicles(List(LaneVehicles(Set(Set(left, forward, right)), Queue())))
  val jvs = JunctionVehicles(rvs, rvs, rvs, rvs)

  val rts = RoadTrafficLights(
    List(
      LaneTrafficLights(
        Set(
          TrafficLight(Set(forward, left, right), Light.green)))))

  val jts = JunctionTrafficLights(rts, rts, rts, rts)

  val simulationState = SimulationState(jvs, jts, List())

//  Primitives.rightArrow(Color.black)
//    .on(Primitives.leftArrow(Color.black))
//    .on(Primitives.forwardArrow(Color.black))
//    .on(Primitives.smallRightArrow(70, Color.black))
//    .on(Primitives.smallLeftArrow(70, Color.black)).transform(Transform.rotate(270.degrees))
//    .draw()
  //Primitives.smallLeftArrow(30, Color.purple).on(Primitives.smallRightArrow(30, Color.yellow)).draw()

  val res = for
    commands <- InputFile.parse(inputFilePath)
    //road <- roadResult
    //sr <- simpleRoadResult
    //junc = Junction(sr, sr, sr, sr)
  yield {
    println("START@@@@@@@@@@\n")
    println("print commands:")
    commands.flatten.foreach(cmd => cmd.introduce())
    println("\n")
    println("Simulation running")
    val finalState = commands.flatten.map {
      case s: Step => Algorithm()
      case other => other
    }.foldLeft(simulationState)((s, c) => c.execute(s))
    //finalState.junction.roads().foreach(println(_))
    finalState.history.reverse.foreach(println(_))
    //println(commands.flatten.foldRight(simulationState)(_.execute(_)))
    //road.toImage.transform(Transform.scale(3,3)).draw()
    //junc.toImage.transform(Transform.scale(2,2)).draw()
    //Lane(Set()).toImage.draw()
  }
}

