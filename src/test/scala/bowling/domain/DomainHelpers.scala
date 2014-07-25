package bowling.domain

trait DomainHelpers {
  private var counter = 0

  def nextInt() = {
    counter = counter + 1
    counter
  }

  def createLaneId() = LaneId(nextInt())
  def createPlayerName() = PlayerName("PlayerName"+nextInt())
  def createPlayerId() = PlayerId(nextInt())
}
