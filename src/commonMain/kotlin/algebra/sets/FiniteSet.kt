package algebra.sets

import core.expressions.Expression

open class FiniteSet(vararg elements: Expression) : Set() {
    var elements: ArrayList<Expression> = ArrayList(elements.asList())

    override fun contains(expression: Expression): Boolean {
        return elements.contains(expression)
    }

    fun add(expression: Expression) {
        if (expression in elements) return else elements.add(expression)
    }

    fun addAll(vararg expressions: Expression) {
        expressions.forEach { expression -> add(expression) }
    }

    override fun toString(): String {
        return "{${elements.joinToString(separator = ",") { expression -> expression.toString() }}}"
    }

    override fun toLaTeX(): String {
        return "\\left\\{${elements.joinToString(separator = ",") { expression -> expression.toLaTeX() }}\\right\\}"
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is FiniteSet -> other.elements == elements
            else -> false
        }
    }
}

class EmptySet : FiniteSet()
