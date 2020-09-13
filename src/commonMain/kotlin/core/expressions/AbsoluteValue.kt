package core.expressions

import core.Sign
import core.expressions.values.Integer
import core.expressions.values.Unknown

class AbsoluteValue(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = if (argument.isZero()) Sign.SIGNLESS else Sign.POSITIVE

    override fun simplified(): Expression {
        return argument.simplified().absoluteValue()
    }

    override fun toString() = "|$argument|"
    override fun toLaTeX() = "\\left|${argument.toLaTeX()}\\right|"

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

    override fun power(power: Expression): Expression {
        //val power = power
        if (power is Integer && power.isEven()) {
            return Power(argument, power).simplified()
        }
        (0..5).map { it + 1 }
        (0..5).toList()
        return this
    }
}
