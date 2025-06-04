package com.dev.simulation.state

case class JunctionTrafficLights(
                                  northRoad: RoadTrafficLights,
                                  eastRoad: RoadTrafficLights,
                                  southRoad: RoadTrafficLights,
                                  westRoad: RoadTrafficLights):
  
  def next(): JunctionTrafficLights = JunctionTrafficLights(northRoad.next(), eastRoad.next(), southRoad.next(), westRoad.next())
  
  def roads(): List[RoadTrafficLights] = List(northRoad, eastRoad, southRoad, westRoad)
