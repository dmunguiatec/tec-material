package expr.creator.fpfactories;

import expr.fps.FPAddExpression;
import expr.fps.FPExpression;

public class FPAddExprFactoryMethod implements FPExprFactoryMethod {
    @Override
    public FPExpression create(double operandA, double operandB) {
        return new FPAddExpression(operandA, operandB);
    }
}
