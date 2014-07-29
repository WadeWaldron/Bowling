package bowling.infrastructure.scorecards

import bowling.domain.{Match, LaneId, MatchRepository}

class DefaultMatchRepository extends MatchRepository {
  def find(laneId: LaneId): Match = ???
}
