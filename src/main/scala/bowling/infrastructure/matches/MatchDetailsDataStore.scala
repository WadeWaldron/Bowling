package bowling.infrastructure.matches

import bowling.domain.{LaneId, MatchId}
import collection.mutable

case class MatchDetails(id: MatchId, lane: Option[LaneId])

trait MatchDetailsDataStore {
  def find(id: MatchId): Option[MatchDetails]
  def save(details: MatchDetails):MatchDetails
}

class InMemoryMatchDetailsDataStore extends MatchDetailsDataStore {
  private val data = new mutable.HashMap[MatchId, MatchDetails]() with mutable.SynchronizedMap[MatchId, MatchDetails]

  def find(id: MatchId): Option[MatchDetails] = data.get(id)
  def save(details: MatchDetails):MatchDetails = {
    data.put(details.id, details)
    details
  }
}