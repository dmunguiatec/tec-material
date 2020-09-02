package expr.creator.intfactories;

import expr.Expression;
import expr.ints.QuotExpression;

public class QuotExprFactoryMethod implements IntExprFactoryMethod {
    @Override
    public Expression create(int operandA, int operandB) {
        return new QuotExpression(operandA, operandB);
    }
}
