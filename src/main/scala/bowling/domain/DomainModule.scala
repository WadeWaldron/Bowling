package bowling.domain

import bowling.core.Module

trait DomainModule extends Module {
  def matchRepository: MatchRepository
  def playerFactory: PlayerFactory
}
