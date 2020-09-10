package core.nodes

import core.Sign
import core.nodes.values.Unknown

abstract class Node : AbsoluteValuable, Differentiable, Opposable {
    abstract val sign: Sign

    abstract fun simplified(): Node
    abstract override fun toString(): String
    abstract fun toLaTeX(): String

    override fun absoluteValue(): Node {
        return AbsoluteValue(this)
    }

    override fun differentiated(unknown: Unknown): Node {
        return Differential(unknown, this)
    }

    override fun opposite(): Node {
        return Opposite(this)
    }
}
