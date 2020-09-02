package expr.ints;

public class AddExpression implements IntExpression {

    private int operandA, operandB;

    public AddExpression(int operandA, int operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Integer evaluate() {
        return operandA + operandB;
    }
}
