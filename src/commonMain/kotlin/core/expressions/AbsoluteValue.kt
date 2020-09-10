package core.expressions

import core.Sign
import core.expressions.values.Unknown

class AbsoluteValue(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.POSITIVE

    override fun simplified(): Expression {
        return argument.simplified().absoluteValue()
    }

    override fun toString(): String {
        return "|$argument|"
    }

    override fun toLaTeX(): String {
        return "\\left|${argument.toLaTeX()}\\right|"
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is AbsoluteValue -> other.argument == argument
            else -> false
        }
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Multiplication(
                argument.differentiated(unknown),
                Division(this, argument)
        )
    }

    override fun integrated(unknown: Unknown): Expression {
        return if (argument.sign == Sign.POSITIVE) {
            argument.integrated(unknown)
        } else {
            Multiplication(argument.integrated(unknown), SignNode(unknown))
        }
    }
}

interface AbsoluteValuable {
    fun absoluteValue(): Expression
}
