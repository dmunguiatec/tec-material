package expr.expression.ints;

import expr.expression.Expression;

import java.util.Optional;

public class SubExpressionFactory implements IntExpressionFactory {
    @Override
    public Optional<Expression<Integer>> create(String operation, Integer operandA, Integer operandB) {
        return Optional.of(new SubExpression(operation, operandA, operandB));
    }
}
