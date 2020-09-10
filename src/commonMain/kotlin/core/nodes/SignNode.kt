package core.nodes

import core.Sign
import core.nodes.values.Integer

class SignNode(var argument: Node) : Node() {
    override val sign: Sign
        get() = argument.sign

    override fun simplified(): Node {
        return if (sign == Sign.UNKNOWN) this else Integer(sign.value)
    }

    override fun toString(): String {
        return "sign($argument)"
    }

    override fun toLaTeX(): String {
        return "sign\\left(" + argument.toLaTeX() + "\\right)"
    }
}
