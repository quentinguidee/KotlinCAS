package core.nodes.values

import core.nodes.Multiplication
import core.nodes.Node

abstract class Value : Node() {
    override fun differentiated(unknown: Unknown): Node {
        return Integer(0)
    }

    override fun integrated(unknown: Unknown): Node {
        return Multiplication(this, unknown)
    }
}
