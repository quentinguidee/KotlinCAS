package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Integral(var unknown: Unknown, var argument: Expression) : Expression() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    constructor(argument: Expression) : this(Unknown(), argument)

    override fun simplified(): Expression {
        TODO("Not yet implemented")
    }

    override fun toString() = "∫$argument d$unknown"
    override fun toLaTeX() = "\\int{${argument.toLaTeX()}d${unknown.toLaTeX()}}"

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Integral -> other.unknown == unknown && other.argument == argument
            else -> false
        }
    }
}
