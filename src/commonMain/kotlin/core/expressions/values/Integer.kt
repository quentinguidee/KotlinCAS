package core.expressions.values

import core.Sign
import core.expressions.Expression
import kotlin.math.sign

class Integer(var value: Int) : Value() {
    override val sign: Sign
        get() = Sign.values().first { it.value == value.sign }

    override fun simplified(): Expression {
        return this
    }

    override fun toString(): String {
        return value.toString()
    }

    override fun toLaTeX(): String {
        return toString()
    }
}