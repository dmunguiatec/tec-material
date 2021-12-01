package expr;

public class Expression {
    enum Operation {
        ADD, SUB, MUL, NOOP
    }

    private Operation operation;
    private Integer operandA;
    private Integer operandB;

    public Expression(Operation operation, Integer operandA, Integer operandB) {
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    public Operation getOperation() {
        return operation;
    }

    public Integer getOperandA() {
        return operandA;
    }

    public Integer getOperandB() {
        return operandB;
    }
}
