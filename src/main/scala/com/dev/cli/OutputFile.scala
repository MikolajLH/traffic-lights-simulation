package com.dev.cli

import upickle.default as upickle
import os as os


object OutputFile {
  case class OutputFileFormat(stepStatuses: List[Map[String,Set[String]]]) derives upickle.ReadWriter

  def getJsonString(obj: List[Set[String]]): String =
    val jsonObj = for stepResult <- obj yield Map("leftVehicles" -> (for vehicle <- stepResult yield vehicle))
    val jsonString = upickle.write(OutputFileFormat(jsonObj), 4)
    jsonString

  def save(outputFilePath: os.Path, obj: List[Set[String]]): Unit =
    val jsonString = getJsonString(obj)
    os.write.over(outputFilePath, jsonString)
}
