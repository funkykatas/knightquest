package util

final case class Seed(long: Long) {
  def next = Seed(long * 6364136223846793005L + 1442695040888963407L)
}

object Seed {
  def nextLong(seed: Seed): (Seed, Long) = (seed.next, seed.long)
  def nextInt(seed: Seed): (Seed, Int) = (seed.next, (seed.long >>> 16).toInt)
  def nextInt(seed: Seed, variation: Int): (Seed, Int) = (seed.next, (seed.long >>> 16).toInt % variation)
}
