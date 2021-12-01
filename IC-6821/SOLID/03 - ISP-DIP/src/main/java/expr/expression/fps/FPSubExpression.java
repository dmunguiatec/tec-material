package expr.expression.fps;

import expr.expression.Expression;
import expr.expression.ExpressionType;

public class FPSubExpression implements Expression {

    private final String operation;
    private final Double operandA;
    private final Double operandB;

    public FPSubExpression(String operation, Double operandA, Double operandB) {
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Integer eval() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double evalFP() {
        return operandA - operandB;
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.FP;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", operandA, operation, operandB);
    }
}
