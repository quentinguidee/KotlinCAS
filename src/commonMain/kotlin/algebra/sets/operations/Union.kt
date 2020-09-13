package algebra.sets.operations

import algebra.sets.EmptySet
import algebra.sets.Set
import core.expressions.Expression

class Union(vararg sets: Set) : MultiSetOperation("∪", "\\cup", *sets) {
    override val cardinal: Int
        get() = TODO("Not yet implemented")

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
