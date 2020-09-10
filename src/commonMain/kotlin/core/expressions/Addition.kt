package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Addition(vararg children: Expression) : MultiNode("+", *children) {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Expression {
        return this
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Addition -> other.children == children
            else -> false
        }
    }

    override fun integrated(unknown: Unknown): Expression {
        return Addition(*children.map { child -> child.integrated(unknown) }.toTypedArray())
    }
}
