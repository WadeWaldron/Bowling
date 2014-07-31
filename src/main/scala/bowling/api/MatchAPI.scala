package bowling.api

import bowling.domain._
import bowling.domain.Player
import bowling.domain.PlayerName
import bowling.domain.PlayerId
import bowling.domain.LaneId

class MatchAPI(matchRepository: MatchRepository, playerRepository: PlayerRepository) {
  def createMatch():MatchId = matchRepository.create().id
  def getMatch(matchId: MatchId):Option[Match] = matchRepository.find(matchId)

  def addPlayer(matchId: MatchId, playerName: PlayerName):PlayerId = {
    matchRepository.find(matchId) match {
      case Some(existingMatch) => {
        val player = playerRepository.create().setName(playerName)
        playerRepository.update(player.id, player)
        matchRepository.update(matchId, existingMatch.addPlayer(player))
        player.id
      }
      case None => throw new InvalidMatchException("The requested match does not exist: "+matchId)
    }
  }

  def getPlayers(matchId: MatchId):Set[Player] = {
    getMatch(matchId) match {
      case Some(existingMatch) => existingMatch.players
      case None => throw new InvalidMatchException("The requested match does not exist: "+matchId)
    }
  }
}
