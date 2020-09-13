package core.expressions

abstract class MultiNode(val separator: String, vararg children: Expression) : Expression() {
    var children: ArrayList<Expression> = ArrayList(children.asList())
        private set

    fun add(child: Expression) {
        this.children.add(child)
    }

    fun addAll(vararg children: Expression) {
        this.children.addAll(children)
    }

    override fun toString(): String {
        return children.joinToString(separator = separator) { node -> node.toString() }
    }

    override fun toLaTeX(): String {
        return children.joinToString(separator = separator) { node -> node.toLaTeX() }
    }

    abstract override fun equals(other: Any?): Boolean
}
