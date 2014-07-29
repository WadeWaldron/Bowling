package bowling.domain

trait DomainHelpers {
  private var counter = 0

  def nextInt() = {
    counter = counter + 1
    counter
  }

  def createMatchId() = MatchId(nextInt())
  def createMatch(id: MatchId = createMatchId(), lane:Option[Lane] = None, players: Set[Player] = Set()) = Match(id, lane, players)

  def createLaneId() = LaneId(nextInt())
  def createLane(id:LaneId = createLaneId()) = Lane(id)

  def createPlayerName() = PlayerName("PlayerName"+nextInt())
  def createPlayerId() = PlayerId(nextInt())
}
