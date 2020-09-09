package core

import core.nodes.*
import core.nodes.values.Integer
import kotlin.test.Test
import kotlin.test.assertEquals

class CoreTests {
    @Test
    internal fun testInteger() {
        val integer = Integer(2)
        assertEquals(integer.toString(), "2")
        assertEquals(integer.toLaTeX(), "2")
    }

    @Test
    fun testPow() {
        val power = Power(Integer(2), Integer(3))
        assertEquals(power.toString(), "2^3")
        assertEquals(power.toLaTeX(), "{2}^{3}")
    }

    @Test
    internal fun testDivision() {
        val division = Division(Integer(2), Integer(4))
        assertEquals(division.toString(), "2/4")
        assertEquals(division.toLaTeX(), "\\frac{2}{4}")
    }

    @Test
    internal fun testAddition() {
        val addition = Addition(Integer(1), Integer(2), Integer(10))
        assertEquals(addition.toString(), "1+2+10")
    }

    @Test
    internal fun testAbsoluteValue() {
        val absoluteValue = AbsoluteValue(Integer(3))
        assertEquals(absoluteValue.toString(), "|3|")
        assertEquals(absoluteValue.toLaTeX(), "\\left|3\\right|")
    }

    @Test
    internal fun testCos() {
        val cos = Cos(Integer(3))
        assertEquals(cos.toString(), "cos(3)")
        assertEquals(cos.toLaTeX(), "\\cos{(3)}")
    }
}