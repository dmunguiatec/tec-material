package expr.expression;

import java.util.Optional;

public interface ExpressionFactory {
    Optional<Expression<Integer>> create(String operation, Integer operandA, Integer operandB);
    Optional<Expression<Double>> create(String operation, Double operandA, Double operandB);
}
