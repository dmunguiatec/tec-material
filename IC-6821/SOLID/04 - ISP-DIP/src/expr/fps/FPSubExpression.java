package expr.fps;

public class FPSubExpression implements FPExpression {

    private double operandA, operandB;

    public FPSubExpression(double operandA, double operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Double evaluate() {
        return operandA - operandB;
    }
}
