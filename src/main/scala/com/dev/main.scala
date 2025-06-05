package com.dev

import doodle.java2d.*
import doodle.image.syntax.all.*
import doodle.core.*
import doodle.image.*
import cats.effect.unsafe.implicits.global
import com.dev.cli.{InputFile, OutputFile}
import com.dev.simulation.Junctions
import com.dev.simulation.mutable.Simulation
import com.dev.simulation.solve.CliquesSolver
import com.dev.graphics.given

import com.dev.simulation.deprecated as deprecated

@main def main(inputFilePath: String, outputFilePath: String): Unit = {
  
  val (j, cs) = Junctions.x4_LiFiR
  val sim = new Simulation(j, CliquesSolver(cs))

  val simpleRoadResult = deprecated.RoadBuilder()
    .push(deprecated.Crossing())
    .flatMap(_.push(deprecated.Lane(Set(deprecated.TrafficLightsDirection.right))))
    .flatMap(_.push(deprecated.Lane(Set(deprecated.TrafficLightsDirection.forward))))
    .flatMap(_.push(deprecated.Lane(Set(deprecated.TrafficLightsDirection.left))))
    .flatMap(_.push(deprecated.Crossing()))
    .flatMap(_.build)

  for
   commands <- InputFile.parse(inputFilePath)
   sr <- simpleRoadResult
  yield {
    val cmds = commands.flatten

    for (cmd, i) <- cmds.zipWithIndex
    do {
      println(s"CMD $i")
      cmd.executeOn(sim)
    }

    //cmds.foreach(_.executeOn(sim))
    
    println("stepStatuses")
    sim.left.reverse.foreach(println(_))

    //deprecated.Junction(sr, sr, sr, sr).toImage.transform(Transform.scale(2,2)).draw()

    OutputFile.save(os.pwd / outputFilePath, sim.left.reverse.map(_.toSet))
    println(s"Saved to $outputFilePath")
  }
}
