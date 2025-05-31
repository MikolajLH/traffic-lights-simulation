package com.dev.simulation.junction

trait RoadElement:
  def precondition(elems: Seq[RoadElement]): Either[String, Unit]
  def postcondition(elems: Seq[RoadElement]): Either[String, Unit]