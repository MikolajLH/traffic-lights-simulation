package com.dev.simulation.state

import com.dev.simulation.Vehicle

import scala.util.Try
import scala.collection.immutable.Queue

case class RoadState(vehicles: Queue[(Vehicle, Int)]):
  def put(vehicle: Vehicle): RoadState = RoadState(vehicles.appended((vehicle, 0)))
  def peek(): Option[(Vehicle, Int)] = Try(vehicles.front).toOption
  def get(): Option[((Vehicle, Int), RoadState)] = Try(vehicles.dequeue).toOption.map {case (v, q) => (v, RoadState(q))}
  def increment(): RoadState = RoadState(vehicles.map{case (v, i) => (v, i + 1)})

object RoadState:
  def apply(): RoadState = RoadState(Queue())