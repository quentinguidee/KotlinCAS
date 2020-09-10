package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Sin(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Expression {
        return Sin(argument.simplified())
    }

    override fun toString(): String {
        return "sin($argument)"
    }

    override fun toLaTeX(): String {
        return "\\sin{(${argument.toLaTeX()})}"
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Multiplication(argument.differentiated(unknown), Cos(argument))
    }
}
