package bowling.infrastructure.matches

import bowling.domain.{InvalidMatchException, PlayerRepository, Match}
import org.scalatest.FreeSpec
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import bowling.infrastructure.InfrastructureHelpers

class DataStoreMatchRepositoryTest extends FreeSpec with InfrastructureHelpers with MockitoSugar {
  val mockPlayerRepository = mock[PlayerRepository]
  val mockIdDataStore = mock[MatchIdDataStore]
  val mockDetailsDataStore = mock[MatchDetailsDataStore]

  val repo = new DataStoreMatchRepository(mockIdDataStore, mockDetailsDataStore, mockPlayerRepository)

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
    "should include the Lane when it is present" in {
      val lane = createLane()
      val matchDetails = createMatchDetails(lane = Some(lane.id))

      when(mockDetailsDataStore.find(matchDetails.id)).thenReturn(Some(matchDetails))

      val result = repo.find(matchDetails.id)

      assert(result === Some(Match(matchDetails.id, Some(lane), Set())))
    }
    "should include the Players when they are present" in {
      val player1 = createPlayer()
      val player2 = createPlayer()
      val matchDetails = createMatchDetails(players = Set(player1.id, player2.id))

      when(mockDetailsDataStore.find(matchDetails.id)).thenReturn(Some(matchDetails))
      when(mockPlayerRepository.find(player1.id)).thenReturn(Some(player1))
      when(mockPlayerRepository.find(player2.id)).thenReturn(Some(player2))

      val result = repo.find(matchDetails.id)

      assert(result === Some(Match(matchDetails.id, None, Set(player1, player2))))
    }
    "should throw an exception if the Player is recorded in the match but does not exist" in {
      val player = createPlayer()
      val matchDetails = createMatchDetails(players = Set(player.id))

      when(mockDetailsDataStore.find(matchDetails.id)).thenReturn(Some(matchDetails))
      when(mockPlayerRepository.find(player.id)).thenReturn(None)

      intercept[InvalidMatchException] {
        repo.find(matchDetails.id)
      }
    }
  }
  "create" - {
    "should create a new match and record new details in the data store" in {
      val matchId = createMatchId()

      when(mockIdDataStore.createId()).thenReturn(matchId)

      val result = repo.create()

      assert(result.id === matchId)
      verify(mockDetailsDataStore, times(1)).save(MatchDetails(matchId, None, Set()))
    }
  }
  "update" - {
    "should update the details of the match and return the match" in {
      val updatedMatch = createMatch()
      val expectedDetails = MatchDetails(updatedMatch.id, updatedMatch.lane.map(_.id), updatedMatch.players.map(_.id))

      when(mockDetailsDataStore.save(expectedDetails)).thenReturn(expectedDetails)

      val result = repo.update(updatedMatch.id, updatedMatch)

      assert(result === updatedMatch)
      verify(mockDetailsDataStore, times(1)).save(expectedDetails)
    }
  }
}
