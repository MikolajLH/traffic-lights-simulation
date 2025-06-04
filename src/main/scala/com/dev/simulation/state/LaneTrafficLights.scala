package com.dev.simulation.state

import com.dev.simulation.{Direction, LaneDirection, TrafficLight}

case class LaneTrafficLights(lights: Set[TrafficLight]):
  def next(): LaneTrafficLights = LaneTrafficLights(lights.map(_.next()))