package com.dev.simulation.state

import com.dev.simulation.{LaneDirection, Vehicle}

import scala.collection.immutable.Queue
import scala.util.Try

case class RoadVehicles(lanes: List[LaneVehicles]):
  def empty: RoadVehicles = RoadVehicles(for lane <- lanes yield lane.empty)

  def increment(): RoadVehicles = RoadVehicles(for lane <- lanes yield lane.increment())

  def addVehicle(vehicle: Vehicle): Either[String, RoadVehicles] = {
    for
      (laneWhereAdd, i) <- Try(lanes.zipWithIndex
        .filter((p, i) => p.possibleFlows.exists(_.contains(vehicle.direction)))
        .minBy((p, i) => p.vehicles.length)).toEither.left.map(_.getMessage)
      updatedLane <- laneWhereAdd.addVehicle(vehicle)
    yield
      RoadVehicles(lanes.updated(i, updatedLane))
  }


  def peekFirsts: List[Option[(Vehicle, Int)]] = for lane <- lanes  yield lane.peek()

  def getLanesAndLanesRemoved: List[(LaneVehicles, LaneVehicles)] =
    for lane <- lanes
    yield (
      lane,
      Try(lane.vehicles.dequeue)
        .toOption
        .map((_, q) => LaneVehicles(lane.possibleFlows, q))
        .getOrElse(LaneVehicles(lane.possibleFlows, lane.vehicles))
    )