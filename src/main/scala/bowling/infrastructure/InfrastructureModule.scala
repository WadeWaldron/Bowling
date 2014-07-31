package bowling.infrastructure

import bowling.core.Module
import matches._
import bowling.domain.{PlayerRepository, MatchId, MatchRepository}
import players._

trait InfrastructureModule extends Module {
  lazy val matchIdDataStore: MatchIdDataStore = new InMemoryMatchIdDataStore
  lazy val matchDetailsDataStore: MatchDetailsDataStore = new InMemoryMatchDetailsDataStore
  lazy val matchRepository:MatchRepository = new DataStoreMatchRepository(matchIdDataStore, matchDetailsDataStore)

  lazy val playerIdDataStore: PlayerIdDataStore = new InMemoryPlayerIdDataStore
  lazy val playerDetailsDataStore: PlayerDetailsDataStore = new InMemoryPlayerDetailsDataStore
  lazy val playerRepository:PlayerRepository = new DataStorePlayerRepository(playerIdDataStore, playerDetailsDataStore)
}
