public class AddExpression implements Expression {

    private Integer operandA;
    private Integer operandB;

    public AddExpression(int operandA, int operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public ExpressionType getType() {
        return null;
    }

    @Override
    public int evaluateInt() {
        return operandA + operandB;
    }

    @Override
    public double evaluateFP() {
        throw new UnsupportedOperationException();
    }
}
