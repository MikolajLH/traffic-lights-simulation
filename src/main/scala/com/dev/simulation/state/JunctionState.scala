package com.dev.simulation.state

import com.dev.simulation
import com.dev.simulation.Direction
import com.dev.simulation.Direction.{E, N, S, W}

case class JunctionState(northRoad: RoadState, eastRoad: RoadState, southRoad: RoadState, westRoad: RoadState):
  def getRoad(which: Direction): RoadState = which match {
    case N => northRoad
    case E => eastRoad
    case S => southRoad
    case W => westRoad
  }
  
  def increment(): JunctionState = JunctionState(northRoad.increment(), eastRoad.increment(), southRoad.increment(), westRoad.increment())
  
  def mapRoad(which: Direction, f: RoadState => RoadState): JunctionState =
    which match {
      case N => JunctionState(f(northRoad), eastRoad, southRoad, westRoad)
      case E => JunctionState(northRoad, f(eastRoad), southRoad, westRoad)
      case S => JunctionState(northRoad, eastRoad, f(southRoad), westRoad)
      case W => JunctionState(northRoad, eastRoad, southRoad, f(westRoad))
    }
    
object JunctionState:
  def apply(): JunctionState = JunctionState(RoadState(), RoadState(), RoadState(), RoadState())