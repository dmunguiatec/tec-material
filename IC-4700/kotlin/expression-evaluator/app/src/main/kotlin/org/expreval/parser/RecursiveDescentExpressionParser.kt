package org.expreval.parser

import org.expreval.expression.Expression
import org.expreval.expression.LiteralExpression
import org.expreval.expression.MulExpression
import org.expreval.expression.SumExpression
import org.expreval.token.TokenType
import org.expreval.token.Tokenizer

class RecursiveDescentExpressionParser(
    private val tokenizer: Tokenizer,
) : ExpressionParser {
    /**
     * Grammar
     *
     * expression -> term { "+" term }*
     * term -> factor { "*" factor }*
     * factor -> INT_LITERAL | "(" expression ")"
     * INT_LITERAL -> [\d+]
     */
    override fun parse(input: String): Expression {
        this.tokenizer.tokenize(input)
        return applyExpressionRule()
    }

    /**
     * Rule
     * expression -> term { "+" term }*
     */
    private fun applyExpressionRule(): Expression {
        var term = applyTermRule()
        while (this.tokenizer.hasNext() && this.tokenizer.peek()?.type == TokenType.OP_SUM) {
            this.tokenizer.next() // ignore the operand, we know we're building a sum expression
            val nextTerm = applyTermRule()
            term = SumExpression(term, nextTerm)
        }

        return term
    }

    /**
     * Rule
     * term -> factor { "*" factor }*
     */
    private fun applyTermRule(): Expression {
        var factor = applyFactorRule()
        while (this.tokenizer.hasNext() && this.tokenizer.peek()?.type == TokenType.OP_MUL) {
            this.tokenizer.next() // ignore the operand, we know we're building a mul expression
            val nextFactor = applyFactorRule()
            factor = MulExpression(factor, nextFactor)
        }

        return factor
    }

    /**
     * Rule
     * factor -> INT_LITERAL | "(" expression ")"
     */
    private fun applyFactorRule(): Expression {
        if (this.tokenizer.hasNext() && this.tokenizer.peek()?.type == TokenType.INT_LITERAL) {
            return applyIntLiteralRule()
        }

        if (this.tokenizer.hasNext() && this.tokenizer.peek()?.type != TokenType.LBRACK) {
            throw RuntimeException(
                "${RecursiveDescentExpressionParser::class.simpleName}: expected int literal or '(', " +
                    "instead got ${this.tokenizer.next() ?: "<EOF>"}",
            )
        }

        this.tokenizer.next() // consume ( and ignore it

        val expression = applyExpressionRule()

        if (!this.tokenizer.hasNext() || this.tokenizer.peek()?.type != TokenType.RBRACK) {
            throw RuntimeException(
                "${RecursiveDescentExpressionParser::class.simpleName}: expected ')', " +
                    "instead got ${this.tokenizer.next() ?: "<EOF>"}",
            )
        }

        this.tokenizer.next() // consume ) and ignore it

        return expression
    }

    /**
     * Rule
     * INT_LITERAL -> [\d+]
     */
    private fun applyIntLiteralRule(): Expression {
        val intLiteral = this.tokenizer.next()!!
        return LiteralExpression(intLiteral.value.toInt())
    }
}
