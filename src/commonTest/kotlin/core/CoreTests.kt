package core

import core.nodes.*
import core.nodes.values.Integer
import core.nodes.values.Unknown
import kotlin.test.Test
import kotlin.test.assertEquals

class CoreTests {
    @Test
    internal fun testInteger() {
        val integer = Integer(2)
        assertEquals(integer.toString(), "2")
        assertEquals(integer.toLaTeX(), "2")
        assertEquals(Integer(3).sign, Sign.POSITIVE)
        assertEquals(Integer(0).sign, Sign.SIGNLESS)
        assertEquals(Integer(-3).sign, Sign.NEGATIVE)
        assertEquals(Integer(-9).differentiated().toString(), "0")
    }

    @Test
    internal fun testUnknown() {
        val unknownX = Unknown()
        val unknownY = Unknown("y")
        assertEquals(unknownX.toString(), "x")
        assertEquals(unknownY.toString(), "y")
        assertEquals(unknownX.toLaTeX(), "x")
        assertEquals(unknownY.toString(), "y")
        assertEquals(unknownX.differentiated().toString(), "1")
        assertEquals(unknownX.differentiated(unknownY).toString(), "0")
    }

    @Test
    fun testPow() {
        val power = Power(Integer(2), Integer(3))
        assertEquals(power.toString(), "2^3")
        assertEquals(power.toLaTeX(), "{2}^{3}")
        assertEquals(power.differentiated().toString(), "0")
        assertEquals(Power(Unknown(), Integer(3)).differentiated().toString(), "3*x^3+-1")
        assertEquals(Power(Unknown(), Integer(2)).differentiated().toString(), "2*x^2+-1")
    }

    @Test
    internal fun testDivision() {
        val division = Division(Integer(2), Integer(4))
        assertEquals(division.toString(), "2/4")
        assertEquals(division.toLaTeX(), "\\frac{2}{4}")
        assertEquals(division.differentiated().toString(), "0*4+-2*0/4^2")
    }

    @Test
    internal fun testAddition() {
        val addition = Addition(Integer(1), Integer(2), Integer(10))
        assertEquals(addition.toString(), "1+2+10")
        assertEquals(addition.toLaTeX(), "1+2+10")
        assertEquals(addition.differentiated().toString(), "d/dx(1+2+10)")
    }

    @Test
    internal fun testAbsoluteValue() {
        val absoluteValue = AbsoluteValue(Integer(3))
        assertEquals(absoluteValue.toString(), "|3|")
        assertEquals(absoluteValue.toLaTeX(), "\\left|3\\right|")
        assertEquals(AbsoluteValue(Unknown()).differentiated().toString(), "1*|x|/x")
    }

    @Test
    internal fun testSin() {
        val sin = Sin(Integer(3))
        assertEquals(sin.toString(), "sin(3)")
        assertEquals(sin.toLaTeX(), "\\sin{(3)}")
        assertEquals(Sin(Unknown()).differentiated().toString(), "1*cos(x)")
    }

    @Test
    internal fun testCos() {
        val cos = Cos(Integer(3))
        assertEquals(cos.toString(), "cos(3)")
        assertEquals(cos.toLaTeX(), "\\cos{(3)}")
        assertEquals(Cos(Unknown()).differentiated().toString(), "1*-sin(x)")
    }

    @Test
    internal fun testTan() {
        val tan = Tan(Integer(3))
        assertEquals(tan.toString(), "tan(3)")
        assertEquals(tan.toLaTeX(), "\\tan{(3)}")
        assertEquals(tan.differentiated().toString(), "0*cos(3)*cos(3)+-sin(3)*0*-sin(3)/cos(3)^2")
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
    }

    @Test
    fun testDifferential() {
        val differential = Differential(Unknown())
        assertEquals(differential.toString(), "d/dx(x)")
        assertEquals(differential.toLaTeX(), "\\frac{d}{dx}\\left(x\\right)")
        assertEquals(differential.differentiated().toString(), "d/dx(d/dx(x))")
    }
}