package parser

class Lexer {
    companion object {
        fun execute(input: String): ArrayList<Token> {
            var tokens = ArrayList<Token>()
            var i = 0
            while (i < input.length) {
                var current = input[i].toString()
                val currentTokenType = TokenType.from(current)
                var j = 1
                while (i + j < input.length && currentTokenType == TokenType.from(input[i + j].toString())) {
                    current += input[i + j]
                    j++
                }
                i += j
                tokens.add(Token(currentTokenType, current))
            }
            Lexer.handleReals(tokens)
            return tokens
        }

        private fun handleReals(tokens: ArrayList<Token>) {
            var i = 0
            while (i < tokens.count() - 2) {
                if (tokens[i].tokenType == TokenType.INTEGER &&
                    tokens[i + 1].tokenType == TokenType.DOT &&
                    tokens[i + 2].tokenType == TokenType.INTEGER
                ) {
                    val token = Token(
                        TokenType.REAL,
                        tokens[i].value + tokens[i + 1].value + tokens[i + 2].value
                    )

                    repeat(3) {
                        tokens.removeAt(i)
                    }

                    tokens.add(i, token)
                }
                i++
            }
        }
    }
}
