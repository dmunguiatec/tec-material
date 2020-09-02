package expr.creator.fpfactories;

import expr.fps.FPExpression;
import expr.fps.FPMulExpression;

public class FPMulExprFactoryMethod implements FPExprFactoryMethod {
    @Override
    public FPExpression create(double operandA, double operandB) {
        return new FPMulExpression(operandA, operandB);
    }
}
