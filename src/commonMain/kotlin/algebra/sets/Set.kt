package algebra.sets

import algebra.sets.operations.*
import core.Node
import core.expressions.Expression

abstract class Set : Node(), Intersectable, Unionable {
    abstract val cardinal: Int

    abstract fun contains(element: Expression): Boolean
    abstract fun simplified(): Set

    override fun union(set: Set): Set {
        return Union(this, set)
    }

    override fun intersection(set: Set): Set {
        return Intersection(this, set)
    }
}
