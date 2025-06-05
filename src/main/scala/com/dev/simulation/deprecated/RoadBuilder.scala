package com.dev.simulation.deprecated

case class RoadBuilder(incompleteRoad: List[RoadElement]):
  def push(element: RoadElement): Either[String, RoadBuilder] =
    element.precondition(incompleteRoad).map(_ => RoadBuilder(element :: incompleteRoad))

  def build: Either[List[String], Road] = Road.create(incompleteRoad)

object RoadBuilder:
  def apply(): RoadBuilder = RoadBuilder(List())
