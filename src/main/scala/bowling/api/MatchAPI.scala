package bowling.api

import bowling.domain._
import bowling.domain.Player
import bowling.domain.PlayerName
import bowling.domain.PlayerId
import bowling.domain.LaneId

class MatchAPI(matchRepository: MatchRepository) {
  def addPlayer(lane: LaneId, playerName: PlayerName):PlayerId = {
    null
  }

  def getPlayers(lane: LaneId):Set[Player] = {
    null
  }
}
