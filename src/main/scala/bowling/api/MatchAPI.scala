package bowling.api

import bowling.domain._
import bowling.domain.Player
import bowling.domain.PlayerName
import bowling.domain.PlayerId
import bowling.domain.LaneId

case class InvalidMatchException(matchId: MatchId) extends IllegalArgumentException("The requested match does not exist: "+matchId)

class MatchAPI(matchRepository: MatchRepository, playerRepository: PlayerRepository) {
  def createMatch():MatchId = matchRepository.create().id
  def getMatch(matchId: MatchId):Option[Match] = matchRepository.find(matchId)

  def addPlayer(matchId: MatchId, playerName: PlayerName) = {
    matchRepository.find(matchId) match {
      case Some(existingMatch) => {
        val player = playerRepository.create().setName(playerName)
        matchRepository.update(matchId, existingMatch.addPlayer(player))
      }
      case None => throw new InvalidMatchException(matchId)
    }
  }

  def getPlayers(matchId: MatchId):Set[Player] = {
    null
  }

  def setLane(matchId: MatchId, laneId: LaneId) {

  }
}
