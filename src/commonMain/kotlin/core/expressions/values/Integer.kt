package core.expressions.values

open class Integer(value: Int) : Rational(value.toDouble()) {
    fun toInt(): Int = value.toInt()

    fun toRational() = Rational(value)

    fun isEven() = toInt() % 2 == 0
    fun isOdd() = toInt() % 2 == 1

    override fun toString() = toInt().toString()
}
