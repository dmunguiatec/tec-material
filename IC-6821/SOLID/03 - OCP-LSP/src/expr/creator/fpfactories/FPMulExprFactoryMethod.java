package expr.creator.fpfactories;

import expr.Expression;
import expr.fps.FPMulExpression;

public class FPMulExprFactoryMethod implements FPExprFactoryMethod {
    @Override
    public Expression create(double operandA, double operandB) {
        return new FPMulExpression(operandA, operandB);
    }
}
