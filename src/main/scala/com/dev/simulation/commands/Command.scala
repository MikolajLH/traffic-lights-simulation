package com.dev.simulation.commands

import com.dev.simulation.commands.{AddVehicle, Step}
import com.dev.simulation.state.SimulationState
import com.dev.simulation.solve.Simulation

trait Command:
  def introduce(): Unit
  def execute(state: SimulationState): SimulationState = state
  def executeOn(simulation: Simulation): Unit = ()
  
object Command:
  def fromMap(data: Map[String, String]): Option[Command] = {
    data.get("type") flatMap {
      case "step" => Step.fromMap(data)
      case "addVehicle" => AddVehicle.fromMap(data)
      case _ => None
    }
  }