package core.nodes

import core.Sign
import core.nodes.values.Unknown

class Integral(var unknown: Unknown, var argument: Node) : Node() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    constructor(argument: Node) : this(Unknown(), argument)

    override fun simplified(): Node {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "∫$argument d$unknown"
    }

    override fun toLaTeX(): String {
        return "\\int{" + argument.toLaTeX() + "d" + unknown.toLaTeX() + "}"
    }
}

interface Integrable {
    fun integrated(unknown: Unknown = Unknown()): Node
}