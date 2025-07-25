package com.dev.simulation.mutable

import com.dev.simulation.utility.{Light, TrafficLight, Vehicle}

import scala.collection.mutable
import scala.util.Try

class Lane(var trafficPoles: Set[TrafficLight]):
  var vehicles: mutable.Queue[(Vehicle, Int)] = mutable.Queue()
  def addVehicle(vehicle: Vehicle): Lane = {
    vehicles.enqueue((vehicle, 0))
    this
  }

  def incrementWaitingTime(): Lane = {
    //vehicles.mapInPlace((v, i) => (v, i + 1))

    val res = Try({
      val (v, i) = vehicles(0)
      vehicles(0) = (v, i + 1)
      ()
    }).toOption

    this
  }

  def totalLoad(): Int = vehicles.foldRight(0)((a, acc) => acc + a._2)
  
  def firstLoad(): Int = Try(vehicles.head).toOption.map(p => p._2).getOrElse(0)

  def go(): Option[String] = {
    val res = for
      (v, _) <- Try(vehicles.front).toOption
      good <- trafficPoles.find(p => p.flow.contains(v.direction)).map(_.light != Light.red)
    yield
      if good
      then {
        vehicles.dequeue()
        Some(v.vehicleId)
      }
      else None

    res.flatten
  }

