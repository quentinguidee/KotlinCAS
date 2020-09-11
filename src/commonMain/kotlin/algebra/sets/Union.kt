package algebra.sets

import core.expressions.Expression

class Union(vararg sets: Set) : MultiSet("∪", "\\cup", *sets) {
    override fun contains(element: Expression): Boolean {
        sets.forEach { set -> if (set.contains(element)) return true }
        return false
    }

    override fun simplified(): Set {
        var set: Set = EmptySet()
        sets.forEach { s -> set = set.union(s) }
        return set
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Union -> other.sets == sets
            else -> false
        }
    }
}

interface Unionable {
    fun union(set: Set): Set
}