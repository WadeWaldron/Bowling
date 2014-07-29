package bowling.infrastructure.matches

import bowling.domain.{LaneId, MatchId}

case class MatchDetails(id: MatchId, lane: Option[LaneId])

trait MatchDetailsDataStore {
  def find(id: MatchId): Option[MatchDetails]
  def save(details: MatchDetails):MatchDetails
}

class InMemoryMatchDetailsDataStore extends MatchDetailsDataStore {
  def find(id: MatchId): Option[MatchDetails] = null
  def save(details: MatchDetails):MatchDetails = null
}