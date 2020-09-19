package core.expressions

import core.Sign
import core.expressions.values.Integer

class KroneckerDelta(val i: Integer, val j: Integer) : Expression() {
    override val sign: Sign
        get() = Sign.values().first { it == simplified().sign }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is KroneckerDelta -> other.i == i && other.j == j
            else -> false
        }
    }

    override fun simplified(): Expression = if (i.value == j.value) Integer(1) else Integer(0)

    override fun toLaTeX() = "\\delta_{${i.toLaTeX()}${j.toLaTeX()}}"

    override fun toString() = "δ_$i$j"
}
