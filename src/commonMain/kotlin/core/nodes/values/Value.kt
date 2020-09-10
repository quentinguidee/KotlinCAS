package core.nodes.values

import core.nodes.Node

abstract class Value : Node() {
    override fun differentiated(unknown: Unknown): Node {
        return Integer(0)
    }
}
