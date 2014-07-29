package bowling.infrastructure

import bowling.core.Module
import matches._
import bowling.domain.{MatchId, MatchRepository}

trait InfrastructureModule extends Module {
  private lazy val matchIdFactory: MatchIdFactory = new InMemoryMatchIdFactory
  private lazy val matchDetailsDataStore: MatchDetailsDataStore = new InMemoryMatchDetailsDataStore
  lazy val matchRepository:MatchRepository = new DataStoreMatchRepository(matchIdFactory, matchDetailsDataStore)
}
