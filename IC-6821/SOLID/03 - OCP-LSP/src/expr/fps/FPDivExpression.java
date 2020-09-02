package expr.fps;

import expr.Expression;
import expr.ExpressionType;

public class FPDivExpression implements Expression {

    private double operandA, operandB;

    public FPDivExpression(double operandA, double operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.FP;
    }

    @Override
    public int evaluateInt() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double evaluateFP() {
        return operandA / operandB;
    }
}
