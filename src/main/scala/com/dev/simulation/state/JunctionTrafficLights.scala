package com.dev.simulation.state

case class JunctionTrafficLights(
                                  northRoad: RoadTrafficLights,
                                  eastRoad: RoadTrafficLights,
                                  southRoad: RoadTrafficLights,
                                  westRoad: RoadTrafficLights):
  
  def roads(): List[RoadTrafficLights] = List(northRoad, eastRoad, southRoad, westRoad)
