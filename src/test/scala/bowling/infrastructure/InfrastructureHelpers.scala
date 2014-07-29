package bowling.infrastructure

import bowling.domain.{LaneId, MatchId, DomainHelpers}
import matches.MatchDetails

trait InfrastructureHelpers extends DomainHelpers {
  def createMatchDetails(id: MatchId = createMatchId(), lane: Option[LaneId] = None) = MatchDetails(id, lane)
}
