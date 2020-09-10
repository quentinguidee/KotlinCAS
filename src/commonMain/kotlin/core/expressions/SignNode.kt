package core.expressions

import core.Sign
import core.expressions.values.Integer

class SignNode(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = argument.sign

    override fun simplified(): Expression {
        return if (sign == Sign.UNKNOWN) this else Integer(sign.value)
    }

    override fun toString(): String {
        return "sign($argument)"
    }

    override fun toLaTeX(): String {
        return "sign\\left(" + argument.toLaTeX() + "\\right)"
    }
}
