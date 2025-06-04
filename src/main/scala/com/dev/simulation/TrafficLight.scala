package com.dev.simulation

case class TrafficLight(flow: Set[LaneDirection], light: Light):
  def next(): TrafficLight = TrafficLight(flow, light.next())