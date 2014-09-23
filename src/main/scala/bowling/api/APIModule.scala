package bowling.api

import bowling.core.Module
import bowling.domain.DomainModule

trait APIModule extends Module { this: DomainModule =>
  val matchAPI = new MatchAPI(matchRepository, playerFactory)
}
