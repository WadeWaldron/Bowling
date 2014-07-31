package bowling.infrastructure.players

import bowling.domain._
import bowling.domain.Player
import bowling.domain.PlayerId

class DataStorePlayerRepository(playerIdDataStore: PlayerIdDataStore, playerDetailsDataStore: PlayerDetailsDataStore) extends PlayerRepository {
  def create(): Player = {
    val id = playerIdDataStore.createId()
    Player(id, PlayerName("Default"))
  }
  def find(id: PlayerId): Option[Player] = null
  def update(id: PlayerId, value: Player): Player = null
}
