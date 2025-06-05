package com.dev.simulation.solve

import com.dev.simulation.utility.Light.yellow
import com.dev.simulation.utility.{Direction, LaneDirection, Light}


case class Vertex(atRoad: Direction, atLane: Int, atTrafficPole: Set[LaneDirection], light: Light, waitTime: Int, vehiclesCount: Int, firstWaitingTime: Int):
  def cost(th: Int): Float =
    if light == yellow || vehiclesCount == 0 then 0
    else if firstWaitingTime > th
    then Float.PositiveInfinity
    else 3 * vehiclesCount * (waitTime + 1)

  def isEmpty: Boolean = vehiclesCount == 0
  
  def toVertexIndex: VertexIndex = VertexIndex(atRoad, atLane, atTrafficPole)