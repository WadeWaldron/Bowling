package bowling.infrastructure.players

import bowling.infrastructure.InfrastructureHelpers
import org.scalatest.FreeSpec
import bowling.TestInjector

class PlayerDetailsDataStoreTest extends FreeSpec with InfrastructureHelpers {
  val injector = new TestInjector()
  val dataStore = injector.playerDetailsDataStore

  "find" - {
    "should return None when no PlayerDetails exist" in {
      val result = dataStore.find(createPlayerId())

      assert(result === None)
    }
    "should return the PlayerDetails when they exist" in {
      val playerDetails = dataStore.save(createPlayerDetails())

      val result = dataStore.find(playerDetails.id)

      assert(result === Some(playerDetails))
    }
  }
  "save" - {
    "should save the PlayerDetails and return them" in {
      val playerDetails = createPlayerDetails()

      val result = dataStore.save(playerDetails)

      assert(result === playerDetails)
      assert(dataStore.find(playerDetails.id) === Some(playerDetails))
    }
    "should overwrite PlayerDetails with the same Id" in {
      val playerDetails1 = createPlayerDetails()
      val playerDetails2 = playerDetails1.copy(name = createPlayerName())

      dataStore.save(playerDetails1)
      dataStore.save(playerDetails2)

      assert(dataStore.find(playerDetails1.id) === Some(playerDetails2))
    }
    "should not overwrite PlayerDetails with a different Id" in {
      val playerDetails1 = createPlayerDetails()
      val playerDetails2 = createPlayerDetails()

      dataStore.save(playerDetails1)
      dataStore.save(playerDetails2)

      assert(dataStore.find(playerDetails1.id) === Some(playerDetails1))
      assert(dataStore.find(playerDetails2.id) === Some(playerDetails2))
    }
  }
}
