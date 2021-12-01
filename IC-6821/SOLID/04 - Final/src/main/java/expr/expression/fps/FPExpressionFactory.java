package expr.expression.fps;

import expr.expression.Expression;

import java.util.Optional;

public interface FPExpressionFactory {
    Optional<Expression<Double>> create(String operation, Double operandA, Double operandB);
}
