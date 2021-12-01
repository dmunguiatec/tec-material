package expr.expression.fps;

import expr.expression.Expression;

public class FPDivExpression implements Expression<Double> {

    private final String operation;
    private final Double operandA;
    private final Double operandB;

    public FPDivExpression(String operation, Double operandA, Double operandB) {
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Double eval() {
        return operandA / operandB;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", operandA, operation, operandB);
    }
}
