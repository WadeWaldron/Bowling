package bowling.domain

trait MatchRepository {
  def find(matchId: MatchId): Option[Match]
  def create(): Match
}
