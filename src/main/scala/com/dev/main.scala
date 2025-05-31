package com.dev

import com.dev.cli.InputFile
import com.dev.simulation.junction.TrafficDirection.{forward, left}
import com.dev.simulation.junction.{Crossing, Lane, RoadBuilder}


@main def main(inputFilePath: String): Unit = {
  println(inputFilePath)

  val roadResult = RoadBuilder()
    .push(Crossing())
    .flatMap(_.push(Lane(Set(forward))))
    .flatMap(_.push(Lane(Set(left))))
    .flatMap(_.push(Crossing()))
    .flatMap(_.build)

  for
    commands <- InputFile.parse(inputFilePath)
    road <- roadResult
  do
    commands.flatten.foreach( cmd => cmd.introduce())
}

