package expr.fps;

public class FPMulExpression implements FPExpression {

    private double operandA, operandB;

    public FPMulExpression(double operandA, double operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Double evaluate() {
        return operandA * operandB;
    }
}
