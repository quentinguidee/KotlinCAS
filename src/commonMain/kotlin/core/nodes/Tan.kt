package core.nodes

import core.Sign
import core.nodes.values.Unknown

class Tan(var argument: Node) : Node() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Node {
        return Tan(argument.simplified())
    }

    override fun toString(): String {
        return "tan($argument)"
    }

    override fun toLaTeX(): String {
        return "\\tan{(" + argument.toLaTeX() + ")}"
    }

    override fun differentiated(unknown: Unknown): Node {
        return Division(Sin(argument), Cos(argument)).differentiated(unknown)
    }
}
