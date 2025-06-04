package com.dev.simulation.state

case class SimulationState(
                            junction: JunctionState,
                            trafficLights: TrafficLightsState,
                            history: List[List[String]]):
  def move(): SimulationState = this
  def increment(): SimulationState = SimulationState(junction.increment(), trafficLights, history)
