package org.expreval.token

enum class TokenType {
    INT_LITERAL,
    OP_SUM,
    OP_MUL,
    LBRACK,
    RBRACK,
}

data class Token(
    val type: TokenType,
    val value: String,
)
