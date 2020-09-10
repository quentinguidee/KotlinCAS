package core.nodes

import core.Sign
import core.nodes.values.Unknown

class AbsoluteValue(var argument: Node) : Node() {
    override val sign: Sign
        get() = Sign.POSITIVE

    override fun simplified(): Node {
        return argument.simplified().absoluteValue()
    }

    override fun toString(): String {
        return "|$argument|"
    }

    override fun toLaTeX(): String {
        return "\\left|" + argument.toLaTeX() + "\\right|"
    }

    override fun differentiated(unknown: Unknown): Node {
        return Multiplication(
                argument.differentiated(unknown),
                Division(this, argument)
        )
    }
}

interface AbsoluteValuable {
    fun absoluteValue(): Node
}
