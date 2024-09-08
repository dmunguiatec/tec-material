package org.expreval.expression

class LiteralExpression(
    val operand: Int,
) : Expression {
    override fun evaluate(): Int = operand
}
