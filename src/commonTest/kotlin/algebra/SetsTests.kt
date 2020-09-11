package algebra

import algebra.sets.*
import core.expressions.values.*
import kotlin.collections.Set
import kotlin.test.*

class SetsTests {
    @Test
    internal fun testFiniteSet() {
        val finiteSet = FiniteSet()
        finiteSet.add(Integer(2))
        finiteSet.add(Integer(5))
        finiteSet.addAll(Integer(2), Integer(3), Integer(4))
        assertEquals("{2,5,3,4}", finiteSet.toString())
    }

    @Test
    internal fun testInfiniteSet() {
        assertTrue(RealSet().contains(Real(1)))
        assertTrue(RealSet().contains(Natural(1)))
        assertTrue(ComplexSet().contains(Natural(1)))
        assertTrue(ComplexSet().contains(Complex(Real(1), Imaginary(Real(2)))))
        assertEquals(RealSet(), Union(RealSet(), NaturalSet()).simplified())
    }

    @Test
    internal fun testUnion() {
        assertEquals(RationalSet(), Union(RationalSet(), RationalSet()).simplified())
        assertNotEquals(NaturalSet(), Union(RealSet(), NaturalSet()).simplified())
        assertEquals(RealSet(), Union(FiniteSet(Integer(3)), RealSet()).simplified())
        assertEquals(RealSet(), Union(RealSet(), FiniteSet(Integer(3))).simplified())
    }

    @Test
    internal fun testIntersection() {
        assertEquals(FiniteSet(Integer(3)), Intersection(IntegerSet(), FiniteSet(Integer(3), Real(3.2))).simplified())
        assertEquals(FiniteSet(Integer(3)), Intersection(FiniteSet(Integer(3), Real(3.2)), IntegerSet()).simplified())
        assertEquals(RealSet(), Intersection(RealSet(), RealSet()).simplified())
        assertEquals(RationalSet(), Intersection(RationalSet(), RealSet()).simplified())
        assertEquals(RationalSet(), Intersection(RealSet(), RationalSet()).simplified())
    }
}
