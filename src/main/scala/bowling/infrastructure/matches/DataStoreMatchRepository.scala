package bowling.infrastructure.matches

import bowling.domain.{MatchId, Match, LaneId, MatchRepository}

trait MatchIdFactory {
  def createId(): MatchId
}

class InMemoryMatchIdFactory extends MatchIdFactory {
  def createId(): MatchId = null
}

class DataStoreMatchRepository(matchIdFactory: MatchIdFactory, matchDetailsDataStore: MatchDetailsDataStore) extends MatchRepository {
  def find(matchId: MatchId): Option[Match] = matchDetailsDataStore.find(matchId: MatchId).map(details => Match(details.id, None, Set()))
  def create(): Match = {
    val matchDetails = new MatchDetails(matchIdFactory.createId(), None)

    matchDetailsDataStore.save(matchDetails)

    Match(matchDetails.id, None, Set())
  }
}
