package com.dev.simulation

import org.scalatest.funsuite.AnyFunSuite


class DirectionTest extends AnyFunSuite {
  test("Valid direction names should return correct Direction") {
    assert(Direction.fromString("north").contains(Direction.N))
    assert(Direction.fromString("east").contains(Direction.E))
    assert(Direction.fromString("south").contains(Direction.S))
    assert(Direction.fromString("west").contains(Direction.W))
  }

  test("Invalid direction names should return None") {
    assert(Direction.fromString("invalid").isEmpty)
    assert(Direction.fromString("NORTHEAST").isEmpty)
  }

  test("Case insensitivity should be handled correctly") {
    assert(Direction.fromString("NORTH").contains(Direction.N))
    assert(Direction.fromString("WEST").contains(Direction.W))
    assert(Direction.fromString("sOuTh").contains(Direction.S))
    assert(Direction.fromString("EaSt").contains(Direction.E))
  }
}
