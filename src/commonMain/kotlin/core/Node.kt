package core

abstract class Node {
    abstract override fun toString(): String
    abstract fun toLaTeX(): String

    abstract override fun equals(other: Any?): Boolean
}
