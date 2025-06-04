package com.dev.cli

import upickle.default as upickle
import os as os


object OutputFile {
  private case class OutputFileFormat(stepStatuses: List[Map[String,List[String]]]) derives upickle.ReadWriter

  def save(outputFilePath: String, obj: List[List[String]]): Unit =
    val jsonObj = for stepResult <- obj yield Map("leftVehicles" -> (for vehicle <- stepResult yield vehicle))
    val jsonString = upickle.write(OutputFileFormat(jsonObj), 4)
    os.write(os.pwd/outputFilePath, jsonString)
}
