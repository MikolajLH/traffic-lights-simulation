package com.dev.simulation.state

import com.dev.simulation
import com.dev.simulation.{Direction, Vehicle}
import com.dev.simulation.Direction.{E, N, S, W}

case class JunctionVehicles(northRoad: RoadVehicles,
                            eastRoad: RoadVehicles,
                            southRoad: RoadVehicles,
                            westRoad: RoadVehicles):

  def roads(): List[RoadVehicles] = List(northRoad, eastRoad, southRoad, westRoad)

  def mapRoad(which: Direction, f: RoadVehicles => RoadVehicles): JunctionVehicles =
    which match {
      case N => JunctionVehicles(f(northRoad), eastRoad, southRoad, westRoad)
      case E => JunctionVehicles(northRoad, f(eastRoad), southRoad, westRoad)
      case S => JunctionVehicles(northRoad, eastRoad, f(southRoad), westRoad)
      case W => JunctionVehicles(northRoad, eastRoad, southRoad, f(westRoad))
    }

  def empty(): JunctionVehicles = JunctionVehicles(
    northRoad.empty,
    eastRoad.empty,
    southRoad.empty,
    westRoad.empty)

  def addVehicle(from: Direction, vehicle: Vehicle): Either[String, JunctionVehicles] = {
    val res = from match {
      case N => northRoad.addVehicle(vehicle)
      case E => eastRoad.addVehicle(vehicle)
      case S => southRoad.addVehicle(vehicle)
      case W => westRoad.addVehicle(vehicle)
    }
    
    res.map(value => from match
      case N => JunctionVehicles(value, eastRoad, southRoad, westRoad)
      case E => JunctionVehicles(northRoad, value, southRoad, westRoad)
      case S => JunctionVehicles(northRoad, eastRoad, value, westRoad)
      case W => JunctionVehicles(northRoad, eastRoad, southRoad, value))
  }


  def increment(): JunctionVehicles = JunctionVehicles(northRoad.increment(), eastRoad.increment(), southRoad.increment(), westRoad.increment().increment())


object JunctionVehicles:
  def apply(list: List[RoadVehicles]): Option[JunctionVehicles] =
    if list.length != 4
    then None
    else Some(JunctionVehicles(list.head, list(1), list(2), list(3)))

