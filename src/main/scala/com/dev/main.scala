package com.dev

import doodle.core.*
import doodle.java2d.*
import doodle.image.*
import doodle.image.syntax.all.*
import cats.effect.unsafe.implicits.global
import com.dev.cli.InputFile
import com.dev.simulation.junction.TrafficLightsDirection.{forward, left, leftArrow}
import com.dev.simulation.junction.{Crossing, Lane, RoadBuilder}
import com.dev.graphics.{Primitives, given}


@main def main(inputFilePath: String): Unit = {


  println(inputFilePath)

  val roadResult = RoadBuilder()
    .push(Lane(Set(forward)))
    .flatMap(_.push(Lane(Set(left))))
    .flatMap(_.push(Lane(Set(forward,left, leftArrow))))
    .flatMap(_.build)


  //Primitives.rightArrow(Color.red).on(Primitives.leftArrow(Color.green)).on(Primitives.forwardArrow(Color.blue)).draw()
  //Primitives.smallLeftArrow(30, Color.purple).on(Primitives.smallRightArrow(30, Color.yellow)).draw()

  val res = for
    commands <- InputFile.parse(inputFilePath)
    road <- roadResult
  yield {
    commands.flatten.foreach(cmd => cmd.introduce())
    road.toImage.transform(Transform.scale(3,3)).draw()
  }
}

