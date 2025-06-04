package com.dev.simulation.commands

import com.dev.simulation.utility.Direction.{N, S, W}
import org.scalatest.funsuite.AnyFunSuite


class AddVehicleTest extends AnyFunSuite {
  val validData1: Map[String, String] = Map(
    "type" -> "addVehicle",
    "vehicleId" -> "vehicle1",
    "startRoad" -> "south",
    "endRoad" -> "north"
  )
  val validData2: Map[String, String] = Map(
    "type" -> "addVehicle",
    "vehicleId" -> "vehicle3",
    "startRoad" -> "west",
    "endRoad" -> "south"
  )

  test("Valid input maps should create AddVehicle instances") {
    assert(AddVehicle.fromMap(validData1).contains(AddVehicle("vehicle1", S, N)))
    assert(AddVehicle.fromMap(validData2).contains(AddVehicle("vehicle3", W, S)))
  }

  // wrong command type
  val invalidData1: Map[String, String] = Map(
    "type" -> "step",
    "vehicleId" -> "vehicle1",
    "startRoad" -> "south",
    "endRoad" -> "north"
  )

  test("AddVehicle can't be create if the type of command is wrong") {
    assert(AddVehicle.fromMap(invalidData1).isEmpty)
  }


  // type key is missing
  val invalidData2: Map[String, String] = Map(
    "vehicleId" -> "vehicle1",
    "startRoad" -> "south",
    "endRoad" -> "north"
  )

  //startRoad key is missing
  val invalidData3: Map[String, String] = Map(
    "type" -> "addVehicle",
    "vehicleId" -> "vehicle1",
    "endRoad" -> "north"
  )

  test("AddVehicle can't be created if one the keys is missing") {
    assert(AddVehicle.fromMap(invalidData2).isEmpty)
    assert(AddVehicle.fromMap(invalidData3).isEmpty)
  }

  // empty string as value for endRoad
  val invalidData4: Map[String, String] = Map(
    "type" -> "step",
    "vehicleId" -> "vehicle1",
    "startRoad" -> "south",
    "endRoad" -> ""
  )

  // empty string as value for vehicleId
  val invalidData5: Map[String, String] = Map(
    "type" -> "step",
    "vehicleId" -> "",
    "startRoad" -> "south",
    "endRoad" -> "north"
  )
  // empty string as value for type
  val invalidData6: Map[String, String] = Map(
    "type" -> "",
    "vehicleId" -> "vehicle123",
    "startRoad" -> "south",
    "endRoad" -> "north"
  )

  test("Empty string is not a proper value for any field") {
    assert(AddVehicle.fromMap(invalidData4).isEmpty)
    assert(AddVehicle.fromMap(invalidData5).isEmpty)
    assert(AddVehicle.fromMap(invalidData6).isEmpty)

  }

}
