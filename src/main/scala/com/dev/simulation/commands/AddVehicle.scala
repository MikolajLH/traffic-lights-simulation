package com.dev.simulation.commands

import com.dev.simulation.solve.Simulation
import com.dev.simulation.{Direction, LaneDirection, Vehicle}
import com.dev.simulation.state.{JunctionTrafficLights, JunctionVehicles, SimulationState}


case class AddVehicle(vehicleId: String, from: Direction, to: Direction) extends Command:
  def introduce(): Unit = println(this.toString)
  
  override def execute(state: SimulationState): SimulationState = {
    val updated = state.junction.addVehicle(from, Vehicle(vehicleId, LaneDirection(from, to))).left.map(s => println(s)).getOrElse(state.junction)
    SimulationState(
      updated,
      state.trafficLights,
      state.history)
  }

  override def executeOn(simulation: Simulation): Unit = {
    println("AddVehicle()")
    val vehicle = Vehicle(vehicleId, LaneDirection(from, to))
    for err <- simulation.junction.addVehicle(from, vehicle).left do simulation.logs = err :: simulation.logs
    ()
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
