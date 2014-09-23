package bowling.infrastructure.matches

import bowling.domain._
import bowling.domain.Match
import bowling.domain.MatchId
import bowling.infrastructure.players.PlayerDataStore

class DataStoreMatchRepository(matchIdFactory: MatchIdDataStore, matchDetailsDataStore: MatchDetailsDataStore, playerDatastore: PlayerDataStore) extends MatchRepository {
  def find(matchId: MatchId): Option[Match] = {

    matchDetailsDataStore.find(matchId: MatchId).map(details => {
      val players = details.players.map(p => playerDatastore.find(p).getOrElse(throw new InvalidMatchException("Invalid Player For Match: "+matchId+" -> "+p)))
      val lane = details.lane.map(id => Lane(id))
      Match(details.id, lane, players)
    })
  }

  def create(): Match = {
    val matchDetails = new MatchDetails(matchIdFactory.createId(), None, Set())

    matchDetailsDataStore.save(matchDetails)

    Match(matchDetails.id, None, Set())
  }

  def update(id: MatchId, value: Match): Match = {
    val matchDetails = new MatchDetails(id, value.lane.map(_.id), value.players.map(_.id))

    value.players.foreach(p => playerDatastore.update(p.id, p))
    matchDetailsDataStore.save(matchDetails)

    value
  }
}
