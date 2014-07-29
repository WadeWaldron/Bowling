package bowling.api

import bowling.domain._
import bowling.domain.Player
import bowling.domain.PlayerName
import bowling.domain.PlayerId
import bowling.domain.LaneId

class MatchAPI(matchRepository: MatchRepository) {
  def createMatch():MatchId = matchRepository.create().id
  def getMatch(matchId: MatchId):Option[Match] = matchRepository.find(matchId)

  def setLane(matchId: MatchId, laneId: LaneId) {

  }

  def addPlayer(matchId: MatchId, playerName: PlayerName):PlayerId = {
    null
  }

  def getPlayers(matchId: MatchId):Set[Player] = {
    null
  }
}
