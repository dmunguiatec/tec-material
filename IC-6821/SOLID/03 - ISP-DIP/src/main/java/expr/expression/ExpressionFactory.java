package expr.expression;

import java.util.Optional;

public interface ExpressionFactory {
    Optional<Expression> create(String operation, Integer operandA, Integer operandB);
    Optional<Expression> create(String operation, Double operandA, Double operandB);
}
