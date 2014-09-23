package bowling.domain

import bowling.core.{Factory, Repository}

trait MatchRepository extends Repository[MatchId, Match] with Factory[Match]
