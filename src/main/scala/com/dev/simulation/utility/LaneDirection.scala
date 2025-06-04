package com.dev.simulation.utility



enum LaneDirection:
  case forward, left, right

object LaneDirection:
  def apply(from: Direction, to: Direction): LaneDirection = (from, to) match
    case (Direction.N, Direction.S) => forward
    case (Direction.S, Direction.N) => forward
    case (Direction.E, Direction.W) => forward
    case (Direction.W, Direction.E) => forward
    
    case (Direction.N, Direction.W) => right
    case (Direction.W, Direction.S) => right
    case (Direction.S, Direction.E) => right
    case (Direction.E, Direction.N) => right

    case (Direction.N, Direction.E) => left
    case (Direction.E, Direction.S) => left
    case (Direction.S, Direction.W) => left
    case (Direction.W, Direction.N) => left
    