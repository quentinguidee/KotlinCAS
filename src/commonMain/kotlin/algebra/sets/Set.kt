package algebra.sets

import algebra.sets.operations.*
import core.Node
import core.expressions.Expression

abstract class Set : Node() {
    abstract val cardinal: Int

    abstract fun contains(element: Expression): Boolean
    abstract fun simplified(): Set

    internal open fun union(set: Set): Set = Union(this, set)
    internal open fun intersection(set: Set): Set = Intersection(this, set)
}
