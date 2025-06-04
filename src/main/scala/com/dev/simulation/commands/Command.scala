package com.dev.simulation.commands

import com.dev.simulation.commands.{AddVehicle, Step}
import com.dev.simulation.mutable.Simulation

trait Command:
  def executeOn(simulation: Simulation): Unit = ()

object Command:
  def fromMap(data: Map[String, String]): Option[Command] = {
    data.get("type") flatMap {
      case "step" => Step.fromMap(data)
      case "addVehicle" => AddVehicle.fromMap(data)
      case _ => None
    }
  }