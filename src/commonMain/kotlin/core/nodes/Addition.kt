package core.nodes

import core.Sign
import core.nodes.values.Unknown

class Addition(vararg children: Node) : MultiNode("+", *children) {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Node {
        return this
    }

    override fun integrated(unknown: Unknown): Node {
        return Addition(*children.map { child -> child.integrated(unknown) }.toTypedArray())
    }
}
