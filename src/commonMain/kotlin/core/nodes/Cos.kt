package core.nodes

import core.Sign
import core.nodes.values.Unknown

class Cos(var argument: Node) : Node() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Node {
        return Cos(argument.simplified())
    }

    override fun toString(): String {
        return "cos($argument)"
    }

    override fun toLaTeX(): String {
        return "\\cos{(" + argument.toLaTeX() + ")}"
    }

    override fun differentiated(unknown: Unknown): Node {
        return Multiplication(argument.differentiated(unknown), Opposite(Sin(argument)))
    }
}