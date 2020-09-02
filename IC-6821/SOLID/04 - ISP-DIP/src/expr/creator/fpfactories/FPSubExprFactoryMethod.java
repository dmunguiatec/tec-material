package expr.creator.fpfactories;

import expr.fps.FPExpression;
import expr.fps.FPSubExpression;

public class FPSubExprFactoryMethod implements FPExprFactoryMethod {
    @Override
    public FPExpression create(double operandA, double operandB) {
        return new FPSubExpression(operandA, operandB);
    }
}
