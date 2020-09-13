package core.expressions

import core.Sign

class Vector(vararg elements: Expression) : Expression() {
    var elements: ArrayList<Expression> = ArrayList(elements.toList())
    override val sign: Sign
        get() = TODO("Not yet implemented")

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Vector -> other.elements == elements
            else -> false
        }
    }

    override fun simplified(): Expression {
        return Vector(*elements.map { it.simplified() }.toTypedArray())
    }

    override fun toString(): String {
        return "(${elements.joinToString(separator = ",") { it.toString() }})"
    }

    override fun toLaTeX(): String {
        return "\\left(${elements.joinToString(separator = ",") { it.toLaTeX() }}\\right)"
    }
}
