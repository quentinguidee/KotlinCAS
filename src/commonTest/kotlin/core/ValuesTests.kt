package core

import core.expressions.Expression
import core.expressions.values.*
import kotlin.test.*

class ValuesTests {
    @Test
    internal fun testNatural() {
        val natural = Natural(3)
        assertEquals(3.0, natural.value)
        assertEquals(3, natural.toInt())
        assertEquals("3", natural.toString())
        assertEquals("3", natural.toLaTeX())
        assertEquals(Sign.POSITIVE, natural.sign)
        assertTrue(natural.isOdd())
        assertFalse(natural.isEven())
        assertNotEquals(Natural(2), natural)
        assertEquals(Natural(3), natural)
    }

    @Test
    internal fun testInteger() {
        val integer = Integer(2)
        assertEquals("2", integer.toString())
        assertEquals("2", integer.toLaTeX())
        assertEquals(Sign.POSITIVE, Integer(3).sign)
        assertEquals(Sign.SIGNLESS, Integer(0).sign)
        assertEquals(Sign.NEGATIVE, Integer(-3).sign)
        assertEquals("0", Integer(-9).differentiated().toString())
        assertEquals("-9*x", Integer(-9).integrated().toString())
        assertFalse(integer.isOdd())
        assertTrue(integer.isEven())
        assertFalse(integer.isZero())
        assertTrue(Integer(0).isZero())
        assertEquals(Integer(2), integer)
        assertNotEquals(Integer(3), integer)
        assertEquals(Natural(2), integer)
        assertNotEquals(Natural(3), integer)
    }

    @Test
    internal fun testRational() {
        val rational = Rational(2)
        assertEquals("2.0", rational.toString())
        assertEquals("2.0", rational.toLaTeX())
        assertEquals(Sign.POSITIVE, Rational(3).sign)
        assertEquals(Sign.SIGNLESS, Rational(0).sign)
        assertEquals(Sign.NEGATIVE, Rational(-3).sign)
        assertEquals("0", Rational(-9).differentiated().toString())
        assertEquals("-9.0*x", Rational(-9).integrated().toString())
        assertEquals(Rational(2), rational)
        assertNotEquals(Rational(3), rational)
        assertEquals(Natural(2), rational)
        assertNotEquals(Natural(3), rational)
    }

    @Test
    internal fun testReal() {
        val real = Real(2)
        assertEquals(real.toString(), "2.0")
        assertEquals(real.toLaTeX(), "2.0")
        assertEquals(Sign.POSITIVE, Real(3).sign)
        assertEquals(Sign.SIGNLESS, Real(0).sign)
        assertEquals(Sign.NEGATIVE, Real(-3).sign)
        assertEquals("0", Real(-9).differentiated().toString())
        assertEquals("-9.0*x", Real(-9).integrated().toString())
        assertEquals(Rational(2), real)
        assertNotEquals(Rational(3), real)
        assertEquals(Natural(2), real)
        assertNotEquals(Natural(3), real)
    }

    @Test
    internal fun testComplex() {
        val complex = Complex(Integer(3), Imaginary(Integer(2)))
        val complexFromNatural = Natural(6).toInteger().toRational().toReal().toComplex()
        assertEquals("3+2i", complex.toString())
        assertEquals("3+2i", complex.toLaTeX())
        assertEquals(3, complex.real.value.toInt())
        assertEquals(2, complex.imaginary.value.value.toInt())
        assertEquals(6, complexFromNatural.real.value.toInt())
        assertEquals(0, complexFromNatural.imaginary.value.value.toInt())
        assertEquals(Complex(Integer(3), Imaginary(Real(2))), complex)
        assertNotEquals(Complex(Integer(2), Imaginary(Real(3))), complex)
        // TODO: Should return True, but shouldn't be applied to "equals(other: Any?)"
        assertNotEquals<Expression>(Natural(2), Complex(Integer(2), Imaginary(Real(0))))
        assertNotEquals<Expression>(Natural(3), Complex(Integer(2), Imaginary(Real(0))))
    }

    @Test
    internal fun testUnknown() {
        val unknownX = Unknown()
        val unknownY = Unknown("y")
        assertEquals("x", unknownX.toString())
        assertEquals("y", unknownY.toString())
        assertEquals("x", unknownX.toLaTeX())
        assertEquals("y", unknownY.toString())
        assertEquals("1", Unknown().differentiated().toString())
        assertEquals("0", Unknown().differentiated(unknownY).toString())
        assertEquals("x^2/2", Unknown().integrated().toString())
        assertEquals("x*y", Unknown().integrated(unknownY).toString())
        assertEquals(Unknown(), unknownX)
        assertNotEquals(Unknown(), unknownY)
    }
}