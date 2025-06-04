package com.dev.simulation.commands

import com.dev.simulation.state.SimulationState

trait Step extends Command:
  def introduce(): Unit = println("Step()")
  def calculate(state: SimulationState): SimulationState = state
  final override def execute(state: SimulationState): SimulationState = {
    println("Before step:")
    state.junction.roads().foreach(println(_))
    println("++++++++++++++")
    state.trafficLights.roads().foreach(println(_))
    val res = calculate(state).move().increment()
    println("After step")
    res.junction.roads().foreach(println(_))
    println("++++++++++++++")
    res.trafficLights.roads().foreach(println(_))
    println("=================")
    println("")
    res
  }

object Step:
  def fromMap(data: Map[String, String]): Option[Step] =
    Some(new Step {})