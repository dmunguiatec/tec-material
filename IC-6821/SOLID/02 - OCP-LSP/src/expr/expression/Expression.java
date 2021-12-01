package expr.expression;

public class Expression {

    private final Operation operation;
    private final Integer operandA;
    private final Integer operandB;

    public Expression(Operation operation, Integer operandA, Integer operandB) {
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    public Integer evaluate() {
        Integer result = null;

        if (Operation.ADD.equals(this.operation)) {
            result = operandA + operandB;
        } else if (Operation.SUB.equals(this.operation)) {
            result = operandA - operandB;
        } else if (Operation.MUL.equals(this.operation)) {
            result = operandA * operandB;
        }

        return result;
    }
}
