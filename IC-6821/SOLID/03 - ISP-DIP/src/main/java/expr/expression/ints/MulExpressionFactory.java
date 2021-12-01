package expr.expression.ints;

import expr.expression.Expression;
import expr.expression.ExpressionFactory;

import java.util.Optional;

public class MulExpressionFactory implements ExpressionFactory {
    @Override
    public Optional<Expression> create(String operation, Integer operandA, Integer operandB) {
        return Optional.of(new MulExpression(operation, operandA, operandB));
    }

    @Override
    public Optional<Expression> create(String operation, Double operandA, Double operandB) {
        return Optional.empty();
    }
}
