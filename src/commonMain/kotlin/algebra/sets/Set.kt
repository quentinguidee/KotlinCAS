package algebra.sets

import core.Node
import core.expressions.Expression

abstract class Set : Node(), Unionable {
    abstract fun contains(element: Expression): Boolean

    override fun union(set: Set): Set {
        return Union(this, set)
    }
}
