package com.dev.graphics

import doodle.image.*
import doodle.core.*

object Primitives:
  def rect(width: Double, height: Double, color: Color): Image = Image.rectangle(width, height).fillColor(color)
  def circle(diameter: Double, color: Color): Image = Image.circle(diameter).fillColor(color)
  
  def rightArrow(color: Color): Image = Image.path(
      OpenPath.empty
      .lineTo(0, 100)
      .curveTo(0, 100, 0, 150, 50, 150)
      .lineTo(60, 150)
      .lineTo(60, 170)
      .lineTo(80, 150)
      .lineTo(60, 130)
      .lineTo(60,150))
      .strokeWidth(5)
      .strokeColor(color)

  def leftArrow(color: Color): Image = rightArrow(color).transform(Transform.horizontalReflection)

  def forwardArrow(color: Color): Image = Image.path(
        OpenPath.empty
        .lineTo(0, 170)
        .lineTo(20, 170)
        .lineTo(0, 190)
        .lineTo(-20, 170)
        .lineTo(0, 170))
        .strokeWidth(5)
        .strokeColor(color)

  def smallRightArrow(height: Double, color: Color): Image = Image.path(
      OpenPath.empty
      .lineTo(0, height)
      .curveTo(0, height, 0, height + 15, 15, height + 15)
      .lineTo(15, height + 30)
      .lineTo(30, height + 15)
      .lineTo(15, height)
      .lineTo(15, height + 15))
      .strokeWidth(5)
      .strokeColor(color)
  
  def smallLeftArrow(height: Double, color: Color): Image = smallRightArrow(height, color).transform(Transform.horizontalReflection)

  def alternatingRectangles(count: Int, width: Double, height: Double, color1: Color, color2: Color): Image = Image.circle(0)