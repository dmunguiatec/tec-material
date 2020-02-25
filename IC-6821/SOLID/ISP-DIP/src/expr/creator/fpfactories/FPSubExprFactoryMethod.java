package expr.creator.fpfactories;

import expr.Expression;
import expr.fps.FPSubExpression;

public class FPSubExprFactoryMethod implements FPExprFactoryMethod {
    @Override
    public Expression create(double operandA, double operandB) {
        return new FPSubExpression(operandA, operandB);
    }
}
