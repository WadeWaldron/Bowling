package bowling.infrastructure

import bowling.core.Module
import matches._
import bowling.domain.{PlayerFactory, MatchId, MatchRepository}
import players._

trait InfrastructureModule extends Module {
  lazy val playerIdDataStore: PlayerIdDataStore = new InMemoryPlayerIdDataStore
  lazy val playerDetailsDataStore: PlayerDetailsDataStore = new InMemoryPlayerDetailsDataStore
  lazy val playerDataStore:PlayerDataStore = new PlayerDataStore(playerDetailsDataStore)
  lazy val playerFactory:PlayerFactory = new DataStorePlayerFactory(playerIdDataStore)

  lazy val matchIdDataStore: MatchIdDataStore = new InMemoryMatchIdDataStore
  lazy val matchDetailsDataStore: MatchDetailsDataStore = new InMemoryMatchDetailsDataStore
  lazy val matchRepository:MatchRepository = new DataStoreMatchRepository(matchIdDataStore, matchDetailsDataStore, playerDataStore)
}
