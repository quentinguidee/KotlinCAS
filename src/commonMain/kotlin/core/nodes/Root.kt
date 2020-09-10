package core.nodes

import core.Sign
import core.nodes.values.Integer

class Root(
        var radicand: Node,
        var index: Node = Integer(2)
) : Node() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Node {
        return this
    }

    override fun toString(): String {
        val index = this.index
        return if (index is Integer && index.value == 2) {
            "sqrt($radicand)"
        } else if (index is Integer && index.value == 3) {
            "cbrt($radicand)"
        } else {
            "root($radicand,$index)"
        }
    }

    override fun toLaTeX(): String {
        val index = this.index
        return if (index is Integer && index.value == 2) {
            "\\sqrt{" + radicand.toLaTeX() + "}"
        } else {
            "\\sqrt[" + index.toLaTeX() + "]{" + radicand.toLaTeX() + "}"

        }
    }
}
