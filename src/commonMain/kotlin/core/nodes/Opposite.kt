package core.nodes

import core.Sign
import core.nodes.values.Unknown

class Opposite(var argument: Node) : Node() {
    override val sign: Sign
        get() = Sign.values().first { it.value == argument.sign.value * -1 }

    override fun simplified(): Node {
        return argument.simplified().opposite()
    }

    override fun toString(): String {
        return "-$argument"
    }

    override fun toLaTeX(): String {
        return "-" + argument.toLaTeX()
    }

    override fun opposite(): Node {
        return argument
    }

    override fun differentiated(unknown: Unknown): Node {
        return Opposite(argument.differentiated(unknown))
    }

    override fun integrated(unknown: Unknown): Node {
        return Opposite(argument.integrated(unknown))
    }
}

interface Opposable {
    fun opposite(): Node
}