package parser

class Token(val tokenType: TokenType, val value: String) {
    override fun toString(): String {
        return "($tokenType, $value)"
    }
}
