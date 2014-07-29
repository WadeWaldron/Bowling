package bowling.infrastructure.scorecards

import bowling.domain.{MatchId, Match, LaneId, MatchRepository}

class DefaultMatchRepository extends MatchRepository {
  def find(matchId: MatchId): Option[Match] = null
  def create(): Match = null
}
