package core.expressions.values

class Natural(value: Int) : Integer(value) {
    fun toInteger() = Integer(this.toInt())
}
