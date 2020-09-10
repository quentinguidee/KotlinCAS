package core.expressions.values

open class Integer(value: Int) : Rational(value.toDouble()) {
    fun toInt(): Int {
        return value.toInt()
    }

    fun toRational(): Rational {
        return Rational(value)
    }

    override fun toString(): String {
        return toInt().toString()
    }
}