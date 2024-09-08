package org.expreval.expression

class SumExpression(
    val operandA: Expression,
    val operandB: Expression,
) : Expression {
    override fun evaluate(): Int = operandA.evaluate() + operandB.evaluate()
}
