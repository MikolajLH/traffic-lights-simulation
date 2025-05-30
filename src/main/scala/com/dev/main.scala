package com.dev

import upickle.default as upickle

case class InputFile(commands: List[Map[String, String]]) derives upickle.ReadWriter

trait Command:
  def introduce(): Unit

case class Step() extends Command:
  def introduce(): Unit = println("Step")

case class AddVehicle(id: String, from: String, to: String) extends Command:
  def introduce(): Unit = println(s"AddVehicle: $id, $from, $to")
case class Unknown() extends Command:
  def introduce(): Unit = println("Unknown")

@main def main(inputFilePath: String): Unit= {
  println(inputFilePath)
  val source = scala.io.Source.fromFile(inputFilePath)
  val jsonString = try source.mkString finally source.close()

  val inputFile = upickle.read[InputFile](jsonString)
  val commands = inputFile.commands.map(
    m => if !m.contains("type")
      then Unknown()
      else m("type") match {
        case "step" => Step()
        case "addVehicle" => AddVehicle(m("vehicleId"), m("startRoad"), m("endRoad"))
        case _ => Unknown()
      })

  commands.foreach( cmd => cmd.introduce())
}

