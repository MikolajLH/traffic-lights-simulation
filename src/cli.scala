import upickle.default as upickle
import os as os


@main def cli(inputFilePath: String): Unit = {
  println(inputFilePath)
  val jsonString = os.read(os.pwd / inputFilePath)
  val jsonMap = upickle.read[Map[String, List[Map[String, String]]]](jsonString)
}