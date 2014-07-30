package bowling.infrastructure.players

import bowling.domain.{PlayerName, PlayerId}

case class PlayerDetails(id: PlayerId, name: PlayerName)

trait PlayerDetailsDataStore {
  def find(playerId: PlayerId):Option[PlayerDetails]
  def save(details: PlayerDetails):PlayerDetails
}
