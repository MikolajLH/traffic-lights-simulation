package com.dev.simulation.solve

import com.dev.simulation.mutable.Simulation
import com.dev.simulation.utility.Light.{green, red, yellow}

case class CliquesSolver(cliques: List[Set[VertexIndex]]) extends Solver:
  override def solve(simulation: Simulation): Unit = {
    val vertices = simulation.junction.getAllVertices

    // Find a clique that maximizes a cost function, ignore the vertices with yellow light since they are going to turn red anyway
    val ratedCliques = for clique <- cliques
      yield {
        val cc = clique.flatMap(vi => simulation.junction.getVertex(vi).map(_.cost(5))).sum
        (clique, cc)
      }

    val ps = ratedCliques.maxBy(_._2)
    val toBeActive = ps._1

    // If vertex is in the clique it can be turned green/staye green without any worry
    for v <- vertices
      do {
        val vi = v.toVertexIndex
        if v.light == yellow
        then simulation.junction.setVertex(vi)
        else if toBeActive.contains(vi)
          then if v.light == red then simulation.junction.setVertex(vi)
        
        if  v.light == green && !toBeActive.contains(vi)
          then simulation.junction.setVertex(vi)
      }
  }
