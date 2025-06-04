package com.dev.simulation.solve

import com.dev.simulation.Direction

class Simulation(var junction: Junction):
  var logs: List[String] = List()
  var left: List[List[String]] = List()
  
  def progressSimulation(): Simulation = this
  
  