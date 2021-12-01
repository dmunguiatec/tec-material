package expr.expression.ints;

import expr.expression.Expression;
import expr.expression.ExpressionType;

public class QuotExpression implements Expression {

    private final String operation;
    private final Integer operandA;
    private final Integer operandB;

    public QuotExpression(String operation, Integer operandA, Integer operandB) {
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Integer eval() {
        return operandA / operandB;
    }

    @Override
    public Double evalFP() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.INT;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", operandA, operation, operandB);
    }
}
