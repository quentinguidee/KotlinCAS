package core.expressions.values

import core.Sign
import core.expressions.Expression

open class Imaginary(var value: Real) : Value() {
    override val sign: Sign
        get() = value.sign

    override fun toString() = "${value}i"
    override fun toLaTeX() = toString()

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Imaginary -> other.value == value
            else -> false
        }
    }

    override fun simplified(): Expression {
        return this
    }
}
