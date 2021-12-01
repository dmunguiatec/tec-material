package expr.expression.ints;

import expr.expression.Expression;

import java.util.Optional;

public interface IntExpressionFactory {
    Optional<Expression<Integer>> create(String operation, Integer operandA, Integer operandB);
}
