package exercises

trait Fruit {
  def stringify(): String

  def eatenBy(value: String) = s"$value ate $stringify"
}
