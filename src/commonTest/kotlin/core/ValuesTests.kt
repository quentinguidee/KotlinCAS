package core

import core.expressions.Expression
import core.expressions.values.*
import kotlin.test.*

class ValuesTests {
    @Test
    internal fun testNatural() {
        val natural = Natural(3)
        assertEquals(natural.value, 3.0)
        assertEquals(natural.toInt(), 3)
        assertEquals(natural.toString(), "3")
        assertEquals(natural.toLaTeX(), "3")
        assertEquals(natural.sign, Sign.POSITIVE)
        assertTrue(natural.isOdd())
        assertFalse(natural.isEven())
        assertNotEquals(natural, Natural(2))
        assertEquals(natural, Natural(3))
    }

    @Test
    internal fun testInteger() {
        val integer = Integer(2)
        assertEquals(integer.toString(), "2")
        assertEquals(integer.toLaTeX(), "2")
        assertEquals(Integer(3).sign, Sign.POSITIVE)
        assertEquals(Integer(0).sign, Sign.SIGNLESS)
        assertEquals(Integer(-3).sign, Sign.NEGATIVE)
        assertEquals(Integer(-9).differentiated().toString(), "0")
        assertEquals(Integer(-9).integrated().toString(), "-9*x")
        assertFalse(integer.isOdd())
        assertTrue(integer.isEven())
        assertFalse(integer.isZero())
        assertTrue(Integer(0).isZero())
        assertEquals(integer, Integer(2))
        assertNotEquals(integer, Integer(3))
        assertEquals(integer, Natural(2))
        assertNotEquals(integer, Natural(3))
    }

    @Test
    internal fun testRational() {
        val rational = Rational(2)
        assertEquals(rational.toString(), "2.0")
        assertEquals(rational.toLaTeX(), "2.0")
        assertEquals(Rational(3).sign, Sign.POSITIVE)
        assertEquals(Rational(0).sign, Sign.SIGNLESS)
        assertEquals(Rational(-3).sign, Sign.NEGATIVE)
        assertEquals(Rational(-9).differentiated().toString(), "0")
        assertEquals(Rational(-9).integrated().toString(), "-9.0*x")
        assertEquals(rational, Rational(2))
        assertNotEquals(rational, Rational(3))
        assertEquals(rational, Natural(2))
        assertNotEquals(rational, Natural(3))
    }

    @Test
    internal fun testReal() {
        val real = Real(2)
        assertEquals(real.toString(), "2.0")
        assertEquals(real.toLaTeX(), "2.0")
        assertEquals(Real(3).sign, Sign.POSITIVE)
        assertEquals(Real(0).sign, Sign.SIGNLESS)
        assertEquals(Real(-3).sign, Sign.NEGATIVE)
        assertEquals(Real(-9).differentiated().toString(), "0")
        assertEquals(Real(-9).integrated().toString(), "-9.0*x")
        assertEquals(real, Rational(2))
        assertNotEquals(real, Rational(3))
        assertEquals(real, Natural(2))
        assertNotEquals(real, Natural(3))
    }

    @Test
    internal fun testComplex() {
        val complex = Complex(Real(3), Real(2))
        assertEquals(complex.real.value.toInt(), 3)
        assertEquals(complex.imaginary.value.toInt(), 2)

        val complexFromNatural = Natural(6).toInteger().toRational().toReal().toComplex()
        assertEquals(complexFromNatural.real.value.toInt(), 6)
        assertEquals(complexFromNatural.imaginary.value.toInt(), 0)
        assertEquals(complex, Complex(Integer(3), Integer(2)))
        assertNotEquals(complex, Complex(Integer(2), Integer(3)))
        assertEquals<Expression>(Complex(Integer(2), Integer(0)), Natural(2))
        assertNotEquals<Expression>(Complex(Integer(2), Integer(0)), Natural(3))
    }

    @Test
    internal fun testUnknown() {
        val unknownX = Unknown()
        val unknownY = Unknown("y")
        assertEquals(unknownX.toString(), "x")
        assertEquals(unknownY.toString(), "y")
        assertEquals(unknownX.toLaTeX(), "x")
        assertEquals(unknownY.toString(), "y")
        assertEquals(Unknown().differentiated().toString(), "1")
        assertEquals(Unknown().differentiated(unknownY).toString(), "0")
        assertEquals(Unknown().integrated().toString(), "x^2/2")
        assertEquals(Unknown().integrated(unknownY).toString(), "x*y")
        assertEquals(unknownX, Unknown())
        assertNotEquals(unknownY, Unknown())
    }
}