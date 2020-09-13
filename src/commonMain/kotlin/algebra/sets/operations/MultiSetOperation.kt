package algebra.sets.operations

import algebra.sets.Set

abstract class MultiSetOperation(var separator: String, var latexSeparator: String, vararg sets: Set) : Set() {
    protected var sets: ArrayList<Set> = ArrayList(sets.asList())

    override fun toString(): String {
        return sets.joinToString(separator = separator) { set -> "$set" }
    }

    override fun toLaTeX(): String {
        return sets.joinToString(separator = latexSeparator) { set -> "$set" }
    }

    abstract override fun equals(other: Any?): Boolean
    abstract override fun simplified(): Set
}