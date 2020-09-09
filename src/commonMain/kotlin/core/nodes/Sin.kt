package core.nodes

import core.Sign

class Sin(var argument: Node): Node() {
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
}
