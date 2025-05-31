package com.dev.graphics

import doodle.image.*
import doodle.core.*

object Primitives:
  def rect(width: Double, height: Double, color: Color): Image = Image.rectangle(width, height).fillColor(color)
  def circle(diameter: Double, color: Color): Image = Image.circle(diameter).fillColor(color)
  
  def rightArrow(): Image = Image.circle(0)
  def leftArrow(): Image = Image.circle(0)
  def forwardArrow(): Image = Image.circle(0)
  def laneSeparator(): Image = Image.circle(0)
  def alternatingRectangles(count: Int, width: Double, height: Double, color1: Color, color2: Color): Image = Image.circle(0)