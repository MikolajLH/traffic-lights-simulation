package com.dev.graphics

import com.dev.simulation.junction.{Crossing, Junction, Lane, Road, RoadElement, TrafficLightsDirection}
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
    case TrafficLightsDirection.leftArrow => Primitives.smallLeftArrow(70, Color.green)
    case TrafficLightsDirection.rightArrow => Primitives.smallRightArrow(70, Color.green)
  }.reduce(_.on(_)).transform(Transform.rotate(270.degrees)).transform(Transform.scale(0.2, 0.2))

given Drawable[Lane] with
  extension (a: Lane) def toImage: Image = {
    a.trafficDirections.toImage.at(60, 0).on(Image.rectangle(200, 48).fillColor(Color.darkGray).strokeColor(Color.darkGray))
  }

given Drawable[Crossing] with
  extension (a: Crossing) def toImage: Image = {
    Image.rectangle(200,20).fillColor(Color.lightGray)
  }

given Drawable[Road] with
  extension (r: Road) def toImage: Image = {
    var laneCounter = 0
    var isCrossing = false
    val zebra = (for _ <- 1 to 4 yield List(Image.rectangle(30, 5).fillColor(Color.white).strokeColor(Color.white), Image.rectangle(30, 4).fillColor(Color.darkGray).strokeColor(Color.darkGray))).flatten.reduce(_.below(_)).below(Image.rectangle(30, 4).fillColor(Color.white).strokeColor(Color.white))
    val parts = for
      el <- r.elements
    yield {
        el match {
          case l: Lane =>
            laneCounter = laneCounter + 1
            val limg = if isCrossing then zebra.at(40,0).on(l.toImage) else l.toImage
            if laneCounter > 0
            then List(limg, Image.rectangle(200,2).fillColor(Color.lightGray).strokeColor(Color.lightGray))
            else List(limg)
          case _ =>
            laneCounter = 0
            isCrossing = true
            List(Image.rectangle(200,2).fillColor(Color.red).strokeColor(Color.black))
        }
      }
    parts.flatten.reduce(_.above(_))
    //r.elements.map(_.toImage).reduce(_.above(_))
  }


given Drawable[Junction] with
  extension (j: Junction) def toImage: Image = {

    j.westRoad.toImage.originAt(180, 0)
      .above(j.southRoad.toImage.transform(Transform.rotate(90.degrees))).at(0, -100)
      .beside(j.eastRoad.toImage.transform(Transform.rotate(180.degrees))).at(0, 0)
      .below(j.northRoad.toImage.transform(Transform.rotate(270.degrees)))
    //j.southRoad.toImage.transform(Transform.rotate(90.degrees))
    //j.eastRoad.toImage.transform(Transform.rotate(180.degrees))
    //j.northRoad.toImage.transform(Transform.rotate(270.degrees))
  }



