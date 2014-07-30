package bowling.domain

import org.scalatest.FreeSpec

class MatchTest extends FreeSpec with DomainHelpers {
  "addPlayer" - {
    "should return a copy of the Match with the player added" in {
      val oldMatch = createMatch()
      val player = createPlayer()

      val updatedMatch = oldMatch.addPlayer(player)

      assert(updatedMatch != oldMatch)
      assert(updatedMatch.players === Set(player))
    }
  }
}
