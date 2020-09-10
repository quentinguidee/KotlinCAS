package algebra.sets

import core.Node
import core.expressions.Expression

abstract class Set : Node() {
    abstract fun contains(expression: Expression): Boolean
}
