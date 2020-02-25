package expr.creator.intfactories;

import expr.ints.IntExpression;
import expr.ints.MulExpression;

public class MulExprFactoryMethod implements IntExprFactoryMethod {
    @Override
    public IntExpression create(int operandA, int operandB) {
        return new MulExpression(operandA, operandB);
    }
}
