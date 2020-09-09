package core.nodes

import core.Sign

class Addition(vararg children: Node) : MultiNode("+", *children) {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Node {
        return this
    }
}