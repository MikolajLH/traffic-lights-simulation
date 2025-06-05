package com.dev.simulation.deprecated

trait RoadElement:
  def precondition(elements: Seq[RoadElement]): Either[String, Unit] = Right(())
  def postcondition(elements: Seq[RoadElement]): Either[String, Unit] = Right(())