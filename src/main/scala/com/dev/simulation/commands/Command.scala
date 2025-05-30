package com.dev.simulation.commands

import com.dev.simulation.commands.{AddVehicle, Step}

trait Command:
  def introduce(): Unit


object Command:
  def fromMap(data: Map[String, String]): Option[Command] = {
    data.get("type") flatMap {
      case "step" => Step.fromMap(data)
      case "addVehicle" => AddVehicle.fromMap(data)
      case _ => None
    }
  }