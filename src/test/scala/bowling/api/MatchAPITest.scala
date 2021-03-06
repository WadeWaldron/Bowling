package bowling.api

import bowling.domain._
import org.scalatest.FreeSpec
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import bowling.domain.Match
import scala.Some

class MatchAPITest extends FreeSpec with DomainHelpers with MockitoSugar {
  val mockMatchRepository = mock[MatchRepository]
  val mockPlayerFactory = mock[PlayerFactory]
  val api = new MatchAPI(mockMatchRepository, mockPlayerFactory)

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
    "should add a new player to the match and save the player" in {
      val playerName = createPlayerName()
      val existingMatch = createMatch()
      val player = createPlayer().setName(playerName)

      when(mockPlayerFactory.create()).thenReturn(player)
      when(mockMatchRepository.find(existingMatch.id)).thenReturn(Some(existingMatch))

      val playerId = api.addPlayer(existingMatch.id, playerName)

      assert(playerId === player.id)

      verify(mockMatchRepository, times(1)).update(existingMatch.id, existingMatch.addPlayer(player))
    }
  }

  "getPlayers" - {
    "should throw an exception if the match does not exist" in {
      val matchId = createMatchId()

      when(mockMatchRepository.find(matchId)).thenReturn(None)

      intercept[InvalidMatchException] {
        api.getPlayers(matchId)
      }
    }
    "should return an empty set if no players have been assigned to the Match" in {
      val existingMatch = createMatch(players = Set())

      when(mockMatchRepository.find(existingMatch.id)).thenReturn(Some(existingMatch))

      val result = api.getPlayers(existingMatch.id)

      assert(result === Set())
    }
    "should return all players who have been added to the match" in {
      val player1 = createPlayer()
      val player2 = createPlayer()
      val existingMatch = createMatch(players = Set(player1, player2))

      when(mockMatchRepository.find(existingMatch.id)).thenReturn(Some(existingMatch))

      val result = api.getPlayers(existingMatch.id)

      assert(result === Set(player1, player2))
    }
  }
}
