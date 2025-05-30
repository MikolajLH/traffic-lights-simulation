package com.dev.simulation.commands

import com.dev.simulation.{Command, Direction}


case class AddVehicle(vehicleId: String, from: Direction, to: Direction) extends Command:
  def introduce(): Unit = println(this.toString)

object AddVehicle:
  def fromMap(data: Map[String, String]): Option[AddVehicle] =
    for
      vehicleId <- data.get("vehicleId")
      startRoad <- data.get("startRoad")
      endRoad <- data.get("endRoad")
      from <- Direction.fromString(startRoad)
      to <- Direction.fromString(endRoad)
    yield AddVehicle(vehicleId, from, to)
