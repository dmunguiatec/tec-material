package expr.ints;

import expr.Expression;
import expr.ExpressionType;

public class SubExpression implements Expression {

    private int operandA, operandB;

    public SubExpression(int operandA, int operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.INT;
    }

    @Override
    public int evaluateInt() {
        return operandA - operandB;
    }

    @Override
    public double evaluateFP() {
        throw new UnsupportedOperationException();
    }
}
