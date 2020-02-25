package expr.creator.fpfactories;

import expr.Expression;

public interface FPExprFactoryMethod {
    Expression create(double operandA, double operandB);
}
