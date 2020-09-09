package core.nodes

import core.Sign

class Opposite(var argument: Node): Node() {
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
}

interface Opposable {
    fun opposite(): Node
}