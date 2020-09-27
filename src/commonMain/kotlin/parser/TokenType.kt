package parser

enum class TokenType {
    ADDITION,
    SUBSTRACTION,
    MULTIPLICATION,
    DIVISION,

    INTEGER,
    REAL,

    DOT,

    ERROR;

    companion object {
        fun from(string: String): TokenType {
            return when (string) {
                "+" -> ADDITION
                "-" -> SUBSTRACTION
                "*" -> MULTIPLICATION
                "/" -> DIVISION
                "." -> DOT
                else -> {
                    return if (string.toIntOrNull() != null) {
                        INTEGER
                    } else {
                        ERROR
                    }
                }
            }
        }
    }
}
