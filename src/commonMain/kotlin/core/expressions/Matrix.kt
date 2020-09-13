package core.expressions

import core.Sign

class Matrix(vararg lines: Array<Expression>) : Expression() {
    var lines: Array<Array<Expression>> = arrayOf(*lines)

    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun equals(other: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun simplified(): Expression {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "[${
            lines.joinToString(separator = ",") { line ->
                "[${line.joinToString(separator = ",") { element -> element.toString() }}]"
            }
        }]"
    }

    override fun toLaTeX(): String {
        return "[${
            lines.joinToString(separator = ",") { line ->
                "[${line.joinToString(separator = ",") { element -> element.toString() }}]"
            }
        }]"
    }
}
