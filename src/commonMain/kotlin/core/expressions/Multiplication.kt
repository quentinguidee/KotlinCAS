package core.expressions

import core.Sign

class Multiplication(vararg children: Expression) : MultiNode("*", *children) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Multiplication -> other.children == children
            else -> false
        }
    }

    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Expression {
        return this
    }
}
