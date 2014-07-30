package bowling.infrastructure.players

import bowling.domain.{Player, PlayerId, PlayerRepository}

class DataStorePlayerRepository extends PlayerRepository {
  def create(): Player = null
  def find(id: PlayerId): Option[Player] = null
  def update(id: PlayerId, value: Player): Player = null
}
