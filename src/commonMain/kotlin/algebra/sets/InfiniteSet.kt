package algebra.sets

import core.expressions.Expression
import core.expressions.values.*

abstract class InfiniteSet(var symbol: String) : Set() {
    override val cardinal: Int
        get() = TODO("Not yet implemented (should be infinity)")

    override fun toString(): String = symbol

    override fun toLaTeX(): String {
        // TODO: Return the LaTeX Symbols
        return toString()
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is InfiniteSet -> other.symbol == symbol
            else -> false
        }
    }

    override fun simplified(): Set {
        TODO("Not yet implemented")
    }

    override fun union(set: Set): Set {
        when (set) {
            is InfiniteSet -> if (set.symbol == symbol) return this
            is FiniteSet -> {
                set.elements.removeAll { element -> this.contains(element) }
                if (set.elements.count() == 0) return this
            }
        }
        return super.union(set)
    }

    override fun intersection(set: Set): Set {
        when (set) {
            is InfiniteSet -> if (set.symbol == symbol) return this
            is FiniteSet -> {
                set.elements.removeAll { element -> !this.contains(element) }
                return set
            }
        }
        return super.intersection(set)
    }
}

class ComplexSet : InfiniteSet("ℂ") {
    override fun contains(element: Expression): Boolean {
        return element is Complex || element is Real || element is Imaginary
    }
}

class ImaginarySet : InfiniteSet("\uD835\uDD40") {
    override fun contains(element: Expression): Boolean = element is Imaginary
}

class RealSet : InfiniteSet("ℝ") {
    override fun contains(element: Expression): Boolean = element is Real

    override fun union(set: Set): Set {
        if (set is RationalSet || set is IntegerSet || set is NaturalSet) {
            return this
        }
        return super.union(set)
    }

    override fun intersection(set: Set): Set {
        if (set is RationalSet || set is IntegerSet || set is NaturalSet) {
            return set
        }
        return super.intersection(set)
    }
}

class RationalSet : InfiniteSet("ℚ") {
    override fun contains(element: Expression): Boolean = element is Rational

    override fun union(set: Set): Set {
        if (set is IntegerSet || set is NaturalSet) {
            return this
        } else if (set is Real) {
            return set
        }
        return super.union(set)
    }

    override fun intersection(set: Set): Set {
        if (set is RealSet) {
            return this
        } else if (set is IntegerSet || set is NaturalSet) {
            return set
        }
        return super.intersection(set)
    }
}

class IntegerSet : InfiniteSet("ℤ") {
    override fun contains(element: Expression): Boolean = element is Integer

    override fun union(set: Set): Set {
        if (set is NaturalSet) {
            return this
        } else if (set is RationalSet || set is RealSet) {
            return set
        }
        return super.union(set)
    }

    override fun intersection(set: Set): Set {
        if (set is RealSet || set is RationalSet) {
            return this
        } else if (set is NaturalSet) {
            return set
        }
        return super.intersection(set)
    }
}

class NaturalSet : InfiniteSet("ℕ") {
    override fun contains(element: Expression): Boolean = element is Natural

    override fun union(set: Set): Set {
        if (set is RealSet || set is RationalSet || set is IntegerSet) {
            return set
        }
        return super.intersection(set)
    }

    override fun intersection(set: Set): Set {
        if (set is RealSet || set is RationalSet || set is IntegerSet) {
            return this
        }
        return super.intersection(set)
    }
}
