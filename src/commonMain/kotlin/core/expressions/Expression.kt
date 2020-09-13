package core.expressions

import core.Node
import core.Sign
import core.expressions.values.Unknown

abstract class Expression : Node() {
    abstract val sign: Sign

    abstract override fun toString(): String
    abstract override fun equals(other: Any?): Boolean

    abstract fun simplified(): Expression

    /** Should not be called directly. Call [AbsoluteValue][simplified] instead. */
    internal open fun absoluteValue(): Expression = AbsoluteValue(this)

    /** Should not be called directly. Call [Differential][simplified] instead. */
    internal open fun differentiated(unknown: Unknown = Unknown()): Expression = Differential(unknown, this)

    /** Should not be called directly. Call [Integral][simplified] instead. */
    internal open fun integrated(unknown: Unknown = Unknown()): Expression = Integral(unknown, this)

    /** Should not be called directly. Call [Opposite][simplified] instead. */
    internal open fun opposite(): Expression = Opposite(this)

    /** Should not be called directly. Call [Power][simplified] instead. */
    internal open fun power(power: Expression): Expression = Power(this, power)

    fun isPositive(): Boolean = sign == Sign.POSITIVE
    fun isZero(): Boolean = sign == Sign.SIGNLESS
    fun isNegative(): Boolean = sign == Sign.NEGATIVE
}
