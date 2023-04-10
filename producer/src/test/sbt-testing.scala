package com.rockthejvm
import org.scalatest.funsuite.AnyFunSuite
class SimpleTest extends AnyFunSuite {
  test("compare 2 strings ignoring case") {
    val calculatedString = "ROCKtheJVM"
    val expectedString = "rockthejvm"
    assert(calculatedString.toLowerCase == expectedString)
  }
}


