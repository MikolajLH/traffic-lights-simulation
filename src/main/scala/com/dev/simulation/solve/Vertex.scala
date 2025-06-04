package com.dev.simulation.solve

import com.dev.simulation.utility.{Direction, LaneDirection, Light}


case class Vertex(atRoad: Direction, atLane: Int, atTrafficPole: Set[LaneDirection], light: Light, waitTime: Int, vehiclesCount: Int)
