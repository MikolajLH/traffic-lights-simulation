import upickle.default._
import os._

@main def cli(inputFilePath: String): Unit = {
  println(inputFilePath)
  val jsonString = os.read(os.pwd / inputFilePath)
  print(jsonString)
}