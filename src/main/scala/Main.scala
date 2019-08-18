import model.{FightResult, Knight}
import util.Seed

import scala.annotation.tailrec

object Main {

  //damage varies up to 10%
  def maxDamageRandomRange(damage: Int): Int = {
    if (damage < 10) 0 else (damage * 0.1).toInt
  }

  def randomizeDamage(damage: Int, seed: Seed): (Seed, Int) = {
    val damageRandomRange = maxDamageRandomRange(damage)
    val (nextSeed, randomValue) = damageRandomRange match {
      case 0 => (seed, damage) //reuse same seed
      case x => Seed.nextInt(seed, x)
    }
    (nextSeed, damage + randomValue)
  }

  // Critical hit change: 20% depending on items and defence. Implement someday maybe
  def doDamage(defender: Knight, attacker: Knight, seed: Seed): (Seed, Knight) = {
    val (nextSeed, damageWithVariation) = randomizeDamage(attacker.damage, seed)
    val damagedKnight = defender.copy(health = defender.health - damageWithVariation)
    (nextSeed, damagedKnight)
  }

  // Someday death will depend on knights items (guardian angel?)
  def isDead(knight: Knight): Boolean = {
    knight.health <= 0
  }

  @tailrec
  def fight(first: Knight, second: Knight, seed: Seed): FightResult = {

    val (seed1, firstAfterRound) = doDamage(first, second, seed)
    val (seed2, secondAfterRound) = doDamage(second, first, seed1)

    if (isDead(first))  {
      FightResult(second.id)
    } else if (isDead(second)) {
      FightResult(first.id)
    } else {
      fight(firstAfterRound, secondAfterRound, seed2)
    }
  }

  def main(args: Array[String]): Unit = {
    val result: FightResult = fight(Knight(1, 100, 10), Knight(2, 200, 20), Seed(100))
    println(s"The winner is a knight with id: ${result.winnerId}")
  }

}
