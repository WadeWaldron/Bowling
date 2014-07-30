package bowling.api

import org.scalatest.FreeSpec
import bowling.domain.DomainHelpers
import bowling.TestInjector

class MatchAPIFunctionalTest extends FreeSpec with DomainHelpers {
  val injector = new TestInjector()
  val matchAPI = injector.matchAPI

  "createMatch" - {
    "should create a new match." in {
      val matchId = matchAPI.createMatch()

      assert(matchAPI.getMatch(matchId).get.id === matchId)
    }
  }

  "getMatch" - {
    "should return the match if it exists." in {
      val matchId = matchAPI.createMatch()

      assert(matchAPI.getMatch(matchId).get.id === matchId)
    }
  }

  "addPlayer" - {
    "should add a player to the score card for the lane." in pendingUntilFixed {
      val matchId = matchAPI.createMatch()
      val playerName = createPlayerName()

      val playerId = matchAPI.addPlayer(matchId, playerName)

      assert(matchAPI.getPlayers(matchId).map(_.name).contains(playerName))
      assert(matchAPI.getPlayers(matchId).map(_.id).contains(playerId))
    }
  }

  "getPlayers" - {
    "should return a set of all players." in pendingUntilFixed {
      val matchId = matchAPI.createMatch()
      val playerNames = (1 to 5).map(_ => createPlayerName()).toSet

      playerNames.foreach(name => matchAPI.addPlayer(matchId, name))

      val players = matchAPI.getPlayers(matchId)

      assert(players.map(_.name) === playerNames)
    }
  }
}
