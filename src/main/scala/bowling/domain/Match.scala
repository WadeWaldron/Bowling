package bowling.domain

case class MatchId(value:Int)

case class Match(id: MatchId, lane: Lane, players:Set[Player]) {
  def addPlayer(player: Player):Match = ???
}
