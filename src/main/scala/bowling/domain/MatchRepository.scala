package bowling.domain

trait MatchRepository {
  def find(laneId: LaneId): Match
}
