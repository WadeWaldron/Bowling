package bowling.api

import bowling.domain._
import org.scalatest.FreeSpec
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import bowling.domain.Match
import scala.Some

class MatchAPITest extends FreeSpec with DomainHelpers with MockitoSugar {
  val mockMatchRepository = mock[MatchRepository]
  val mockPlayerRepository = mock[PlayerRepository]
  val api = new MatchAPI(mockMatchRepository, mockPlayerRepository)

  "createMatch" - {
    "should create a match and return it" in {
      val expectedMatch = createMatch()

      when(mockMatchRepository.create()).thenReturn(expectedMatch)

      val matchId = api.createMatch()

      verify(mockMatchRepository, times(1)).create()
      assert(matchId === expectedMatch.id)
    }
  }

  "getMatch" - {
    "should return None when no match exists" in {
      val matchId = createMatchId()

      when(mockMatchRepository.find(matchId)).thenReturn(None)

      val result = api.getMatch(matchId)

      verify(mockMatchRepository, times(1)).find(matchId)
      assert(result === None)
    }
    "should return the match when it exists" in {
      val existingMatch = createMatch()

      when(mockMatchRepository.find(existingMatch.id)).thenReturn(Some(existingMatch))

      val result = api.getMatch(existingMatch.id)

      verify(mockMatchRepository, times(1)).find(existingMatch.id)
      assert(result === Some(existingMatch))
    }
  }

  "addPlayer" - {
    "should add throw an exception if the match does not exist." in {
      val matchId = createMatchId()

      when(mockMatchRepository.find(matchId)).thenReturn(None)

      intercept[InvalidMatchException] {
        api.addPlayer(matchId, createPlayerName())
      }
    }
    "should add a new player to the match" in {
      val playerName = createPlayerName()
      val existingMatch = createMatch()
      val player = createPlayer().setName(playerName)

      when(mockPlayerRepository.create()).thenReturn(player)
      when(mockMatchRepository.find(existingMatch.id)).thenReturn(Some(existingMatch))

      val playerId = api.addPlayer(existingMatch.id, playerName)

      assert(playerId === player.id)
      verify(mockMatchRepository, times(1)).update(existingMatch.id, existingMatch.addPlayer(player))
    }
  }
}
