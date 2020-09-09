package core.nodes

abstract class MultiNode(val separator: String, vararg children: Node) : Node() {
    private var children: ArrayList<Node> = ArrayList(children.asList())

    fun add(child: Node) {
        this.children.add(child)
    }

    fun addAll(vararg children: Node) {
        this.children.addAll(children)
    }

    override fun toString(): String {
        return children.joinToString(separator = separator) { node -> node.toString() }
    }

    override fun toLaTeX(): String {
        TODO("Not yet implemented")
    }
}
