package bowling.infrastructure.players

import bowling.domain.{PlayerName, PlayerId}
import collection.mutable

case class PlayerDetails(id: PlayerId, name: PlayerName)

trait PlayerDetailsDataStore {
  def find(playerId: PlayerId):Option[PlayerDetails]
  def save(details: PlayerDetails):PlayerDetails
}

class InMemoryPlayerDetailsDataStore extends PlayerDetailsDataStore {
  private val data = new mutable.HashMap[PlayerId, PlayerDetails] with mutable.SynchronizedMap[PlayerId, PlayerDetails]

  def find(playerId: PlayerId): Option[PlayerDetails] = data.get(playerId)

  def save(details: PlayerDetails): PlayerDetails = {
    data.put(details.id, details)
    details
  }
}
