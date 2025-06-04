package com.dev.simulation.commands

import com.dev.simulation.state.SimulationState

trait Step extends Command:
  def introduce(): Unit = println("It's me, abstact step")
  def calculate(state: SimulationState): SimulationState = state
  final override def execute(state: SimulationState): SimulationState = {
    calculate(state).move().increment()
  }

object Step:
  def fromMap(data: Map[String, String]): Option[Step] =
    Some(new Step {})