package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Cos(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Expression {
        return Cos(argument.simplified())
    }

    override fun toString(): String {
        return "cos($argument)"
    }

    override fun toLaTeX(): String {
        return "\\cos{(${argument.toLaTeX()})}"
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Multiplication(argument.differentiated(unknown), Opposite(Sin(argument)))
    }
}