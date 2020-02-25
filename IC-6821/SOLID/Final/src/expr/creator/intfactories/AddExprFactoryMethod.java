package expr.creator.intfactories;

import expr.ints.AddExpression;
import expr.ints.IntExpression;

public class AddExprFactoryMethod implements IntExprFactoryMethod {
    @Override
    public IntExpression create(int operandA, int operandB) {
        return new AddExpression(operandA, operandB);
    }
}
