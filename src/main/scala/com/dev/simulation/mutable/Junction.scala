package com.dev.simulation.mutable

import com.dev.simulation.solve.{Vertex, VertexIndex}

import scala.collection.mutable
import com.dev.simulation.utility.Direction.{E, N, S, W}
import com.dev.simulation.utility.{Direction, Light, Vehicle}

import scala.util.Try

class Junction private (var junction: mutable.Map[Direction, Road]):
  def this(northRoad: Road, eastRoad: Road, southRoad: Road, westRoad: Road) = this(mutable.Map(N -> northRoad, E -> eastRoad, S -> southRoad, W -> westRoad))

  def getAllVertices: List[Vertex] = {
    val res = for ((d, road) <- junction)
      yield for (lane, i) <- road.lanes.zipWithIndex
        yield for pole <- lane.trafficPoles
          yield Vertex(d, i, pole.flow, pole.light, lane.totalLoad(), lane.vehicles.size)

    res.flatten.flatten.toList
  }

  def getVertexIndexFromInt(i: Int): Option[VertexIndex] = {
    val vertices = getAllVertices
    if i < 0 || i >= vertices.size then None
    else Some(vertices(i).toVertexIndex)
  }

  def getIntFromVertexIndex(vi: VertexIndex): Int = {
    getAllVertices.map(_.toVertexIndex).zipWithIndex.find((vj, k) => vj == vi ).get._2
  }

  def getVertex(v: VertexIndex): Option[Vertex] = {
    val lane = Try(junction(v.atRoad).lanes(v.atLane)).toOption
    val len = lane.map(_.vehicles.length)
    val load = lane.map(_.totalLoad())
    val light = lane.flatMap(_.trafficPoles.find(p => p.flow == v.atTrafficPole).map(_.light))

    for
      a <- len
      b <- load
      c <- light
    yield Vertex(v.atRoad, v.atLane, v.atTrafficPole, c, b, a)
  }


  def setVertex(v: VertexIndex): Option[Unit] = {
    for
      lane <- Try(junction(v.atRoad).lanes(v.atLane)).toOption
      chosen <- lane.trafficPoles.find(p => p.flow == v.atTrafficPole)
    yield {
      val diff = lane.trafficPoles - chosen
      val updated = diff + chosen.next()
      lane.trafficPoles = updated
    }
  }

  def addVehicle(from: Direction, vehicle: Vehicle): Either[String, Junction] = junction(from).addVehicle(vehicle).map(_ => this)