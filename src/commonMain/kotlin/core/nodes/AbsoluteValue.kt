package core.nodes

import core.Sign

class AbsoluteValue(var argument: Node): Node() {
    override val sign: Sign
        get() = Sign.Positive

    override fun simplified(): Node {
        return argument.simplified().absoluteValue()
    }

    override fun toString(): String {
        return "|$argument|"
    }

    override fun toLaTeX(): String {
        return "\\left|" + argument.toLaTeX() + "\\right|"
    }
}

interface AbsoluteValuable {
    fun absoluteValue(): Node
}
