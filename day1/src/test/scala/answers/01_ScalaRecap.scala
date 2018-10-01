package day1.answers

import minitest._

/*
 * Our most used Scala features:
 * - Case class
 * - Companion Object
 * - Apply function
 * - Type parameter
 * - Trait as interface/mixin
 * - Implicit parameter
 * - Extension class
 *
 */

object ScalaRecap extends SimpleTestSuite {

  /*
   * TODO: one test at a time,
   *       read `ignore()` description
   *       uncomment the code,
   *       and add the code to get a green test
   *
   * ADD YOUR CODE HERE INSIDE THE OBJECT
   */

  trait Fruit {
    def eatenBy(name: String): String =
      s"${name} ate ${stringify}"

    def stringify: String
  }
  case class Apple() extends Fruit {
    def stringify: String = "an apple"
  }
  case class Banana() extends Fruit {
    def stringify: String = "a banana"
  }

  case class Person(name: String, age: Int) {
    def apply(suffix: String): String =
      s"${suffix} mi chiamo ${name}!"

    def eat[A <: Fruit](what: A): String =
      what.eatenBy(name)

    def makeYounger(implicit value: Int): Person =
      copy(age = age - value)
  }
  object Person {
    def apply(value: String): Person =
      create(value)

    def create(value: String): Person = {
      val tokens = value.split(",")
      Person(tokens(0).trim, tokens(1).trim.toInt)
    }
  }

  implicit class PersonOps(p: Person) {
    def toMap(): Map[String, String] = {
      val m0 = Map.empty[String, String]
      val m1 = m0 + ("name" -> p.name)
      val m2 = m1 + ("age" -> p.age.toString)
      m2
    }
  }

  test("define case class") {
    val result = Person("foo", 56)
    assertEquals(result, Person("foo", 56))
  }

  test("define the case class's companion object") {
    val result = Person.create("foo, 56")
    assertEquals(result, Person("foo", 56))
  }

  test("case class apply") {
    val result = Person("foo", 56)("Ciao,")
    assertEquals(result, "Ciao, mi chiamo foo!")
  }

  test("companion object apply") {
    val result = Person("foo, 56")("Ciao,")
    assertEquals(result, "Ciao, mi chiamo foo!")
  }

  test("case class update") {
    val p      = Person("foo", 56)
    val result = p.copy(age = p.age + 100)
    assertEquals(result.age, 156)
  }

  test("trait as interface") {
    assert(Apple().isInstanceOf[Fruit])
    assert(Banana().isInstanceOf[Fruit])

    assertEquals(Apple().stringify, "an apple")
    assertEquals(Banana().stringify, "a banana")
  }

  test("trait as mixin") {
    assertEquals(Apple().eatenBy("foo"), "foo ate an apple")
    assertEquals(Banana().eatenBy("bar"), "bar ate a banana")
  }

  test("type parameter") {
    val p      = Person("foo", 56)
    val result = p.eat(Apple())
    assertEquals(result, "foo ate an apple")
  }

  test("implicit parameter") {
    implicit val years = 30
    val p              = Person("foo", 56)
    val result         = p.makeYounger
    assertEquals(result.age, 26)
  }

  test("extension class") {
    val p      = Person("foo", 56)
    val result = p.toMap
    assertEquals(result, Map("name" -> "foo", "age" -> "56"))
  }
}