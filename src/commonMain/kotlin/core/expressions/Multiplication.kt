package core.expressions

import core.Sign

class Multiplication(vararg children: Expression) : MultiNode("*", *children) {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Expression {
        return this
    }
}
