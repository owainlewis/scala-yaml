package io.forward.yaml

import org.scalatest._

class ASTSpec extends WordSpec with Matchers {

  "A YValue" should {

    "have an isNull method" in {
      YString("HELLO").isNull shouldBe false
      YNull.isNull shouldBe true
    }

    "have an isNumeric method" in {
      YInt(1).isNumeric shouldBe true
      YBigInt(1).isNumeric shouldBe true
      YLong(1).isNumeric shouldBe true
      YDouble(1).isNumeric shouldBe true
      YString("HELLO").isNumeric shouldBe false
    }
  }
}