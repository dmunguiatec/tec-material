package expr.creator.fpfactories;

import expr.Expression;
import expr.fps.FPAddExpression;

public class FPAddExprFactoryMethod implements FPExprFactoryMethod {
    @Override
    public Expression create(double operandA, double operandB) {
        return new FPAddExpression(operandA, operandB);
    }
}
