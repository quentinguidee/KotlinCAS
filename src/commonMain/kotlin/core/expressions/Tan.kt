package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Tan(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Expression = Tan(argument.simplified())
    override fun toString() = "tan($argument)"
    override fun toLaTeX() = "\\tan{(${argument.toLaTeX()})}"

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Tan -> other.argument == argument
            else -> false
        }
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Division(Sin(argument), Cos(argument)).differentiated(unknown)
    }
}
