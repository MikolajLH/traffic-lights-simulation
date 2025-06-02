package com.dev.graphics

import com.dev.simulation.junction.{Crossing, Lane, Road, RoadElement, TrafficLightsDirection}
import doodle.core.*
import doodle.syntax.all.*
import doodle.image.*


trait Drawable[A]:
  extension (a: A) def toImage: Image



given Drawable[RoadElement] with
  extension (a: RoadElement) def toImage: Image = a match {
    case l: Lane => summon[Drawable[Lane]].toImage(l)
    case c: Crossing => summon[Drawable[Crossing]].toImage(c)
    case _ => throw new Exception()
  }


given Drawable[Set[TrafficLightsDirection]] with
  extension (a: Set[TrafficLightsDirection]) def toImage: Image = a.map {
    case TrafficLightsDirection.forward => Primitives.forwardArrow(Color.green)
    case TrafficLightsDirection.left => Primitives.leftArrow(Color.green)
    case TrafficLightsDirection.right => Primitives.rightArrow(Color.green)
    case TrafficLightsDirection.leftArrow => Primitives.smallLeftArrow(30, Color.green)
    case TrafficLightsDirection.rightArrow => Primitives.smallRightArrow(30, Color.green)
  }.reduce(_.on(_)).transform(Transform.rotate(270.degrees))

given Drawable[Lane] with
  extension (a: Lane) def toImage: Image = a.to.toImage.transform(Transform.scale(0.1, 0.1)).on(Image.rectangle(200, 20).fillColor(Color.gray))

given Drawable[Crossing] with
  extension (a: Crossing) def toImage: Image = Image.rectangle(200,20).fillColor(Color.azure)

given Drawable[Road] with
  extension (r: Road) def toImage: Image = r.elements.map(_.toImage).reduce(_.above(_))




