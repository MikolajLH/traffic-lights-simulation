package com.dev.simulation.commands

import com.dev.simulation.state.SimulationState

trait Step extends Command:
  def introduce(): Unit = println("It's me, abstact step")
  def calculate(state: SimulationState): SimulationState = state
  final override def execute(state: SimulationState): SimulationState = {
    println("Before step:")
    state.junction.roads().foreach(println(_))
    print("++++++++++++++")
    state.trafficLights.roads().foreach(println(_))
    val res = calculate(state).move().increment()
    println("After step")
    state.junction.roads().foreach(println(_))
    print("++++++++++++++")
    state.trafficLights.roads().foreach(println(_))
    println("=================")
    res
  }

object Step:
  def fromMap(data: Map[String, String]): Option[Step] =
    Some(new Step {})