package com.dev.simulation.mutable

import com.dev.simulation.Vehicle

import scala.collection.mutable
import scala.util.Try

class Road(var lanes: mutable.ArrayDeque[Lane]):
  def addVehicle(vehicle: Vehicle): Either[String, Road] =
    for
      (_, i) <- Try(lanes.zipWithIndex.filter((lane, i) => lane.trafficPoles.exists(p =>p.flow.contains(vehicle.direction))).minBy(_._1.totalLoad())).toEither.left.map(_.getMessage)
    yield {
      lanes(i).vehicles.enqueue((vehicle, 0))
      this
    }