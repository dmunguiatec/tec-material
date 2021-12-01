package expr.expression.fps;

import expr.expression.Expression;

import java.util.Optional;

public class FPDivExpressionFactory implements FPExpressionFactory {
    @Override
    public Optional<Expression<Double>> create(String operation, Double operandA, Double operandB) {
        return Optional.of(new FPDivExpression(operation, operandA, operandB));
    }
}
