package com.dev.simulation.solve

import com.dev.simulation.mutable.Simulation
import com.dev.simulation.utility
import com.dev.simulation.utility.Light

import scala.util.Try

case class CliquesSolver(cliques: List[Set[VertexIndex]]) extends Solver:
  override def solve(simulation: Simulation): Unit = {
    val vertices = simulation.junction.getAllVertices.filter(!_.isEmpty)
    val greens = vertices.filter(p => p.light == Light.green).map(p => p.toVertexIndex).toSet
    val waiting = vertices.filter(p => p.light == Light.red).map(p => p.toVertexIndex).toSet

    //println(waiting)
    //println(greens)
    // Find a clique that maximizes a cost function
    // ensures that clique is either a subset of greens or contains are greens
    // because green light always changes into yelow light, and vehicles can move at yellow light,
    val ratedCliques = for clique <- cliques if clique.subsetOf(greens) || greens.subsetOf(clique)
      yield {
        val weights = clique.flatMap(vi => simulation.junction.getVertex(vi).map(_.cost(3))).sum
        val size = clique.intersect(waiting).size
        (clique, (weights + 1) * (2 * size + 1))
      }


    val ps = Try(ratedCliques.maxBy(_._2)).toOption.map(_._1)
    val toBeActive = ps.getOrElse(Set())
    //println(toBeActive)

    for v <- vertices
      do {
        val vi = v.toVertexIndex
        v.light match {
          case utility.Light.red =>
            if toBeActive.contains(vi)
            then simulation.junction.setVertex(vi) // turn green
            else () // stay red
          case utility.Light.yellow =>
            simulation.junction.setVertex(vi)
          case utility.Light.green =>
            if toBeActive.contains(vi)
            then ()  // stay green
            else simulation.junction.setVertex(vi) // Turn yellow
        }
      }
  }
