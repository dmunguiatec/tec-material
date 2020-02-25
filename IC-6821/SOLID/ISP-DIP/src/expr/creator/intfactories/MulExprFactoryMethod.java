package expr.creator.intfactories;

import expr.Expression;
import expr.ints.MulExpression;

public class MulExprFactoryMethod implements IntExprFactoryMethod {
    @Override
    public Expression create(int operandA, int operandB) {
        return new MulExpression(operandA, operandB);
    }
}
