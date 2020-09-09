package core.nodes.values

import core.Sign
import core.nodes.Node

class Integer(var value: Int) : Value() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Node {
        return this
    }

    override fun toString(): String {
        return value.toString()
    }

    override fun toLaTeX(): String {
        return toString()
    }
}