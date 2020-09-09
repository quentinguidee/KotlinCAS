package core.nodes

import core.Sign

class Division(var numerator: Node, var denominator: Node) : Node() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Node {
        return this
    }

    override fun toString(): String {
        return "$numerator/$denominator"
    }

    override fun toLaTeX(): String {
        return "\\frac{" + numerator.toLaTeX() + "}{" + denominator.toLaTeX() + "}"
    }
}