package expr.creator.fpfactories;

import expr.fps.FPExpression;

public interface FPExprFactoryMethod {
    FPExpression create(double operandA, double operandB);
}
