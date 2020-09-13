package core.expressions

import core.Sign

class Multiplication(vararg children: Expression) : MultiNode("*", *children) {
    override val sign: Sign
        get() = Sign.values().first { it.value == children.map { it.sign.value }.reduce { acc, i -> acc * i } }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Multiplication -> other.children == children
            else -> false
        }
    }

    override fun simplified(): Expression {
        return this
    }
}
