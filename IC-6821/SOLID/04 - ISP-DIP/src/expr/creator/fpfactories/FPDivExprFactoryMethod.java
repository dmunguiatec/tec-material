package expr.creator.fpfactories;

import expr.fps.FPDivExpression;
import expr.fps.FPExpression;

public class FPDivExprFactoryMethod implements FPExprFactoryMethod {
    @Override
    public FPExpression create(double operandA, double operandB) {
        return new FPDivExpression(operandA, operandB);
    }
}
