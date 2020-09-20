package core.expressions

import core.Sign
import core.expressions.values.Integer

class Matrix(vararg lines: Array<Expression>) : Expression() {
    var lines: Array<Array<Expression>> = arrayOf(*lines)

    val dimension = Dimension(lines.size, lines.first().size)

    /** Returns true if the number of rows is 1 */
    fun isRowVector() = dimension.m == 1

    /** Returns true if the number of columns is 1 */
    fun isColumnVector() = dimension.n == 1

    /** Returns true if the matrix is a square matrix (m=n) */
    fun isSquareMatrix() = dimension.m == dimension.n

    companion object {
        fun zero(order: Int) = zero(order, order)

        fun zero(m: Int, n: Int) = zero(Dimension(m, n))

        fun zero(dimension: Dimension): Matrix {
            return Matrix(*Array(dimension.m) {
                Array(dimension.n) { Integer(0) }
            })
        }

        fun identity(order: Int): Matrix {
            val matrix = zero(order)
            for (i in 0 until order) {
                matrix.lines[i][i] = Integer(1)
            }
            return matrix
        }
    }

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
        TODO("Not yet implemented")
    }
}

data class Dimension(
    val m: Int,
    val n: Int
)
