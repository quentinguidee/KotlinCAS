package core.nodes.values

import core.Sign
import core.nodes.Node

class Unknown(var symbol: String = "x") : Value() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Node {
        return this
    }

    override fun toString(): String {
        return symbol
    }

    override fun toLaTeX(): String {
        return toString()
    }

    override fun differentiated(unknown: Unknown): Node {
        return if (symbol == unknown.symbol) Integer(1) else Integer(0)
    }
}
