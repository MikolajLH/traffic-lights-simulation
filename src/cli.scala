import upickle.default._
import os as os

enum Direction(direction: String) {
  case N extends Direction("north")
  case E extends Direction("east")
  case S extends Direction("south")
  case W extends Direction("west")
}

case class Route(from: Direction, to: Direction)
case class VehicleRoute(vehicleId: String, route: Route)


sealed trait Command
case class AddVehicleCommand(vehicleId: String, startRoad: String, endRoad: String) extends Command derives ReadWriter
case class StepCommand() extends Command derives ReadWriter
case class UnknownCommand() extends Command derives ReadWriter

case class Test(test: String) derives ReadWriter

@main def cli(inputFilePath: String): Unit = {
  println(inputFilePath)
  val jsonString = os.read(os.pwd / inputFilePath)

  val jsonMap = read[Map[String, List[Map[String, String]]]](jsonString)
  val commands = jsonMap.getOrElse("commands", List()).map[Command](
    map =>
      map.get("type") match
        case Some("addVehicle") => AddVehicleCommand(map("vehicleId"), map("startRoad"), map("endRoad"))
        case Some("step") => StepCommand()
        case _ => UnknownCommand()
  )

  for cmd <- commands do println(cmd)
}