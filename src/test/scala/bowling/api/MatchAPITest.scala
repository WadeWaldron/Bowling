package bowling.api

import bowling.domain.{MatchRepository, DomainHelpers}
import org.scalatest.FreeSpec
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar

class MatchAPITest extends FreeSpec with DomainHelpers with MockitoSugar {
  val mockRepository = mock[MatchRepository]
  val api = new MatchAPI(mockRepository)

  "createMatch" - {
    "should create a match and return it" in {
      val expectedMatch = createMatch()

      when(mockRepository.create()).thenReturn(expectedMatch)

      val newMatch = api.createMatch()

      verify(mockRepository, times(1)).create()
      assert(newMatch === expectedMatch)
    }
  }

  "getMatch" - {
    "should return None when no match exists" in {
      val matchId = createMatchId()

      when(mockRepository.find(matchId)).thenReturn(None)

      val result = api.getMatch(matchId)

      verify(mockRepository, times(1)).find(matchId)
      assert(result === None)
    }
  }
}
