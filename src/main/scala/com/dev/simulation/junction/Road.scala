package com.dev.simulation.junction

case class Road private (elements: List[RoadElement])

object Road:
  def create(elements: List[RoadElement]): Either[List[String], Road] = {
    val errors = elements.flatMap(element => element.postcondition(elements).swap.toOption)

    if errors.isEmpty
    then Right(Road(elements))
    else Left(errors)
  }