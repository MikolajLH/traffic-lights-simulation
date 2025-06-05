package com.dev.simulation.utility

import scala.math.Ordering


enum Direction:
  case N, E, S, W

given Ordering[Direction] = Ordering.by(_.ordinal)

object Direction:
  def fromString(str: String): Option[Direction] = 
    str.toLowerCase match 
      case "north" => Some(Direction.N)
      case "east" => Some(Direction.E)
      case "south" => Some(Direction.S)
      case "west" => Some(Direction.W)
      case _ => None