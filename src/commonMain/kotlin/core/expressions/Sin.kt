package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Sin(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Expression = Sin(argument.simplified())
    override fun toString() = "sin($argument)"
    override fun toLaTeX() = "\\sin{(${argument.toLaTeX()})}"

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Sin -> other.argument == argument
            else -> false
        }
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Multiplication(argument.differentiated(unknown), Cos(argument))
    }
}
