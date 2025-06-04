package com.dev.simulation.mutable

import scala.collection.mutable
import com.dev.simulation.utility.Direction.{E, N, S, W}
import com.dev.simulation.utility.{Direction, Vehicle}

class Junction private (var junction: mutable.Map[Direction, Road]):
  def this(northRoad: Road, eastRoad: Road, southRoad: Road, westRoad: Road) = this(mutable.Map(N -> northRoad, E -> eastRoad, S -> southRoad, W -> westRoad))

  def addVehicle(from: Direction, vehicle: Vehicle): Either[String, Junction] = junction(from).addVehicle(vehicle).map(_ => this)