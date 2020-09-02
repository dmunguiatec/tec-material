package expr.ints;

public class QuotExpression implements IntExpression {

    private int operandA, operandB;

    public QuotExpression(int operandA, int operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Integer evaluate() {
        return operandA / operandB;
    }
}
