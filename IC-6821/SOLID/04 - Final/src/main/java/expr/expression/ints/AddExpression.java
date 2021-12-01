package expr.expression.ints;

import expr.expression.Expression;

public class AddExpression implements Expression<Integer> {

    private final String operation;
    private final Integer operandA;
    private final Integer operandB;

    public AddExpression(String operation, Integer operandA, Integer operandB) {
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Integer eval() {
        return operandA + operandB;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", operandA, operation, operandB);
    }
}
