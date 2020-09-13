package core.expressions

import core.Sign
import core.expressions.values.Integer

class SignNode(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = argument.sign

    override fun simplified(): Expression {
        return if (sign == Sign.UNKNOWN) this else Integer(sign.value)
    }

    override fun toString() = "sign($argument)"
    override fun toLaTeX() = "sign\\left(${argument.toLaTeX()}\\right)"

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is SignNode -> other.argument == argument
            else -> false
        }
    }
}
