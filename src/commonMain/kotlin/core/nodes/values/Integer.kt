package core.nodes.values

import core.Sign
import core.nodes.Node
import kotlin.math.sign

class Integer(var value: Int) : Value() {
    override val sign: Sign
        get() = Sign.values().first { it.value == value.sign }

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