package com.dev.simulation.algorithms

import com.dev.simulation.commands.Step
import com.dev.simulation.state.SimulationState

case class Algorithm() extends Step:
  override def calculate(state: SimulationState): SimulationState = {
    val res = state.trafficLights.roads().map(p => p.lanes.map(k => k.lights.map(_.next())))
    SimulationState(
      state.junction,
      res,
      state.history
    )
  }
