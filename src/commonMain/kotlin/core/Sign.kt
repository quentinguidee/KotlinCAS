package core

enum class Sign(val value: Int) {
    Negative(-1),
    Signless(0),
    Positive(1),
    Unknown(2)
}