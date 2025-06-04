package com.dev.simulation.solve

import com.dev.simulation.Direction

class Simulation(var junction: Junction):
  var logs: List[String] = List()
  var left: List[List[String]] = List()

  def progressSimulation(): Simulation = {
    var leftNow: List[Option[String]] = List()
    for ((d, road) <- junction.junction)
      do for lane <- road.lanes
        do leftNow = lane.go() :: leftNow

    left = leftNow.flatten :: left
    this
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

