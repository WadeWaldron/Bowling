package bowling.api

import org.scalatest.FreeSpec
import bowling.domain.DomainHelpers
import bowling.TestInjector

class MatchAPIFunctionalTest extends FreeSpec with DomainHelpers {
  val injector = new TestInjector()
  val matchAPI = injector.matchAPI

  "createMatch" - {
    "should create a new match." in pendingUntilFixed {
      val newMatch = matchAPI.createMatch()

      assert(matchAPI.getMatch(newMatch.id) === Some(newMatch))
    }
  }

  "getMatch" - {
    "should return the match if it exists." in pendingUntilFixed {
      val newMatch = matchAPI.createMatch()

      assert(matchAPI.getMatch(newMatch.id) === Some(newMatch))
    }
  }

  "addPlayer" - {
    "should add a player to the score card for the lane." in pendingUntilFixed {
      val newMatch = matchAPI.createMatch()
      val playerName = createPlayerName()

      val playerId = matchAPI.addPlayer(newMatch.id, playerName)

      assert(matchAPI.getPlayers(newMatch.id).map(_.name).contains(playerName))
      assert(matchAPI.getPlayers(newMatch.id).map(_.id).contains(playerId))
    }
  }

  "getPlayers" - {
    "should return a set of all players." in pendingUntilFixed {
      val newMatch = matchAPI.createMatch()
      val playerNames = (1 to 5).map(_ => createPlayerName()).toSet

      playerNames.foreach(name => matchAPI.addPlayer(newMatch.id, name))

      val players = matchAPI.getPlayers(newMatch.id)

      assert(players.map(_.name) === playerNames)
    }
  }
}
