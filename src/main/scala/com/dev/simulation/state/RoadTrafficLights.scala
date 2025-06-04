package com.dev.simulation.state

case class RoadTrafficLights(lanes: List[LaneTrafficLights]):
  def next(): RoadTrafficLights = RoadTrafficLights(lanes.map(_.next()))