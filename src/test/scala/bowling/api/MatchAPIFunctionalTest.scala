package bowling.api

import org.scalatest.FreeSpec
import bowling.domain.DomainHelpers
import bowling.TestInjector

class MatchAPIFunctionalTest extends FreeSpec with DomainHelpers {
  val injector = new TestInjector()
  val matchAPI = injector.matchAPI

  "addPlayer" - {
    "should add a player to the score card for the lane." in pendingUntilFixed {
      val laneId = createLaneId()
      val playerName = createPlayerName()

      val playerId = matchAPI.addPlayer(laneId, playerName)

      assert(matchAPI.getPlayers(laneId).map(_.name).contains(playerName))
      assert(matchAPI.getPlayers(laneId).map(_.id).contains(playerId))
    }
  }

  "getPlayers" - {
    "should return a set of all players." in pendingUntilFixed {
      val laneId = createLaneId()
      val playerNames = (1 to 5).map(_ => createPlayerName()).toSet

      playerNames.foreach(name => matchAPI.addPlayer(laneId, name))

      val players = matchAPI.getPlayers(laneId)

      assert(players.map(_.name) === playerNames)
    }
  }
}
