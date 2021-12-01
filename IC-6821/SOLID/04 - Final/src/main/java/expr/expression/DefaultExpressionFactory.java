package expr.expression;

import expr.expression.fps.FPExpressionFactory;
import expr.expression.ints.IntExpressionFactory;

import java.util.Map;
import java.util.Optional;

public class DefaultExpressionFactory implements ExpressionFactory {

    private final Map<String, IntExpressionFactory> intFactories;
    private final Map<String, FPExpressionFactory> fpFactories;

    public DefaultExpressionFactory(Map<String, IntExpressionFactory> intFactories,
                                    Map<String, FPExpressionFactory> fpFactories) {

        this.intFactories = intFactories;
        this.fpFactories = fpFactories;
    }

    @Override
    public Optional<Expression<Integer>> create(String operation, Integer operandA, Integer operandB) {
        if (!intFactories.containsKey(operation)) {
            return Optional.empty();
        }

        final IntExpressionFactory factory = intFactories.get(operation);
        return factory.create(operation, operandA, operandB);
    }

    @Override
    public Optional<Expression<Double>> create(String operation, Double operandA, Double operandB) {
        if (!fpFactories.containsKey(operation)) {
            return Optional.empty();
        }

        final FPExpressionFactory factory = fpFactories.get(operation);
        return factory.create(operation, operandA, operandB);
    }
}
