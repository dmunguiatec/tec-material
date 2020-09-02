package expr.creator.intfactories;

import expr.Expression;
import expr.ints.AddExpression;

public class AddExprFactoryMethod implements IntExprFactoryMethod {
    @Override
    public Expression create(int operandA, int operandB) {
        return new AddExpression(operandA, operandB);
    }
}
