package com.dev.simulation.utility

enum Light:
  case red, yellow, green
  def next(): Light = this match {
    case Light.red => Light.green
    case Light.yellow => Light.red
    case Light.green => Light.yellow
  }