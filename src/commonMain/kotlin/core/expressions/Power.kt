package core.expressions

import core.Sign
import core.expressions.values.Integer
import core.expressions.values.Real
import core.expressions.values.Unknown

class Power(var base: Expression, var power: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    /**
     * | Impl.                  | Expr.      | Returns        | Only if...   |
     * |------------------------|------------|----------------|--------------|
     * | [Power][power]         | b^1        | b              |              |
     * | [Power][power]         | b^0        | 1              |              |
     * | [Power][power]         | b^(m+n)    | b^m . b^n      | m,n∈ℤ+       |
     * | [Power][power]         | (b^m)^n    | b^(mn)         | b≠0, m,n∈ℤ   |
     * | ~                      | (bc)^n     | b^n . c^n      | b,c∈ℤ0, n∈ℤ  |
     * | ~                      | (bc)^n     | b^n . c^n      | b,c∈ℝ0+, n∈ℝ |
     * | ~                      | 0^n        | 0              | n∈ℝ0+        |
     * | ~                      | 0^0        | 1              |              |
     * | ~                      | 0^n        | undef          | n∈ℝ0-        |
     * | ~                      | 1^n        | 1              |              |
     * | [AbsoluteValue][power] | abs(x)^p   | x^p            | p is even    |
     */
    override fun simplified(): Expression {
        val base = this.base.simplified()
        val power = this.power.simplified()
        if (power is Real && power.value == 1.0) {
            return base
        } else if (power.isZero()) {
            return Integer(1)
        } else if (power is Addition && power.children.all { child ->
                child is Integer && (child.sign == Sign.POSITIVE || child.sign == Sign.SIGNLESS)
            }) {
            return Multiplication(*power.children.map { Power(base, it) }.toTypedArray())
        }
        return base.power(power)
    }

    override fun toString() = "$base^$power"
    override fun toLaTeX() = "{${base.toLaTeX()}}^{${power.toLaTeX()}}"

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Power -> other.base == base && other.power == power
            else -> false
        }
    }

    override fun differentiated(unknown: Unknown): Expression {
        val base = this.base
        if (base is Unknown && base.symbol == unknown.symbol) {
            return Multiplication(power, Power(base, Addition(power, Integer(-1))))
        }
        return Integer(0)
    }

    override fun integrated(unknown: Unknown): Expression {
        val base = this.base
        val power = this.power
        if (base is Unknown && power is Real) {
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

    override fun power(power: Expression): Expression {
        val thisBase = this.base.simplified()
        val thisPower = this.power.simplified()
        if (!thisBase.isZero() && thisPower is Integer && power is Integer) {
            return Power(thisBase, Multiplication(thisPower, power))
        }
        return super.power(power)
    }
}
