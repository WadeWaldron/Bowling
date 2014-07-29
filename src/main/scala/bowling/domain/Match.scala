package bowling.domain

case class Match(lane: Lane, players:Set[Player]) {
  def addPlayer(player: Player):Match = ???
}
