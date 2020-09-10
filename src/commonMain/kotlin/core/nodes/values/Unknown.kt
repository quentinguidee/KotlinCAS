package core.nodes.values

import core.Sign
import core.nodes.Division
import core.nodes.Multiplication
import core.nodes.Node
import core.nodes.Power

class Unknown(var symbol: String = "x") : Value() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Node {
        return this
    }

    override fun toString(): String {
        return symbol
    }

    override fun toLaTeX(): String {
        return toString()
    }

    override fun differentiated(unknown: Unknown): Node {
        return if (symbol == unknown.symbol) Integer(1) else Integer(0)
    }

    override fun integrated(unknown: Unknown): Node {
        return if (symbol == unknown.symbol) {
            Division(Power(this, Integer(2)), Integer(2))
        } else {
            Multiplication(this, unknown)
        }
    }
}
