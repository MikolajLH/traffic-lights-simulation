package com.dev.simulation.junction

case class Crossing() extends RoadElement:
  def precondition(elements: Seq[RoadElement]): Either[String, Unit] = Right(())
  def postcondition(elements: Seq[RoadElement]): Either[String, Unit] = Right(())
