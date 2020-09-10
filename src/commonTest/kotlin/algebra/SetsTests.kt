package algebra

import algebra.sets.*
import core.expressions.values.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

class SetsTests {
    @Test
    internal fun testFiniteSet() {
        val finiteSet = FiniteSet()
        finiteSet.add(Integer(2))
        finiteSet.add(Integer(5))
        finiteSet.addAll(Integer(2), Integer(3), Integer(4))
        assertEquals("{2,5,3,4}", finiteSet.toString())
    }

    internal fun testInfiniteSet() {
        assertTrue(RealSet().contains(Real(1)))
        assertTrue(RealSet().contains(Natural(1)))
        assertTrue(ComplexSet().contains(Natural(1)))
        assertTrue(ComplexSet().contains(Complex(Real(1), Imaginary(Real(2)))))
    }
}
