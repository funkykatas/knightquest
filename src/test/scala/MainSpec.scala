import model.Knight
import org.scalatest.{FlatSpec, Matchers}
import util.Seed

class MainSpec extends FlatSpec with Matchers {

  val defaultSeedValue: Long = 9375017451975L

  "Knight with better stats" should "win" in {
    val result = Main.fight(Knight(1, 30, 1), Knight(2, 200, 20), Seed(defaultSeedValue))
    assert(result.winnerId == 2)
  }

  "Max damage random range" should "be 0 for values < 10" in {
    assert(Main.maxDamageRandomRange(-1) == 0)
    assert(Main.maxDamageRandomRange(0) == 0)
    assert(Main.maxDamageRandomRange(1) == 0)
    assert(Main.maxDamageRandomRange(9) == 0)
  }

  "Max damage random range" should "be calculated for values >= 10" in {
    assert(Main.maxDamageRandomRange(10) == 1)
    assert(Main.maxDamageRandomRange(19) == 1)
    assert(Main.maxDamageRandomRange(20) == 2)
    assert(Main.maxDamageRandomRange(21) == 2)
    assert(Main.maxDamageRandomRange(29) == 2)
  }

  "Randomize damage" should "return value and seed with given values" in {
    val (seed, damage) = Main.randomizeDamage(100, Seed(defaultSeedValue))
    assert(seed.long == 2582238640489843274L)
    assert(damage == 103)
  }

  "Knight" should "do damage to opponent" in {
    val (_, knight) = Main.doDamage(Knight(1, 100, 10), Knight(2, 200, 20), Seed(defaultSeedValue))
    assert(knight.health == 79)
  }

}
