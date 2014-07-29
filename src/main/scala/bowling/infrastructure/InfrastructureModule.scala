package bowling.infrastructure

import bowling.core.Module
import matches._
import bowling.domain.{MatchId, MatchRepository}

trait InfrastructureModule extends Module {
  private lazy val matchIdDataStore: MatchIdDataStore = new InMemoryMatchIdDataStore
  private lazy val matchDetailsDataStore: MatchDetailsDataStore = new InMemoryMatchDetailsDataStore
  lazy val matchRepository:MatchRepository = new DataStoreMatchRepository(matchIdDataStore, matchDetailsDataStore)
}
