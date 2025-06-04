package com.dev.simulation.commands

import com.dev.simulation.mutable.Simulation
import com.dev.simulation.utility.{Direction, LaneDirection, Vehicle}


case class AddVehicle(vehicleId: String, from: Direction, to: Direction) extends Command:

  override def executeOn(simulation: Simulation): Unit = {
    val vehicle = Vehicle(vehicleId, LaneDirection(from, to))
    for err <- simulation.junction.addVehicle(from, vehicle).left do simulation.logs = err :: simulation.logs
  }

object AddVehicle:
  def fromMap(data: Map[String, String]): Option[AddVehicle] =
    for
      cmd <- data.get("type").filter(_ == "addVehicle")
      vehicleId <- data.get("vehicleId").filter(s => !(s.isBlank || s.isEmpty))
      startRoad <- data.get("startRoad")
      endRoad <- data.get("endRoad")
      from <- Direction.fromString(startRoad)
      to <- Direction.fromString(endRoad)
    yield AddVehicle(vehicleId, from, to)
