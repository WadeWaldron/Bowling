package bowling.infrastructure.matches

import bowling.domain.MatchId

trait MatchIdDataStore {
  def createId(): MatchId
}

class InMemoryMatchIdDataStore extends MatchIdDataStore {
  def createId(): MatchId = null
}
