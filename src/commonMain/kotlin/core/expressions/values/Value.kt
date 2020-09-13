package core.expressions.values

import core.expressions.Multiplication
import core.expressions.Expression

abstract class Value : Expression() {
    override fun differentiated(unknown: Unknown): Expression = Integer(0)
    override fun integrated(unknown: Unknown): Expression = Multiplication(this, unknown)
}
