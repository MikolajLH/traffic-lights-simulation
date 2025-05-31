package com.dev

import com.dev.cli.InputFile
import doodle.image.*
import doodle.core.*
import doodle.core.format.Png

// Extension methods
import doodle.image.syntax.all.*
// Render to a window using Java2D (must be running in the JVM)
import doodle.java2d.*
// Need the Cats Effect runtime to run everything
import cats.effect.unsafe.implicits.global


@main def main(inputFilePath: String): Unit = {
  val img = Image
    .rectangle(800,100).fillColor(Color.gray)
    .above(Image.rectangle(800,10).fillColor(Color.lightGray)
      .above(Image.rectangle(800,10).fillColor(Color.gray)))

  val images = List(
    Image.rectangle(800,100).fillColor(Color.gray),
    Image.rectangle(800,10).fillColor(Color.lightGray),
    Image.rectangle(800,100).fillColor(Color.gray)
  )

  // Stack images dynamically
  val stacked = images.reduce(_.above(_))


  stacked.draw()


  println(inputFilePath)

  for
    commands <- InputFile.parse(inputFilePath)
  do
    commands.flatten.foreach( cmd => cmd.introduce())
}

