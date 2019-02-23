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

  implicit class PersonMap(person: Person) {
    def toMap(): Map[String, String] =
      Map(("name" -> person.name), ("age" -> person.age.toString))
  }

  def isFake(person: Person): Boolean =
    person match {
      case Person("foo", _)            => true
      case Person("bar", _)            => true
      case Person(_, age) if (age < 0) => true
      case _                           => false
    }
}
