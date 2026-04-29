package edu.ic5701

data class Token(
    val type: TokenType,
    val lexeme: String,
    val line: Int,
    val offset: Int,
)

enum class TokenType {
    IDENT,
    LITERAL_INT,
    LITERAL_FLOAT
}
