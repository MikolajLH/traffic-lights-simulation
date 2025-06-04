package com.dev.simulation.solve

import com.dev.simulation.mutable.Simulation

trait Solver():
  def solve(simulation: Simulation): Unit = ()