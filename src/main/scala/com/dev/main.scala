package com.dev

import com.dev.cli.InputFile


@main def main(inputFilePath: String): Unit= {
  println(inputFilePath)

  for commands <- InputFile.parse(inputFilePath) do commands.flatten.foreach( cmd => cmd.introduce())

}

