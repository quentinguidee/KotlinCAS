package core.expressions.values

import core.Sign
import core.expressions.Division
import core.expressions.Multiplication
import core.expressions.Expression
import core.expressions.Power

class Unknown(var symbol: String = "x") : Value() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Expression {
        return this
    }

    override fun toString(): String {
        return symbol
    }

    override fun toLaTeX(): String {
        return toString()
    }

    override fun differentiated(unknown: Unknown): Expression {
        return if (symbol == unknown.symbol) Integer(1) else Integer(0)
    }

    override fun integrated(unknown: Unknown): Expression {
        return if (symbol == unknown.symbol) {
            Division(Power(this, Integer(2)), Integer(2))
        } else {
            Multiplication(this, unknown)
        }
    }
}
