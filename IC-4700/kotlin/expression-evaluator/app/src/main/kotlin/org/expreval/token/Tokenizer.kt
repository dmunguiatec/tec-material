package org.expreval.token

interface Tokenizer {
    fun tokenize(input: String)

    fun hasNext(): Boolean

    fun next(): Token?

    fun peek(): Token?
}
