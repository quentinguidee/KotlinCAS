package core.nodes

import core.Sign
import core.nodes.values.Integer
import core.nodes.values.Unknown

class Division(var numerator: Node, var denominator: Node) : Node() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Node {
        return this
    }

    override fun toString(): String {
        return "$numerator/$denominator"
    }

    override fun toLaTeX(): String {
        return "\\frac{" + numerator.toLaTeX() + "}{" + denominator.toLaTeX() + "}"
    }

    override fun differentiated(unknown: Unknown): Node {
        return Division(
                Addition(
                        Multiplication(numerator.differentiated(unknown), denominator),
                        Multiplication(numerator, denominator.differentiated(unknown)).opposite()
                ),
                Power(denominator, Integer(2))
        )
    }
}