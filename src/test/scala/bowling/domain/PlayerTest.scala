package bowling.domain

import org.scalatest.FreeSpec

class PlayerTest extends FreeSpec with DomainHelpers {
  "setName" - {
    "should return a copy of the player with the new name" in {
      val oldPlayer = createPlayer()
      val newName = createPlayerName()
      val updatedPlayer = oldPlayer.setName(newName)

      assert(updatedPlayer != oldPlayer)
      assert(updatedPlayer.name === newName)
    }
  }
}
