package com.dev.simulation

import com.dev.simulation.utility.LaneDirection.{forward, left, right}
import com.dev.simulation.utility.Light.red
import com.dev.simulation.mutable.{Junction, Lane, Road}
import com.dev.simulation.solve.VertexIndex
import com.dev.simulation.utility.TrafficLight


import scala.collection.mutable

object Junctions {
  def x4_LFR: (Junction, List[Set[VertexIndex]]) =
    val roadN = new Road(mutable.ArrayDeque(new Lane(Set(TrafficLight(Set(left, forward, right), red)))))
    val roadE = new Road(mutable.ArrayDeque(new Lane(Set(TrafficLight(Set(left, forward, right), red)))))
    val roadS = new Road(mutable.ArrayDeque(new Lane(Set(TrafficLight(Set(left, forward, right), red)))))
    val roadW = new Road(mutable.ArrayDeque(new Lane(Set(TrafficLight(Set(left, forward, right), red)))))

    val cliques = List( Set(0, 2), Set(1, 3))

    val junc: Junction = new Junction(roadN, roadE, roadS, roadW)
    val cs: List[Set[VertexIndex]] = cliques.map(s => s.flatMap(junc.getVertexIndexFromInt))

    (junc, cs)


  def x4_LiFiR: (Junction, List[Set[VertexIndex]]) = {
    val roadN = new Road(mutable.ArrayDeque(
      new Lane(Set(TrafficLight(Set(left), red))),
      new Lane(Set(TrafficLight(Set(forward), red))),
      new Lane(Set(TrafficLight(Set(right), red)))))


    val roadE = new Road(mutable.ArrayDeque(
      new Lane(Set(TrafficLight(Set(left), red))),
      new Lane(Set(TrafficLight(Set(forward), red))),
      new Lane(Set(TrafficLight(Set(right), red)))))


    val roadS = new Road(mutable.ArrayDeque(
      new Lane(Set(TrafficLight(Set(left), red))),
      new Lane(Set(TrafficLight(Set(forward), red))),
      new Lane(Set(TrafficLight(Set(right), red)))))


    val roadW = new Road(mutable.ArrayDeque(
      new Lane(Set(TrafficLight(Set(left), red))),
      new Lane(Set(TrafficLight(Set(forward), red))),
      new Lane(Set(TrafficLight(Set(right), red)))))


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
