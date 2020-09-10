package algebra.sets

import core.expressions.Expression

class InfiniteSet(var symbol: String) : Set() {
    override fun contains(expression: Expression): Boolean {
        TODO("Not yet implemented")
    }

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