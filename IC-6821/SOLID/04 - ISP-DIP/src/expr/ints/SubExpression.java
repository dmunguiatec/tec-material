package expr.ints;

public class SubExpression implements IntExpression {

    private int operandA, operandB;

    public SubExpression(int operandA, int operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Integer evaluate() {
        return operandA - operandB;
    }
}
