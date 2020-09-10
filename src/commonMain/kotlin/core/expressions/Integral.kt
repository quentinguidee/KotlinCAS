package core.expressions

import core.Sign
import core.expressions.values.Unknown

class Integral(var unknown: Unknown, var argument: Expression) : Expression() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    constructor(argument: Expression) : this(Unknown(), argument)

    override fun simplified(): Expression {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "∫$argument d$unknown"
    }

    override fun toLaTeX(): String {
        return "\\int{" + argument.toLaTeX() + "d" + unknown.toLaTeX() + "}"
    }
}

interface Integrable {
    fun integrated(unknown: Unknown = Unknown()): Expression
}