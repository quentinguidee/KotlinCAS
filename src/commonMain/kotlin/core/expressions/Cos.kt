package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Cos(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Expression = Cos(argument.simplified())
    override fun toString(): String = "cos($argument)"
    override fun toLaTeX(): String = "\\cos{(${argument.toLaTeX()})}"

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Cos -> other.argument == argument
            else -> false
        }
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Multiplication(argument.differentiated(unknown), Opposite(Sin(argument)))
    }
}