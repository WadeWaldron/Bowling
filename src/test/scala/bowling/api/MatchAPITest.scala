package bowling.api

import bowling.domain.{MatchRepository, DomainHelpers}
import org.scalatest.FreeSpec
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar

class MatchAPITest extends FreeSpec with DomainHelpers with MockitoSugar {
  val mockRepository = mock[MatchRepository]
  val api = new MatchAPI(mockRepository)
}
