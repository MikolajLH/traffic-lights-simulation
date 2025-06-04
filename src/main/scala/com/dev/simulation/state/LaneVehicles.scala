package com.dev.simulation.state

import com.dev.simulation.{LaneDirection, Vehicle}

import scala.collection.immutable.Queue
import scala.util.Try

case class LaneVehicles(possibleFlows: Set[Set[LaneDirection]], vehicles: Queue[(Vehicle, Int)]):
  def empty: LaneVehicles = LaneVehicles(possibleFlows, Queue())
  
  def addVehicle(vehicle: Vehicle): Either[String, LaneVehicles] = 
    if possibleFlows.exists(_.contains(vehicle.direction))
    then Right(LaneVehicles(possibleFlows, vehicles.appended((vehicle, 0))))
    else Left("Incompatibible vehicle direction, this lane doesn't support this direction")
    

  def increment(): LaneVehicles = LaneVehicles(possibleFlows, vehicles.map{case (v, i) => (v, i + 1)})
  
  def getFirsts: (Option[(Vehicle, Int)], LaneVehicles) =
    Try(vehicles.dequeue).toOption
      .map {case (v, q) => (Some(v), LaneVehicles(possibleFlows, q))}
      .getOrElse((None, this))
  
  def peek(): Option[(Vehicle, Int)] = Try(vehicles.front).toOption