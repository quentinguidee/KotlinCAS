package core.nodes

import core.Sign

abstract class Node {
    abstract val sign: Sign

    abstract fun simplified(): Node
    abstract override fun toString(): String
    abstract fun toLaTeX(): String
}
