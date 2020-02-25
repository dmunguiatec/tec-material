package expr.creator.intfactories;

import expr.ints.IntExpression;
import expr.ints.SubExpression;

public class SubExprFactoryMethod implements IntExprFactoryMethod {
    @Override
    public IntExpression create(int operandA, int operandB) {
        return new SubExpression(operandA, operandB);
    }
}
