package expr.fps;

public class FPDivExpression implements FPExpression {

    private double operandA, operandB;

    public FPDivExpression(double operandA, double operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Double evaluate() {
        return operandA / operandB;
    }
}
