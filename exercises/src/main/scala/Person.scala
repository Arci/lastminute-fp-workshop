package exercises

case class Person(name: String, age: Int) {
  def apply(prefix: String) = s"$prefix mi chiamo $name!"

  def eat(fruit: Fruit) = fruit.eatenBy(name)

  def makeYounger(implicit years: Int): Person = copy(name, age - years)
}

object Person {
  def create(value: String): Person = {
    val tokens = value.split(",\\s+")
    Person(tokens(0), tokens(1).toInt)
  }

  def apply(value: String) = create(value)
}
