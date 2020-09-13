package algebra.sets.operations

import algebra.sets.Set
import core.expressions.Expression

class Intersection(vararg sets: Set) : MultiSetOperation("∩", "\\cap", *sets) {
    override val cardinal: Int
        get() = TODO("Not yet implemented")

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Intersection -> other.sets == sets
            else -> return false
        }
    }

    override fun contains(element: Expression): Boolean {
        sets.forEach { set -> if (!set.contains(element)) return false }
        return true
    }

    override fun simplified(): Set {
        var set: Set = sets.first()
        sets.forEach { s -> set = set.intersection(s) }
        return set
    }
}

interface Intersectable {
    fun intersection(set: Set): Set
}