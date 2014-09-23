package bowling.core

trait Repository[K,V] {
  def find(id: K):Option[V]
  def update(id: K, value: V):V
}
