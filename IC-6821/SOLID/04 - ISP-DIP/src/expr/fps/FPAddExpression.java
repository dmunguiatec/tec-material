package expr.fps;

public class FPAddExpression implements FPExpression {

    private double operandA, operandB;

    public FPAddExpression(double operandA, double operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Double evaluate() {
        return operandA + operandB;
    }
}
