package expr.expression.fps;

import expr.expression.Expression;
import expr.expression.ExpressionFactory;

import java.util.Optional;

public class FPMulExpressionFactory implements ExpressionFactory {
    @Override
    public Optional<Expression> create(String operation, Integer operandA, Integer operandB) {
        return Optional.empty();
    }

    @Override
    public Optional<Expression> create(String operation, Double operandA, Double operandB) {
        return Optional.of(new FPMulExpression(operation, operandA, operandB));
    }
}
