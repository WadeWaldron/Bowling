package bowling.infrastructure

import bowling.core.Module
import scorecards.DefaultMatchRepository
import bowling.domain.MatchRepository

trait InfrastructureModule extends Module {
  lazy val matchRepository:MatchRepository = new DefaultMatchRepository
}
