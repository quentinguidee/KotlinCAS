package core.expressions.values

import core.Sign
import core.expressions.Expression
import kotlin.math.sign

open class Real(var value: Double) : Value() {
    override val sign: Sign
        get() = Sign.values().first { it.value == value.sign.toInt() }

    constructor(value: Int) : this(value.toDouble())

    fun toComplex(): Complex {
        return Complex(this, Real(0))
    }

    override fun simplified(): Expression {
        return this
    }

    override fun toString(): String {
        return value.toString()
    }

    override fun toLaTeX(): String {
        return toString()
    }

    fun isZero(): Boolean {
        return value == 0.0
    }
}