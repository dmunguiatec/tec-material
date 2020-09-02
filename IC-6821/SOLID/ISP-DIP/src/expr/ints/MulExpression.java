package expr.ints;

public class MulExpression implements IntExpression {

    private int operandA, operandB;

    public MulExpression(int operandA, int operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Integer evaluate() {
        return operandA * operandB;
    }
}
