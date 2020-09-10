package algebra

import algebra.sets.FiniteSet
import core.expressions.values.Integer
import kotlin.test.Test
import kotlin.test.assertEquals

class SetsTests {
    @Test
    internal fun testFiniteSet() {
        val finiteSet = FiniteSet()
        finiteSet.add(Integer(2))
        finiteSet.add(Integer(5))
        finiteSet.addAll(Integer(2), Integer(3), Integer(4))
        assertEquals(finiteSet.toString(), "{2,5,3,4}")
    }
}