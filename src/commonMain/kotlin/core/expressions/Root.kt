package core.expressions

import core.Sign
import core.expressions.values.Integer
import core.expressions.values.Unknown

class Root(
        var radicand: Expression,
        var index: Expression = Integer(2),
) : Expression() {
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun simplified(): Expression {
        return this
    }

    override fun toString(): String {
        val index = this.index
        return if (index is Integer && index.value == 2) {
            "sqrt($radicand)"
        } else if (index is Integer && index.value == 3) {
            "cbrt($radicand)"
        } else {
            "root($radicand,$index)"
        }
    }

    override fun toLaTeX(): String {
        val index = this.index
        return if (index is Integer && index.value == 2) {
            "\\sqrt{" + radicand.toLaTeX() + "}"
        } else {
            "\\sqrt[" + index.toLaTeX() + "]{" + radicand.toLaTeX() + "}"
        }
    }

    override fun differentiated(unknown: Unknown): Expression {
        return Power(radicand, Division(Integer(1), index))
    }
}
