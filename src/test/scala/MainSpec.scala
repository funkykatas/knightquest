import model.Knight
import org.scalatest.{FlatSpec, Matchers}

class MainSpec extends FlatSpec with Matchers {

  "Knight with better stats" should "win" in {
    val result = Main.fight(Knight(1, 30, 1), Knight(2, 200, 20))
    assert(result.winnerId == 2)
  }

}
