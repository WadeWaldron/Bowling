package bowling.infrastructure.players

import bowling.domain.PlayerId

trait PlayerIdDataStore {
  def createId(): PlayerId
}

class InMemoryPlayerIdDataStore extends PlayerIdDataStore {
  private var currentId = 0

  def createId(): PlayerId = {
    synchronized {
      currentId = currentId + 1
    }

    PlayerId(currentId)
  }
}
