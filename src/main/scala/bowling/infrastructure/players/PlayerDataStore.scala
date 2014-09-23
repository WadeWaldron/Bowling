package bowling.infrastructure.players

import bowling.domain._
import bowling.domain.Player
import bowling.domain.PlayerId

class DataStorePlayerFactory(playerIdDataStore: PlayerIdDataStore) extends PlayerFactory {
  def create(): Player = {
    val id = playerIdDataStore.createId()
    Player(id, PlayerName("Default"))
  }
}

class PlayerDataStore(playerDetailsDataStore: PlayerDetailsDataStore) {
  def find(id: PlayerId): Option[Player] = playerDetailsDataStore.find(id).map(details => Player(details.id, details.name))

  def update(id: PlayerId, value: Player): Player = {
    val details = PlayerDetails(id, value.name)
    playerDetailsDataStore.save(details)
    value
  }
}
