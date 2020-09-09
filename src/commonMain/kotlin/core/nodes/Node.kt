package core.nodes

import core.Sign

abstract class Node: AbsoluteValuable {
    abstract val sign: Sign

    abstract fun simplified(): Node
    abstract override fun toString(): String
    abstract fun toLaTeX(): String

    override fun absoluteValue(): Node {
        return AbsoluteValue(this)
    }
}