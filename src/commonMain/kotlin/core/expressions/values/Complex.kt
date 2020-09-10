package core.expressions.values

import core.Sign
import core.expressions.Expression

open class Complex(var real: Real, var imaginary: Real) : Value() {
    override val sign: Sign
        get() = Sign.UNKNOWN

    override fun simplified(): Expression {
        return if (imaginary.sign == Sign.SIGNLESS) {
            if (real.sign == Sign.SIGNLESS) Integer(0) else real
        } else {
            this
        }
    }

    override fun toString(): String {
        return when (imaginary.sign) {
            Sign.POSITIVE, Sign.SIGNLESS, Sign.UNKNOWN -> "$real+${imaginary}i"
            Sign.NEGATIVE -> "$real${imaginary}i"
        }
    }

    override fun toLaTeX(): String {
        return when (imaginary.sign) {
            Sign.POSITIVE, Sign.SIGNLESS, Sign.UNKNOWN -> "${real.toLaTeX()}+${imaginary.toLaTeX()}"
            Sign.NEGATIVE -> "${real.toLaTeX()}${imaginary.toLaTeX()}"
        }
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Complex -> other.real == real && other.imaginary == imaginary
            else -> false
        }
    }
}
