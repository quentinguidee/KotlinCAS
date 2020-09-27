package parser

import kotlin.test.Test

class ParserTests {
    @Test
    fun testLexer() {
        println(Lexer.execute("21.1+1"))
        println(Lexer.execute("32/34"))
    }
}
