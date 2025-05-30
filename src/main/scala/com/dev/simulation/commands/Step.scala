package com.dev.simulation.commands

case class Step() extends Command:
  def introduce(): Unit = println(this.toString)

object Step:
  def fromMap(data: Map[String, String]): Option[Step] =
    Some(Step())