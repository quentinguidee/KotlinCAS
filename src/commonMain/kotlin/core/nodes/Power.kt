package core.nodes

import core.Sign

class Power(var base: Node, var power: Node) : Node() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Node {
        return this
    }

    override fun toString(): String {
        return "$base^$power"
    }

    override fun toLaTeX(): String {
        return "{" + base.toLaTeX() + "}^{" + power.toLaTeX() + "}"
    }
}
