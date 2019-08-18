import model.{FightResult, Knight}

import scala.annotation.tailrec

object Main {

  def doDamage(defender: Knight, attacker: Knight): Knight = {
    defender.copy(health = defender.health - attacker.damage)
  }

  def isDead(knight: Knight): Boolean = {
    knight.health <= 0
  }

  @tailrec
  def fight(first: Knight, second: Knight): FightResult = {

    val firstAfterRound = doDamage(first, second)
    val secondAfterRound = doDamage(second, first)

    if (isDead(first))  {
      FightResult(second.id)
    } else if (isDead(second)) {
      FightResult(first.id)
    } else {
      fight(firstAfterRound, secondAfterRound)
    }
  }

  def main(args: Array[String]): Unit = {
    val result: FightResult = fight(Knight(1, 100, 10), Knight(2, 200, 20))
    println(s"The winner is a knight with id: ${result.winnerId}")
  }

}
