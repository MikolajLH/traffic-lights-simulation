package com.dev.simulation.junction

trait RoadElement:
  def precondition(elements: Seq[RoadElement]): Either[String, Unit] = Right(())
  def postcondition(elements: Seq[RoadElement]): Either[String, Unit] = Right(())