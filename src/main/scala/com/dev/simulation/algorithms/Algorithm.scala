package com.dev.simulation.algorithms

import com.dev.simulation.commands.Step
import com.dev.simulation.state.SimulationState

case class Algorithm() extends Step:
  override def calculate(state: SimulationState): SimulationState = {
    println("Algorithm")
    println(state.junction)
    state
  }
