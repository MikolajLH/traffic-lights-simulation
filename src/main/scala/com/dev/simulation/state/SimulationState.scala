package com.dev.simulation.state

import com.dev.simulation.Light.green

case class SimulationState(
                            junction: JunctionVehicles,
                            trafficLights: JunctionTrafficLights,
                            history: List[List[String]]):
  def move(): SimulationState = {
    val res = for 
      (roadVs, roadTfs) <- junction.roads().zip(trafficLights.roads()) 
    yield {
      val perRoad =
        for
          ((laneVOpt, laneTf), (original, changed)) <- roadVs.peekFirsts.zip(roadTfs.lanes).zip(roadVs.getLanesAndLanesRemoved)
        yield {
          if laneVOpt.isEmpty
          then (laneVOpt, original)
          else if laneTf.lights.exists(tf => tf.flow.contains(laneVOpt.get._1.direction) && tf.light == green)
            then (laneVOpt, changed)
            else (laneVOpt, original)
        }
      perRoad.unzip    
    }
    val (left, roads) = (for (leftPerRoad, lanesPerRoad) <- res
      yield
        (leftPerRoad, RoadVehicles(lanesPerRoad))).unzip
    
    val leftsIds = left.flatten.flatten.map((v, _) => v.vehicleId)
    
    SimulationState(
      JunctionVehicles(roads).getOrElse(junction.empty()),
      trafficLights,
      leftsIds :: history 
    )
  }

  def increment(): SimulationState = SimulationState(junction.increment(), trafficLights, history)
