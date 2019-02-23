package exercises

case class Person(name: String, age: Int) {
  def apply (prefix: String) = s"$prefix mi chiamo $name!"
}

object Person {
  def create(string: String): Person = {
    val res = string.split(",\\s+")
    Person(res(0), res(1).toInt)
  }
}
