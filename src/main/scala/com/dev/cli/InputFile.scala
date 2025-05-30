package com.dev.cli

import com.dev.simulation.commands.Command
import upickle.default as upickle

import scala.util.{Try, Using}

object InputFile {
  private case class InputFileFormat(commands: List[Map[String, String]]) derives upickle.ReadWriter

  def parse(inputFilePath: String): Either[String, List[Option[Command]]] =
    for
      jsonString <- Using(scala.io.Source.fromFile(inputFilePath)) { source => source.mkString }.toEither.left.map(_.getMessage)
      jsonObj <- Try(upickle.read[InputFileFormat](jsonString)).toEither.left.map(_.getMessage)
    yield jsonObj.commands map Command.fromMap
}
