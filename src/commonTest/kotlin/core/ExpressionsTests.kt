package core

import core.expressions.*
import core.expressions.values.Integer
import core.expressions.values.Natural
import core.expressions.values.Real
import core.expressions.values.Unknown
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
class ExpressionsTests {
    @Test
    internal fun testPow() {
        val power = Power(Integer(2), Integer(3))
        assertEquals("2^3", power.toString())
        assertEquals("{2}^{3}", power.toLaTeX())
        assertEquals("0", power.differentiated().toString())
        assertEquals("3*x^3+-1", Power(Unknown(), Integer(3)).differentiated().toString())
        assertEquals("2*x^2+-1", Power(Unknown(), Integer(2)).differentiated().toString())
        assertEquals("1/2+1*x^2+1", Power(Unknown(), Integer(2)).integrated().toString())
        assertEquals(Power(Natural(1), Natural(2)), Power(Natural(1), Natural(2)))
        assertNotEquals(Power(Natural(2), Natural(1)), Power(Natural(1), Natural(2)))
        assertEquals(Unknown(), Power(Unknown(), Integer(1)).simplified())
        assertEquals(Integer(1), Power(Unknown(), Integer(0)).simplified())
        assertEquals(
            Power(Unknown(), Multiplication(Integer(2), Integer(3))),
            Power(Power(Unknown(), Integer(2)), Integer(3)).simplified()
        )
        assertEquals(Power(Unknown(), Integer(4)), Power(AbsoluteValue(Unknown()), Integer(4)).simplified())
        // assertEquals("x^3*x^4", Power(Unknown(), Addition(Integer(3), Integer(4))).simplified().toString())
    }

    @Test
    internal fun testDivision() {
        val division = Division(Integer(2), Integer(4))
        assertEquals("2/4", division.toString())
        assertEquals("\\frac{2}{4}", division.toLaTeX())
        assertEquals("0*4+-2*0/4^2", division.differentiated().toString())
        assertEquals("2/4*x", division.integrated().toString())
        assertEquals(Division(Integer(2), Integer(4)), division)
        assertNotEquals(Division(Integer(2), Integer(1)), division)
    }

    @Test
    internal fun testAddition() {
        val addition = Addition(Integer(1), Integer(2), Integer(10))
        assertEquals("1+2+10", addition.toString())
        assertEquals("1+2+10", addition.toLaTeX())
        assertEquals("d/dx(1+2+10)", addition.differentiated().toString())
        assertEquals("1*x+2*x+10*x", addition.integrated().toString())
        assertEquals(Addition(Integer(1), Integer(2), Integer(10)), addition)
        assertNotEquals(Addition(Integer(1), Integer(10), Integer(2)), addition)
        assertNotEquals(Addition(Integer(1), Integer(10), Integer(10), Integer(2)), addition)
    }

    @Test
    internal fun testAbsoluteValue() {
        val absoluteValue = AbsoluteValue(Integer(3))
        assertEquals("|3|", absoluteValue.toString())
        assertEquals("\\left|3\\right|", absoluteValue.toLaTeX())
        assertEquals("1*|x|/x", AbsoluteValue(Unknown()).differentiated().toString())
        assertEquals("x^2/2*sign(x)", AbsoluteValue(Unknown()).integrated().toString())
        assertEquals(AbsoluteValue(Natural(3)), absoluteValue)
        assertNotEquals(AbsoluteValue(Natural(1)), absoluteValue)
    }

    @Test
    internal fun testSin() {
        val sin = Sin(Integer(3))
        assertEquals("sin(3)", sin.toString())
        assertEquals("\\sin{(3)}", sin.toLaTeX())
        assertEquals("1*cos(x)", Sin(Unknown()).differentiated().toString())
        assertEquals("∫sin(x) dx", Sin(Unknown()).integrated().toString())
        assertNotEquals(Cos(Unknown()), Sin(Unknown()))
        assertEquals(Sin(Unknown()), Sin(Unknown()))
        assertNotEquals(Sin(Unknown("y")), Sin(Unknown()))
    }

    @Test
    internal fun testCos() {
        val cos = Cos(Integer(3))
        assertEquals("cos(3)", cos.toString())
        assertEquals("\\cos{(3)}", cos.toLaTeX())
        assertEquals("1*-sin(x)", Cos(Unknown()).differentiated().toString())
        assertEquals("∫cos(x) dx", Cos(Unknown()).integrated().toString())
        assertNotEquals(Sin(Unknown()), Cos(Unknown()))
        assertEquals(Cos(Unknown()), Cos(Unknown()))
        assertNotEquals(Cos(Unknown("y")), Cos(Unknown()))
    }

    @Test
    internal fun testTan() {
        val tan = Tan(Integer(3))
        assertEquals("tan(3)", tan.toString())
        assertEquals("\\tan{(3)}", tan.toLaTeX())
        assertEquals("0*cos(3)*cos(3)+-sin(3)*0*-sin(3)/cos(3)^2", tan.differentiated().toString())
        assertEquals("∫tan(3) dx", tan.integrated().toString())
        assertNotEquals(Sin(Unknown()), Tan(Unknown()))
        assertEquals(Tan(Unknown()), Tan(Unknown()))
        assertNotEquals(Tan(Unknown("y")), Tan(Unknown()))
    }

    @Test
    internal fun testOpposite() {
        val opposite = Opposite(Integer(3))
        assertEquals("-3", opposite.toString())
        assertEquals("-3", opposite.toLaTeX())
        assertEquals("3", Opposite(opposite).simplified().toString())
        assertEquals(Sign.NEGATIVE, Opposite(Integer(3)).sign)
        assertEquals(Sign.SIGNLESS, Opposite(Integer(0)).sign)
        assertEquals(Sign.POSITIVE, Opposite(Integer(-3)).sign)
        assertEquals("-0", opposite.differentiated().toString())
        assertEquals("-3*x", opposite.integrated().toString())
        assertEquals(Opposite(Real(3)), opposite)
        assertNotEquals(Opposite(Integer(2)), opposite)
    }

    @Test
    internal fun testRoot() {
        val sqrt = Root(Integer(9), Integer(2))
        val cbrt = Root(Integer(8), Integer(3))
        val root = Root(Integer(16), Integer(4))
        assertEquals("sqrt(9)", sqrt.toString())
        assertEquals("cbrt(8)", cbrt.toString())
        assertEquals("root(16,4)", root.toString())
        assertEquals("\\sqrt{9}", sqrt.toLaTeX())
        assertEquals("\\sqrt[3]{8}", cbrt.toLaTeX())
        assertEquals("9^1/2", sqrt.differentiated().toString())
        assertEquals("∫sqrt(9) dx", sqrt.integrated().toString())
        assertEquals(Root(Integer(9), Real(2)), sqrt)
        assertNotEquals(Root(Integer(8), Real(2)), sqrt)
        assertEquals(Root(Integer(9)), sqrt)
    }

    @Test
    internal fun testDifferential() {
        val differential = Differential(Unknown())
        assertEquals("d/dx(x)", differential.toString())
        assertEquals("\\frac{d}{dx}\\left(x\\right)", differential.toLaTeX())
        assertEquals("d/dx(d/dx(x))", differential.differentiated().toString())
        assertEquals("∫d/dx(x) dx", differential.integrated().toString())
        assertNotEquals(Differential(Unknown("y"), Unknown()), differential)
        assertEquals(Differential(Unknown()), differential)
    }

    @Test
    internal fun testIntegral() {
        val integral = Integral(Unknown())
        assertEquals("∫x dx", integral.toString())
        assertEquals("\\int{xdx}", integral.toLaTeX())
        assertEquals("d/dx(∫x dx)", integral.differentiated().toString())
        assertEquals("∫∫x dx dx", integral.integrated().toString())
        assertNotEquals(Integral(Unknown("y"), Unknown()), integral)
        assertEquals(Integral(Unknown(), Unknown()), integral)
    }

    @Test
    internal fun testVector() {
        val vector = Vector(Unknown(), Integer(2))
        assertEquals("(x,2)", vector.toString())
        assertEquals("\\left(x,2\\right)", vector.toLaTeX())
        assertEquals(Vector(Unknown(), Integer(2)), vector)
        assertNotEquals(Vector(Unknown(), Integer(3)), vector)
    }

    @Test
    internal fun testMatrix() {
        assertEquals(
            "[[1,2],[3,4]]", Matrix(
                arrayOf(Integer(1), Integer(2)),
                arrayOf(Integer(3), Integer(4))
            ).toString()
        )
    }
}
