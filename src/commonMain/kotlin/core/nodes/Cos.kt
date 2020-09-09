package core.nodes

import core.Sign

class Cos(var argument: Node): Node() {
    override val sign: Sign
        get() = Sign.Unknown

    override fun simplified(): Node {
        return Cos(argument.simplified())
    }

    override fun toString(): String {
        return "cos($argument)"
    }

    override fun toLaTeX(): String {
        return "\\cos{(" + argument.toLaTeX() + ")}"
    }
}