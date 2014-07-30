package bowling.domain

import bowling.core.Repository

trait PlayerRepository extends Repository[PlayerId, Player]
