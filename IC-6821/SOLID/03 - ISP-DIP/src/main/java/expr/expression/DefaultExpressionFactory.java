package expr.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultExpressionFactory implements ExpressionFactory {

    private static final Map<String, ExpressionFactory> intFactories = new HashMap<>();
    private static final Map<String, ExpressionFactory> fpFactories = new HashMap<>();

    public static void registerFPFactory(String operator, ExpressionFactory expressionFactory) {
        fpFactories.put(operator, expressionFactory);
    }

    public static void registerIntFactory(String operator, ExpressionFactory expressionFactory) {
        intFactories.put(operator, expressionFactory);
    }

    @Override
    public Optional<Expression> create(String operation, Integer operandA, Integer operandB) {
        if (!intFactories.containsKey(operation)) {
            return Optional.empty();
        }

        final ExpressionFactory factory = intFactories.get(operation);
        return factory.create(operation, operandA, operandB);
    }

    @Override
    public Optional<Expression> create(String operation, Double operandA, Double operandB) {
        if (!fpFactories.containsKey(operation)) {
            return Optional.empty();
        }

        final ExpressionFactory factory = fpFactories.get(operation);
        return factory.create(operation, operandA, operandB);
    }
}
