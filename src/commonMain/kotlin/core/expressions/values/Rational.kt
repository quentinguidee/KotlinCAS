package core.expressions.values

open class Rational(value: Double) : Real(value) {
    constructor(value: Int) : this(value.toDouble())

    fun toReal(): Real {
        return Real(value)
    }
}