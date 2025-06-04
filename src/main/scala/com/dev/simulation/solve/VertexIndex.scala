package com.dev.simulation.solve

import com.dev.simulation.utility.{Direction, LaneDirection}

case class VertexIndex(atRoad: Direction, atLane: Int, atTrafficPole: Set[LaneDirection])
