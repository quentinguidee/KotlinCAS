package core.expressions

import core.Sign
import core.expressions.values.Integer
import core.expressions.values.Unknown

class Division(var numerator: Expression, var denominator: Expression) : Expression() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Expression {
        return this
    }

    override fun toString(): String {
        return "$numerator/$denominator"
    }

    override fun toLaTeX(): String {
        return "\\frac{" + numerator.toLaTeX() + "}{" + denominator.toLaTeX() + "}"
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Division(
                Addition(
                        Multiplication(numerator.differentiated(unknown), denominator),
                        Multiplication(numerator, denominator.differentiated(unknown)).opposite()
                ),
                Power(denominator, Integer(2))
        )
    }

    override fun integrated(unknown: Unknown): Expression {
        // TODO: Replace Integer with NumericalValue
        if (numerator is Integer && denominator is Integer) {
            return Multiplication(this, unknown)
        }
        return Integral(unknown, argument = this)
    }
}