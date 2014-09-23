package bowling.infrastructure.players

import org.scalatest.FreeSpec
import bowling.domain.{PlayerName, Player, DomainHelpers}
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import bowling.infrastructure.InfrastructureHelpers

class DataStorePlayerFactoryTest extends FreeSpec with InfrastructureHelpers with MockitoSugar {
  val mockIdDataStore = mock[PlayerIdDataStore]
  val repo = new DataStorePlayerFactory(mockIdDataStore)

  "create" - {
    "should return a new player" in {
      val playerId = createPlayerId()

      when(mockIdDataStore.createId()).thenReturn(playerId)

      val player = repo.create()

      assert(player === Player(playerId, PlayerName("Default")))
    }
  }
}

class PlayerDataStoreTest extends FreeSpec with InfrastructureHelpers with MockitoSugar {
  val mockPlayerDetailsDataStore = mock[PlayerDetailsDataStore]
  val repo = new PlayerDataStore(mockPlayerDetailsDataStore)

  "update" - {
    "should update the player and return it" in {
      val playerDetails = createPlayerDetails()
      val player = createPlayer(playerDetails.id, playerDetails.name)

      when(mockPlayerDetailsDataStore.save(playerDetails)).thenReturn(playerDetails)

      val result = repo.update(player.id, player)

      assert(result === player)
      verify(mockPlayerDetailsDataStore, times(1)).save(playerDetails)
    }
  }
  "find" - {
    "should return None when the player does not exist" in {
      val playerId = createPlayerId()

      when(mockPlayerDetailsDataStore.find(playerId)).thenReturn(None)

      val result = repo.find(playerId)

      assert(result === None)
    }
    "should return the player when they exist" in {
      val details = createPlayerDetails()

      when(mockPlayerDetailsDataStore.find(details.id)).thenReturn(Some(details))

      val result = repo.find(details.id)

      assert(result === Some(Player(details.id, details.name)))
    }
  }
}
