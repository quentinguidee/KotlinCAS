package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Differential(var unknown: Unknown, var argument: Expression) : Expression() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    constructor(argument: Expression) : this(Unknown(), argument)

    override fun simplified(): Expression {
        return argument.differentiated(unknown = unknown)
    }

    override fun toString(): String {
        return "d/d$unknown($argument)"
    }

    override fun toLaTeX(): String {
        return "\\frac{d}{dx}\\left(${argument.toLaTeX()}\\right)"
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Differential -> other.unknown == unknown && other.argument == argument
            else -> false
        }
    }
}

interface Differentiable {
    fun differentiated(unknown: Unknown = Unknown()): Expression
}