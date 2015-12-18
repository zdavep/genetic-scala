package zdavep

package object genetic {
  import Genetic._

  /**
   * Initialize a population (search space of potential solutions).
   */
  def initPopulation[T <: Gene](initSize: Int, size: Int)(implicit g: Genotype[T], f: Fitness[T]): Array[Chromosome[T]] =
    (1 to initSize).map(_ => g.random).sortBy(_.fitness).take(size).toArray

  /**
   * Evolve a population for a single generation.
   */
  def evolve[T <: Gene](p: Array[Chromosome[T]])(implicit s: Selector[T], f: Fitness[T], x: Xover[T], m: Mutate[T]): Unit = {
    x.crossover(s.select(p), s.select(p)).map(m.mutate).foreach { child =>
      val i = scala.util.Random.nextInt(p.length)
      if (child.isMoreFit(p(i))) {
        p(i) = child
      }
    }
  }
}
