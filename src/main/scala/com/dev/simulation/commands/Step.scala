package com.dev.simulation.commands

import com.dev.simulation.mutable.Simulation

trait Step extends Command:
  override def executeOn(simulation: Simulation): Unit = {
    println("Step()")
    simulation.progress()
    simulation.show()
  }

object Step:
  def fromMap(data: Map[String, String]): Option[Step] =
    Some(new Step {})