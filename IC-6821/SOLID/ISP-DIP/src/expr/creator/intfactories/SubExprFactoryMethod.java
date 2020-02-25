package expr.creator.intfactories;

import expr.Expression;
import expr.ints.SubExpression;

public class SubExprFactoryMethod implements IntExprFactoryMethod {
    @Override
    public Expression create(int operandA, int operandB) {
        return new SubExpression(operandA, operandB);
    }
}
