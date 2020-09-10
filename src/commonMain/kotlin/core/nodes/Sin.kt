package core.nodes

import core.Sign
import core.nodes.values.Unknown

class Sin(var argument: Node) : Node() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Node {
        return Sin(argument.simplified())
    }

    override fun toString(): String {
        return "sin($argument)"
    }

    override fun toLaTeX(): String {
        return "\\sin{(" + argument.toLaTeX() + ")}"
    }

    override fun differentiated(unknown: Unknown): Node {
        return Multiplication(argument.differentiated(unknown), Cos(argument))
    }
}
