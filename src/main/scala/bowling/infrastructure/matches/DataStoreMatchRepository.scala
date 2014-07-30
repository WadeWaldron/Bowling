package bowling.infrastructure.matches

import bowling.domain.{MatchId, Match, LaneId, MatchRepository}

class DataStoreMatchRepository(matchIdFactory: MatchIdDataStore, matchDetailsDataStore: MatchDetailsDataStore) extends MatchRepository {
  def find(matchId: MatchId): Option[Match] = matchDetailsDataStore.find(matchId: MatchId).map(details => Match(details.id, None, Set()))
  def create(): Match = {
    val matchDetails = new MatchDetails(matchIdFactory.createId(), None)

    matchDetailsDataStore.save(matchDetails)

    Match(matchDetails.id, None, Set())
  }

  def update(id: MatchId, value: Match): Match = null
}
