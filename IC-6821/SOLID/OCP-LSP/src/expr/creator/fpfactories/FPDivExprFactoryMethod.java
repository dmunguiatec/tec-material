package expr.creator.fpfactories;

import expr.Expression;
import expr.fps.FPDivExpression;

public class FPDivExprFactoryMethod implements FPExprFactoryMethod {
    @Override
    public Expression create(double operandA, double operandB) {
        return new FPDivExpression(operandA, operandB);
    }
}
