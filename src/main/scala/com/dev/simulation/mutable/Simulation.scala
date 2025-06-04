package com.dev.simulation.mutable

import com.dev.simulation.utility.Direction

class Simulation(var junction: Junction):
  var logs: List[String] = List()
  var left: List[List[String]] = List()

  def progress(): Unit = {
    // Move the vehicles
    var leftNow: List[Option[String]] = List()
    for ((d, road) <- junction.junction)
      do for lane <- road.lanes
        do leftNow = lane.go() :: leftNow


    // Increment their waiting timers
    for ((d, road) <- junction.junction)
      do for lane <- road.lanes
        do lane.incrementWaitingTime()
    
    left = leftNow.flatten :: left
  }

  def show(): Unit = {
    for ((d, road) <- junction.junction)
      do {
        println(s"Road ${d}")
        for lane <- road.lanes
          do {
            if lane.vehicles.nonEmpty
            then
              lane.vehicles.foreach(p => print(s"${p} "))
              println("")
          }
      }
  }

