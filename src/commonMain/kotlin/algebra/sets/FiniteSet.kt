package algebra.sets

import core.expressions.Expression

open class FiniteSet(vararg elements: Expression) : Set() {
    var elements: ArrayList<Expression> = ArrayList(elements.asList())

    override val cardinal: Int
        get() = elements.count()

    override fun contains(element: Expression): Boolean {
        return elements.contains(element)
    }

    override fun simplified(): Set {
        TODO("Not yet implemented")
    }

    fun add(expression: Expression) {
        if (expression in elements) return else elements.add(expression)
    }

    fun addAll(vararg expressions: Expression) {
        expressions.forEach { add(it) }
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

    override fun union(set: Set): Set {
        when (set) {
            is FiniteSet -> {
                addAll(*set.elements.toTypedArray())
                return this
            }
            is InfiniteSet -> return set.union(this)
        }
        return super.union(set)
    }

    override fun intersection(set: Set): Set {
        when (set) {
            is FiniteSet -> {
                val newSet = EmptySet()
                set.elements.forEach { element ->
                    if (element in this.elements) {
                        newSet.add(element)
                    }
                }
                return newSet
            }
            is InfiniteSet -> return set.intersection(this)
        }
        return super.intersection(set)
    }
}

class EmptySet : FiniteSet()
