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

      val matchId = api.createMatch()

      verify(mockRepository, times(1)).create()
      assert(matchId === expectedMatch.id)
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
    "should return the match when it exists" in {
      val existingMatch = createMatch()

      when(mockRepository.find(existingMatch.id)).thenReturn(Some(existingMatch))

      val result = api.getMatch(existingMatch.id)

      verify(mockRepository, times(1)).find(existingMatch.id)
      assert(result === Some(existingMatch))
    }
  }
}
