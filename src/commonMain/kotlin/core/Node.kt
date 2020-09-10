package core

import core.expressions.Expression

abstract class Node {
    abstract fun simplified(): Expression

    abstract override fun toString(): String
    abstract fun toLaTeX(): String
}
