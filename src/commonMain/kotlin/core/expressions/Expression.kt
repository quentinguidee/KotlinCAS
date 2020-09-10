package core.expressions

import core.Node
import core.Sign
import core.expressions.values.Unknown

abstract class Expression : Node(), AbsoluteValuable, Differentiable, Integrable, Opposable {
    abstract val sign: Sign

    abstract override fun toString(): String
    abstract override fun equals(other: Any?): Boolean

    abstract fun simplified(): Expression

    override fun absoluteValue(): Expression {
        return AbsoluteValue(this)
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Differential(unknown, this)
    }

    override fun integrated(unknown: Unknown): Expression {
        return Integral(unknown, this)
    }

    override fun opposite(): Expression {
        return Opposite(this)
    }
}