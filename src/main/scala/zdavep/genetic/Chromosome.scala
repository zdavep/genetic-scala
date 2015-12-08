package zdavep.genetic

/**
 * A potential solution (ordered list of Genes).
 */
case class Chromosome[T <: Gene](genes: List[T]) {
  override def equals(any: Any): Boolean = any match {
    case c: Chromosome[T] => genes == c.genes
    case _ => false
  }
  override def hashCode: Int = genes.hashCode()
}

/**
 * Wire fitness behavior into Chromosome.
 */
object Chromosome {
  implicit class CrossoverOps[T <: Gene](val c: Chromosome[T]) extends AnyVal {
    def fitness(implicit f: Fitness[T]): Double = f.fitness(c)
  }
}
