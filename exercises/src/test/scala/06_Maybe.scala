package exercises

import minitest._

/*
 * Functions can't always return a value.
 * In this scenario they are called: partial functions.
 * We can convert them into total functions
 * with the introduction of effects.
 *
 *  f:  InType => Effect[OutType]
 */

object MaybeTests extends SimpleTestSuite {

  /*
   * TODO: remove all nulls
   */

  sealed trait Maybe[+A]
  case class Just[A](value: A) extends Maybe[A]
  object None                  extends Maybe[Nothing]

  case class Qty(value: Int)

  def toQty(value: String): Maybe[Qty] =
    if (value.matches("^[0-9]+$")) Just(Qty(value.toInt))
    else None

  test("valid qty") {
    assertEquals(toQty("100"), Just(Qty(100)))
  }

  test("invalid qty") {
    assertEquals(toQty("asd"), None)
    assertEquals(toQty("1 0 0"), None)
    assertEquals(toQty(""), None)
    assertEquals(toQty("-10"), None)
  }
}
