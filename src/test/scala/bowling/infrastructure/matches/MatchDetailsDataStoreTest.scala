package bowling.infrastructure.matches

import org.scalatest.FreeSpec
import bowling.infrastructure.InfrastructureHelpers
import bowling.TestInjector

class MatchDetailsDataStoreTest extends FreeSpec with InfrastructureHelpers {
  val injector = new TestInjector

  val dataStore = injector.matchDetailsDataStore

  "find" - {
    "should return None if no match details exist" in {
      val result = dataStore.find(createMatchId())
      assert(result === None)
    }
    "should return the Match Details if they exist" in {
      val details = dataStore.save(createMatchDetails())

      val result = dataStore.find(details.id)

      assert(result === Some(details))
    }
  }

  "save" - {
    "should save the match details to the data store and return them." in {
      val details = dataStore.save(createMatchDetails())
      assert(dataStore.find(details.id) === Some(details))
    }
    "should overwrite details with the same id" in {
      val originalDetails = dataStore.save(createMatchDetails())
      val updatedDetails = dataStore.save(originalDetails.copy(lane = Some(createLaneId())))

      assert(dataStore.find(originalDetails.id) === Some(updatedDetails))
    }
    "should not overwrite details with a different id" in {
      val details1 = dataStore.save(createMatchDetails())
      val details2 = dataStore.save(createMatchDetails())

      assert(dataStore.find(details1.id) === Some(details1))
      assert(dataStore.find(details2.id) === Some(details2))
    }
  }
}
