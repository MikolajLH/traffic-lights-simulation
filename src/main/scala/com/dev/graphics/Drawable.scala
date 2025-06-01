package com.dev.graphics

import com.dev.simulation.junction.{Crossing, Lane, Road, RoadElement}
import doodle.core.*
import doodle.image.*

trait Drawable[A]:
  extension (a: A) def toImage: Image


given Drawable[RoadElement] with
  extension (a: RoadElement) def toImage: Image = a match {
    case l: Lane => summon[Drawable[Lane]].toImage(l)
    case c: Crossing => summon[Drawable[Crossing]].toImage(c)
    case _ => throw new Exception()
  }

given Drawable[Lane] with
  extension (a: Lane) def toImage: Image = Image.rectangle(200, 20).fillColor(Color.gray)

given Drawable[Crossing] with
  extension (a: Crossing) def toImage: Image = Image.rectangle(200,20).fillColor(Color.azure)

given Drawable[Road] with
  extension (r: Road) def toImage: Image = r.elements.map(_.toImage).reduce(_.above(_))




