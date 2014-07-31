package bowling.infrastructure.players

import org.scalatest.FreeSpec
import bowling.domain.{PlayerName, Player, DomainHelpers}
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._

class DataStorePlayerRepositoryTest extends FreeSpec with DomainHelpers with MockitoSugar {
  val mockIdDataStore = mock[PlayerIdDataStore]
  val mockPlayerDetailsDataStore = mock[PlayerDetailsDataStore]
  val repo = new DataStorePlayerRepository(mockIdDataStore, mockPlayerDetailsDataStore)

  "create" - {
    "should return a new player" in {
      val playerId = createPlayerId()

      when(mockIdDataStore.createId()).thenReturn(playerId)

      val player = repo.create()

      assert(player === Player(playerId, PlayerName("Default")))
    }
  }
}
