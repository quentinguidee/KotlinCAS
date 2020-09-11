package algebra.sets

import core.Node
import core.expressions.Expression

class Union(vararg sets: Set) : Set() {
    protected var sets: ArrayList<Set> = ArrayList(sets.asList())

    override fun contains(element: Expression): Boolean {
        sets.forEach { set -> if (set.contains(element)) return true }
        return false
    }

    override fun toString(): String {
        return sets.joinToString(separator = "∪") { set -> "$set" }
    }

    fun simplified(): Node {
        var set: Set = EmptySet()
        // sets.forEach { s -> set = set.union(s) }
        for (s in sets) {
            set = set.union(s)
        }
        return set
    }

    override fun toLaTeX(): String {
        return sets.joinToString(separator = "\\cup") { set -> set.toLaTeX() }
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Union -> other.sets == sets
            else -> false
        }
    }

    override fun union(set: Set): Set {
        return Union(this, set)
    }
}

interface Unionable {
    fun union(set: Set): Set
}