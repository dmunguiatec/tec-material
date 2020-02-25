package expr.creator.intfactories;

import expr.Expression;

public interface IntExprFactoryMethod {
    Expression create(int operandA, int operandB);
}
