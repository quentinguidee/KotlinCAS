package algebra

import algebra.sets.EmptySet
import algebra.sets.FiniteSet
import core.expressions.values.Integer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class SetsTests {
    @Test
    internal fun testFiniteSet() {
        val finiteSet = FiniteSet()
        finiteSet.add(Integer(2))
        finiteSet.add(Integer(5))
        finiteSet.addAll(Integer(2), Integer(3), Integer(4))
        assertEquals("{2,5,3,4}", finiteSet.toString())
    }
}