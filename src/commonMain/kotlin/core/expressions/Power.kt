package core.expressions

import core.Sign
import core.expressions.values.Integer
import core.expressions.values.Unknown

class Power(var base: Expression, var power: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Expression {
        return this
    }

    override fun toString(): String {
        return "$base^$power"
    }

    override fun toLaTeX(): String {
        return "{" + base.toLaTeX() + "}^{" + power.toLaTeX() + "}"
    }

    override fun differentiated(unknown: Unknown): Expression {
        val base = this.base
        if (base is Unknown && base.symbol == unknown.symbol) {
            return Multiplication(power, Power(base, Addition(power, Integer(-1))))
        }
        return Integer(0)
    }

    override fun integrated(unknown: Unknown): Expression {
        // TODO: Replace Integer with NumericalValue
        val base = this.base
        val power = this.power
        if (base is Unknown && power is Integer) {
            return if (base.symbol == unknown.symbol) {
                Multiplication(
                        Division(Integer(1), Addition(power, Integer(1))),
                        Power(base, Addition(power, Integer(1)))
                )
            } else {
                Multiplication(unknown, this)
            }
        }

        return super.integrated(unknown)
    }
}
