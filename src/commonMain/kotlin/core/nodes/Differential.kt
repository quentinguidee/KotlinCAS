package core.nodes

import core.Sign
import core.nodes.values.Unknown

class Differential(var unknown: Unknown, var argument: Node) : Node() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    constructor(argument: Node) : this(Unknown(), argument)

    override fun simplified(): Node {
        return argument.differentiated(unknown = unknown)
    }

    override fun toString(): String {
        return "d/d$unknown($argument)"
    }

    override fun toLaTeX(): String {
        return "\\frac{d}{dx}\\left(" + argument.toLaTeX() + "\\right)"
    }
}

interface Differentiable {
    fun differentiated(unknown: Unknown = Unknown()): Node
}