package com.dev.simulation.commands

import com.dev.simulation.Command

case class Step() extends Command:
  def introduce(): Unit = println(this.toString)

object Step:
  def fromMap(data: Map[String, String]): Option[Step] =
    Some(Step())