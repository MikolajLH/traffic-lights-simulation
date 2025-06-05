package com.dev.simulation

import com.dev.simulation.mutable.{Junction, Road}
import com.dev.simulation.solve.VertexIndex

import com.dev.simulation.utility.Direction.{E, N, S, W}
import com.dev.simulation.utility.LaneDirection.{forward, left, right}
import com.dev.simulation.utility.Light.{green, red, yellow}
import com.dev.simulation.mutable.{Junction, Lane, Road, Simulation}
import com.dev.simulation.solve.{CliquesSolver, VertexIndex}
import com.dev.simulation.utility.TrafficLight

import scala.collection.immutable.Queue
import scala.collection.mutable

import scala.collection.mutable

object Examples {
  
  def simple12x: (Junction, List[Set[VertexIndex]]) = {
    val roadN = new Road(mutable.ArrayDeque(
      Lane(Set(TrafficLight(Set(left), red))),
      Lane(Set(TrafficLight(Set(forward), red))),
      Lane(Set(TrafficLight(Set(right), red)))))


    val roadE = new Road(mutable.ArrayDeque(
      Lane(Set(TrafficLight(Set(left), red))),
      Lane(Set(TrafficLight(Set(forward), red))),
      Lane(Set(TrafficLight(Set(right), red)))))


    val roadS = new Road(mutable.ArrayDeque(
      Lane(Set(TrafficLight(Set(left), red))),
      Lane(Set(TrafficLight(Set(forward), red))),
      Lane(Set(TrafficLight(Set(right), red)))))


    val roadW = new Road(mutable.ArrayDeque(
      Lane(Set(TrafficLight(Set(left), red))),
      Lane(Set(TrafficLight(Set(forward), red))),
      Lane(Set(TrafficLight(Set(right), red)))))


    val cliques = List(
      Set(0, 1, 2, 5, 8, 11),
      Set(0, 2, 4, 5, 8, 11),
      Set(0, 2, 5, 6, 8, 11),
      Set(1, 2, 5, 7, 8, 11),
      Set(1, 2, 5, 8, 9, 11),
      Set(2, 3, 4, 5, 8, 11),
      Set(2, 3, 5, 7, 8, 11),
      Set(2, 3, 5, 8, 9, 11),
      Set(2, 5, 6, 7, 8, 11),
      Set(2, 4, 5, 8, 10, 11),
      Set(2, 5, 6, 8, 10, 11),
      Set(2, 5, 8, 9, 10, 11))


    val junc: Junction = new Junction(roadN, roadE, roadS, roadW)

    val cs: List[Set[VertexIndex]] = cliques.map(s => s.flatMap(junc.getVertexIndexFromInt))
    
    (junc, cs)
  }
}
