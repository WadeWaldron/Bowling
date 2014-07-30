package bowling.infrastructure

import bowling.core.Module
import matches._
import bowling.domain.{PlayerRepository, MatchId, MatchRepository}
import players.DataStorePlayerRepository

trait InfrastructureModule extends Module {
  lazy val matchIdDataStore: MatchIdDataStore = new InMemoryMatchIdDataStore
  lazy val matchDetailsDataStore: MatchDetailsDataStore = new InMemoryMatchDetailsDataStore
  lazy val matchRepository:MatchRepository = new DataStoreMatchRepository(matchIdDataStore, matchDetailsDataStore)

  lazy val playerRepository:PlayerRepository = new DataStorePlayerRepository
}
