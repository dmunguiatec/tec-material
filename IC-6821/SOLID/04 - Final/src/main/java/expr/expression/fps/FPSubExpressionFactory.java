package expr.expression.fps;

import expr.expression.Expression;

import java.util.Optional;

public class FPSubExpressionFactory implements FPExpressionFactory {
    @Override
    public Optional<Expression<Double>> create(String operation, Double operandA, Double operandB) {
        return Optional.of(new FPSubExpression(operation, operandA, operandB));
    }
}
