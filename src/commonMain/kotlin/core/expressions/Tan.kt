package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Tan(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Expression {
        return Tan(argument.simplified())
    }

    override fun toString(): String {
        return "tan($argument)"
    }

    override fun toLaTeX(): String {
        return "\\tan{(${argument.toLaTeX()})}"
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Division(Sin(argument), Cos(argument)).differentiated(unknown)
    }
}
