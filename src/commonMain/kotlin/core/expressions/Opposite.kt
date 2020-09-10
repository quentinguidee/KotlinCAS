package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Opposite(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.values().first { it.value == argument.sign.value * -1 }

    override fun simplified(): Expression {
        return argument.simplified().opposite()
    }

    override fun toString(): String {
        return "-$argument"
    }

    override fun toLaTeX(): String {
        return "-" + argument.toLaTeX()
    }

    override fun opposite(): Expression {
        return argument
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Opposite(argument.differentiated(unknown))
    }

    override fun integrated(unknown: Unknown): Expression {
        return Opposite(argument.integrated(unknown))
    }
}

interface Opposable {
    fun opposite(): Expression
}