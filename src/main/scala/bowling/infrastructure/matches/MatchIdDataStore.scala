package bowling.infrastructure.matches

import bowling.domain.MatchId

trait MatchIdDataStore {
  def createId(): MatchId
}

class InMemoryMatchIdDataStore extends MatchIdDataStore {
  private var currentId = 0

  def createId(): MatchId = {
    synchronized {
      currentId = currentId + 1
    }

    MatchId(currentId)
  }
}
