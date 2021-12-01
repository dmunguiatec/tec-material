package expr.expression.ints;

import expr.expression.Expression;

import java.util.Optional;

public class QuotExpressionFactory implements IntExpressionFactory {
    @Override
    public Optional<Expression<Integer>> create(String operation, Integer operandA, Integer operandB) {
        return Optional.of(new QuotExpression(operation, operandA, operandB));
    }
}
