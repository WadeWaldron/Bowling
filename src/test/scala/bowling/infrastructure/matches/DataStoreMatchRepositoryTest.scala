package bowling.infrastructure.matches

import bowling.domain.Match
import org.scalatest.FreeSpec
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import bowling.infrastructure.InfrastructureHelpers

class DataStoreMatchRepositoryTest extends FreeSpec with InfrastructureHelpers with MockitoSugar {
  val mockIdDataStore = mock[MatchIdDataStore]
  val mockDetailsDataStore = mock[MatchDetailsDataStore]

  val repo = new DataStoreMatchRepository(mockIdDataStore, mockDetailsDataStore)

  "find" - {
    "should return None when no match details exist" in {
      val matchId = createMatchId()

      when(mockDetailsDataStore.find(matchId)).thenReturn(None)

      val result = repo.find(matchId)

      assert(result === None)
    }
    "should return the Match when the details exist" in {
      val matchDetails = createMatchDetails()

      when(mockDetailsDataStore.find(matchDetails.id)).thenReturn(Some(matchDetails))

      val result = repo.find(matchDetails.id)

      assert(result === Some(Match(matchDetails.id, None, Set())))
    }
  }
  "create" - {
    "should create a new match and record new details in the data store" in {
      val matchId = createMatchId()

      when(mockIdDataStore.createId()).thenReturn(matchId)

      val result = repo.create()

      assert(result.id === matchId)
      verify(mockDetailsDataStore, times(1)).save(MatchDetails(matchId, None))
    }
  }
}
