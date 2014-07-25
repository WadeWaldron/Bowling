package bowling.domain

trait ScoreCardRepository {
  def find(laneId: LaneId): ScoreCard
}
