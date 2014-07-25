package bowling.domain

case class ScoreCard(lane: Lane, players:Set[Player]) {
  def addPlayer(player: Player):ScoreCard = ???
}
