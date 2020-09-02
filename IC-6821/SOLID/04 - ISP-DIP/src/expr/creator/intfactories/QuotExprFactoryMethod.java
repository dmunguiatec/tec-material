package expr.creator.intfactories;

import expr.ints.IntExpression;
import expr.ints.QuotExpression;

public class QuotExprFactoryMethod implements IntExprFactoryMethod {
    @Override
    public IntExpression create(int operandA, int operandB) {
        return new QuotExpression(operandA, operandB);
    }
}
