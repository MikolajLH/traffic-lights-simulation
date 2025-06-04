package com.dev.simulation.solve

import scala.collection.mutable
import com.dev.simulation.Direction
import com.dev.simulation.Direction.{E, N, S, W}
import com.dev.simulation.Vehicle

class Junction private (var junction: mutable.Map[Direction, Road]):
  def this(northRoad: Road, eastRoad: Road, southRoad: Road, westRoad: Road) = this(mutable.Map(N -> northRoad, E -> eastRoad, S -> southRoad, W -> westRoad))

  def addVehicle(vehicle: Vehicle): Either[String, Junction] = Left("TODO")
    
