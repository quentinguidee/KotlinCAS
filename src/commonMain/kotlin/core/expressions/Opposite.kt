package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Opposite(var argument: Expression) : Expression() {
    override val sign: Sign
        get() = Sign.values().first { it.value == argument.sign.value * -1 }

    override fun simplified(): Expression {
        return argument.simplified().opposite()
    }

    override fun toString() = "-$argument"
    override fun toLaTeX() = "-${argument.toLaTeX()}"

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Opposite -> other.argument == argument
            else -> false
        }
    }

    override fun opposite(): Expression = argument
    override fun differentiated(unknown: Unknown): Expression = Opposite(argument.differentiated(unknown))
    override fun integrated(unknown: Unknown): Expression = Opposite(argument.integrated(unknown))
}
