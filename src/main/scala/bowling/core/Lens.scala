package bowling.core

case class Lens[A,B](get: A => B, set: (A,B) => A) {
  def apply(a: A) = get(a)
}