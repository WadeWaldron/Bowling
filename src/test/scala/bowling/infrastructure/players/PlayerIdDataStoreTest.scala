package bowling.infrastructure.players

import org.scalatest.FreeSpec
import bowling.TestInjector

class PlayerIdDataStoreTest extends FreeSpec {
  val injector = new TestInjector
  val dataStore = injector.playerIdDataStore

  "create" - {
    "should return a new Id" in {
      val id = dataStore.createId()

      assert(id != null)
    }
    "should always return a unique Id" in {
      val idsToGenerate = 100
      val idSet = (1 to idsToGenerate).map(_ => dataStore.createId()).toSet
      assert(idSet.size === idsToGenerate)
    }
  }
}
