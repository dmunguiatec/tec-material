package expr.creator.intfactories;

import expr.ints.IntExpression;

public interface IntExprFactoryMethod {
    IntExpression create(int operandA, int operandB);
}
