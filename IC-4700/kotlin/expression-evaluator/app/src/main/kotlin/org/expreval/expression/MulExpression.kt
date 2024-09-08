package org.expreval.expression

class MulExpression(
    val operandA: Expression,
    val operandB: Expression,
) : Expression {
    override fun evaluate(): Int = operandA.evaluate() * operandB.evaluate()
}
