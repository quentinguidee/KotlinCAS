package algebra.sets

import core.expressions.Expression
import core.expressions.values.*

abstract class InfiniteSet(var symbol: String) : Set() {
    override fun toString(): String {
        return symbol
    }

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
}

class ComplexSet : InfiniteSet("ℂ") {
    override fun contains(expression: Expression): Boolean {
        return expression is Complex || expression is Real || expression is Imaginary
    }
}

class ImaginarySet : InfiniteSet("\uD835\uDD40") {
    override fun contains(expression: Expression): Boolean {
        return expression is Imaginary
    }
}

class RationalSet : InfiniteSet("ℚ") {
    override fun contains(expression: Expression): Boolean {
        return expression is Rational
    }
}

class RealSet : InfiniteSet("ℝ") {
    override fun contains(expression: Expression): Boolean {
        return expression is Real
    }
}

class IntegerSet : InfiniteSet("ℤ") {
    override fun contains(expression: Expression): Boolean {
        return expression is Integer
    }
}

class NaturalSet : InfiniteSet("ℕ") {
    override fun contains(expression: Expression): Boolean {
        return expression is Natural
    }
}
