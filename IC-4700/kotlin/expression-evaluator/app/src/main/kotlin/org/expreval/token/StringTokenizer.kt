package org.expreval.token

private const val WHITESPACE_REGEX = "\\s+"

class StringTokenizer : Tokenizer {
    private var tokens: List<Token> = emptyList()
    private var currentIndex: Int = 0

    override fun tokenize(input: String) {
        val textTokens = input.split(WHITESPACE_REGEX.toRegex())
        val mutableTokens: MutableList<Token> = mutableListOf()
        for (textToken in textTokens) {
            mutableTokens.add(
                Token(
                    when (textToken) {
                        "+" -> TokenType.OP_SUM
                        "*" -> TokenType.OP_MUL
                        "(" -> TokenType.LBRACK
                        ")" -> TokenType.RBRACK
                        else -> TokenType.INT_LITERAL
                    },
                    textToken,
                ),
            )
        }

        this.tokens = mutableTokens
        this.currentIndex = 0
    }

    override fun hasNext(): Boolean = this.currentIndex < this.tokens.size

    override fun next(): Token? =
        if (this.hasNext()) {
            this.tokens[this.currentIndex++]
        } else {
            null
        }

    override fun peek(): Token? =
        if (this.hasNext()) {
            this.tokens[this.currentIndex]
        } else {
            null
        }
}
