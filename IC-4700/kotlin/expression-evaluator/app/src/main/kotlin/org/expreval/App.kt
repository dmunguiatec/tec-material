package org.expreval

import org.expreval.parser.ExpressionParser
import org.expreval.parser.RecursiveDescentExpressionParser
import org.expreval.token.StringTokenizer
import org.expreval.token.Tokenizer

class App {
    private val parser: ExpressionParser = AppConfig.parser()

    fun repl() {
        var done = false
        while (!done) {
            print("> ")
            val input = readLine()
            if (input == null) { // ctrl + d
                done = true
            } else {
                val expression = parser.parse(input)
                val value = expression.evaluate()
                println("$value\n")
            }
        }
    }
}

class AppConfig {
    companion object {
        fun parser(): ExpressionParser = RecursiveDescentExpressionParser(tokenizer())

        private fun tokenizer(): Tokenizer = StringTokenizer()
    }
}

fun main() {
    App().repl()
}
