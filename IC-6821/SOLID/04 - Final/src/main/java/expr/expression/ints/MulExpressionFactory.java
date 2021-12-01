package expr.expression.ints;

import expr.expression.Expression;

import java.util.Optional;

public class MulExpressionFactory implements IntExpressionFactory {
    @Override
    public Optional<Expression<Integer>> create(String operation, Integer operandA, Integer operandB) {
        return Optional.of(new MulExpression(operation, operandA, operandB));
    }
}
