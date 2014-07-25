package bowling.api

import org.scalatest.FreeSpec
import bowling.domain.DomainHelpers

class ScoreCardAPIFunctionalTest extends FreeSpec with DomainHelpers {
  val scoreCardAPI = new ScoreCardAPI

  "addPlayer" - {
    "should add a player to the score card for the lane." in pendingUntilFixed {
      val laneId = createLaneId()
      val playerName = createPlayerName()

      val playerId = scoreCardAPI.addPlayer(laneId, playerName)

      assert(scoreCardAPI.getPlayers(laneId).map(_.name).contains(playerName))
      assert(scoreCardAPI.getPlayers(laneId).map(_.id).contains(playerId))
    }
  }

  "getPlayers" - {
    "should return a set of all players." in pendingUntilFixed {
      val laneId = createLaneId()
      val playerNames = (1 to 5).map(_ => createPlayerName()).toSet

      playerNames.foreach(name => scoreCardAPI.addPlayer(laneId, name))

      val players = scoreCardAPI.getPlayers(laneId)

      assert(players.map(_.name) === playerNames)
    }
  }
}
