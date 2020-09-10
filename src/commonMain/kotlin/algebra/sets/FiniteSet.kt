package algebra.sets

import core.expressions.Expression

class FiniteSet(vararg expressions: Expression) : Set() {
    var expressions: ArrayList<Expression> = ArrayList(expressions.asList())

    override fun contains(expression: Expression): Boolean {
        return expressions.contains(expression)
    }

    fun add(expression: Expression) {
        if (expression in expressions) return else expressions.add(expression)
    }

    fun addAll(vararg expressions: Expression) {
        expressions.forEach { expression -> add(expression) }
    }

    override fun toString(): String {
        return "{${expressions.joinToString(separator = ",") { expression -> expression.toString() }}}"
    }

    override fun toLaTeX(): String {
        return "\\left\\{${expressions.joinToString(separator = ",") { expression -> expression.toLaTeX() }}\\right\\}"
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is FiniteSet -> other.expressions == expressions
            else -> false
        }
    }
}