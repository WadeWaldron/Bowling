package bowling.infrastructure

import bowling.domain._
import matches.MatchDetails
import matches.MatchDetails
import players.PlayerDetails
import players.PlayerDetails
import bowling.domain.PlayerId
import bowling.domain.MatchId
import bowling.domain.LaneId

trait InfrastructureHelpers extends DomainHelpers {
  def createMatchDetails(id: MatchId = createMatchId(), lane: Option[LaneId] = None, players: Set[PlayerId] = Set()) = MatchDetails(id, lane, players)
  def createPlayerDetails(id: PlayerId = createPlayerId(), name: PlayerName = createPlayerName()) = PlayerDetails(id, name)
}
