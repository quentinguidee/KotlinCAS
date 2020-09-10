package core

import core.expressions.*
import core.expressions.values.Integer
import core.expressions.values.Natural
import core.expressions.values.Real
import core.expressions.values.Unknown
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ExpressionsTests {
    @Test
    internal fun testPow() {
        val power = Power(Integer(2), Integer(3))
        assertEquals(power.toString(), "2^3")
        assertEquals(power.toLaTeX(), "{2}^{3}")
        assertEquals(power.differentiated().toString(), "0")
        assertEquals(Power(Unknown(), Integer(3)).differentiated().toString(), "3*x^3+-1")
        assertEquals(Power(Unknown(), Integer(2)).differentiated().toString(), "2*x^2+-1")
        assertEquals(Power(Unknown(), Integer(2)).integrated().toString(), "1/2+1*x^2+1")
        assertEquals(Power(Natural(1), Natural(2)), Power(Natural(1), Natural(2)))
        assertNotEquals(Power(Natural(1), Natural(2)), Power(Natural(2), Natural(1)))
    }

    @Test
    internal fun testDivision() {
        val division = Division(Integer(2), Integer(4))
        assertEquals(division.toString(), "2/4")
        assertEquals(division.toLaTeX(), "\\frac{2}{4}")
        assertEquals(division.differentiated().toString(), "0*4+-2*0/4^2")
        assertEquals(division.integrated().toString(), "2/4*x")
        assertEquals(division, Division(Integer(2), Integer(4)))
        assertNotEquals(division, Division(Integer(2), Integer(1)))
    }

    @Test
    internal fun testAddition() {
        val addition = Addition(Integer(1), Integer(2), Integer(10))
        assertEquals(addition.toString(), "1+2+10")
        assertEquals(addition.toLaTeX(), "1+2+10")
        assertEquals(addition.differentiated().toString(), "d/dx(1+2+10)")
        assertEquals(addition.integrated().toString(), "1*x+2*x+10*x")
        assertEquals(addition, Addition(Integer(1), Integer(2), Integer(10)))
        assertNotEquals(addition, Addition(Integer(1), Integer(10), Integer(2)))
        assertNotEquals(addition, Addition(Integer(1), Integer(10), Integer(10), Integer(2)))
    }

    @Test
    internal fun testAbsoluteValue() {
        val absoluteValue = AbsoluteValue(Integer(3))
        assertEquals(absoluteValue.toString(), "|3|")
        assertEquals(absoluteValue.toLaTeX(), "\\left|3\\right|")
        assertEquals(AbsoluteValue(Unknown()).differentiated().toString(), "1*|x|/x")
        assertEquals(AbsoluteValue(Unknown()).integrated().toString(), "x^2/2*sign(x)")
        assertEquals(absoluteValue, AbsoluteValue(Natural(3)))
        assertNotEquals(absoluteValue, AbsoluteValue(Natural(1)))
    }

    @Test
    internal fun testSin() {
        val sin = Sin(Integer(3))
        assertEquals(sin.toString(), "sin(3)")
        assertEquals(sin.toLaTeX(), "\\sin{(3)}")
        assertEquals(Sin(Unknown()).differentiated().toString(), "1*cos(x)")
        assertEquals(Sin(Unknown()).integrated().toString(), "∫sin(x) dx")
        assertNotEquals<Expression>(Sin(Unknown()), Cos(Unknown()))
        assertEquals(Sin(Unknown()), Sin(Unknown()))
        assertNotEquals(Sin(Unknown()), Sin(Unknown("y")))
    }

    @Test
    internal fun testCos() {
        val cos = Cos(Integer(3))
        assertEquals(cos.toString(), "cos(3)")
        assertEquals(cos.toLaTeX(), "\\cos{(3)}")
        assertEquals(Cos(Unknown()).differentiated().toString(), "1*-sin(x)")
        assertEquals(Cos(Unknown()).integrated().toString(), "∫cos(x) dx")
        assertNotEquals<Expression>(Cos(Unknown()), Sin(Unknown()))
        assertEquals(Cos(Unknown()), Cos(Unknown()))
        assertNotEquals(Cos(Unknown()), Cos(Unknown("y")))
    }

    @Test
    internal fun testTan() {
        val tan = Tan(Integer(3))
        assertEquals(tan.toString(), "tan(3)")
        assertEquals(tan.toLaTeX(), "\\tan{(3)}")
        assertEquals(tan.differentiated().toString(), "0*cos(3)*cos(3)+-sin(3)*0*-sin(3)/cos(3)^2")
        assertEquals(tan.integrated().toString(), "∫tan(3) dx")
        assertNotEquals<Expression>(Tan(Unknown()), Sin(Unknown()))
        assertEquals(Tan(Unknown()), Tan(Unknown()))
        assertNotEquals(Tan(Unknown()), Tan(Unknown("y")))
    }

    @Test
    internal fun testOpposite() {
        val opposite = Opposite(Integer(3))
        assertEquals(opposite.toString(), "-3")
        assertEquals(opposite.toLaTeX(), "-3")
        assertEquals(Opposite(opposite).simplified().toString(), "3")
        assertEquals(Opposite(Integer(3)).sign, Sign.NEGATIVE)
        assertEquals(Opposite(Integer(0)).sign, Sign.SIGNLESS)
        assertEquals(Opposite(Integer(-3)).sign, Sign.POSITIVE)
        assertEquals(opposite.differentiated().toString(), "-0")
        assertEquals(opposite.integrated().toString(), "-3*x")
        assertEquals(opposite, Opposite(Real(3)))
        assertNotEquals(opposite, Opposite(Integer(2)))
    }

    @Test
    internal fun testRoot() {
        val sqrt = Root(Integer(9), Integer(2))
        val cbrt = Root(Integer(8), Integer(3))
        val root = Root(Integer(16), Integer(4))
        assertEquals(sqrt.toString(), "sqrt(9)")
        assertEquals(cbrt.toString(), "cbrt(8)")
        assertEquals(root.toString(), "root(16,4)")
        assertEquals(sqrt.toLaTeX(), "\\sqrt{9}")
        assertEquals(cbrt.toLaTeX(), "\\sqrt[3]{8}")
        assertEquals(sqrt.differentiated().toString(), "9^1/2")
        assertEquals(sqrt.integrated().toString(), "∫sqrt(9) dx")
        assertEquals(sqrt, Root(Integer(9), Real(2)))
        assertNotEquals(sqrt, Root(Integer(8), Real(2)))
        assertEquals(sqrt, Root(Integer(9)))
    }

    @Test
    internal fun testDifferential() {
        val differential = Differential(Unknown())
        assertEquals(differential.toString(), "d/dx(x)")
        assertEquals(differential.toLaTeX(), "\\frac{d}{dx}\\left(x\\right)")
        assertEquals(differential.differentiated().toString(), "d/dx(d/dx(x))")
        assertEquals(differential.integrated().toString(), "∫d/dx(x) dx")
        assertNotEquals(differential, Differential(Unknown("y"), Unknown()))
        assertEquals(differential, Differential(Unknown()))
    }

    @Test
    internal fun testIntegral() {
        val integral = Integral(Unknown())
        assertEquals(integral.toString(), "∫x dx")
        assertEquals(integral.toLaTeX(), "\\int{xdx}")
        assertEquals(integral.differentiated().toString(), "d/dx(∫x dx)")
        assertEquals(integral.integrated().toString(), "∫∫x dx dx")
        assertNotEquals(integral, Integral(Unknown("y"), Unknown()))
        assertEquals(integral, Integral(Unknown(), Unknown()))
    }
}