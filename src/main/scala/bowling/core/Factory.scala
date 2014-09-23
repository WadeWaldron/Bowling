package bowling.core

trait Factory[T] {
  def create():T
}
