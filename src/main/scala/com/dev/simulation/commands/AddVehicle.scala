package com.dev.simulation.commands

import com.dev.simulation.{Direction, Vehicle, LaneDirection}
import com.dev.simulation.state.{SimulationState, RoadState}


case class AddVehicle(vehicleId: String, from: Direction, to: Direction) extends Command:
  def introduce(): Unit = println(this.toString)
  
  override def execute(state: SimulationState): SimulationState =
    SimulationState(
      state.junction.mapRoad(from, _.put(Vehicle(vehicleId, LaneDirection(from, to)))),
      state.trafficLights,
      state.history
    )

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
