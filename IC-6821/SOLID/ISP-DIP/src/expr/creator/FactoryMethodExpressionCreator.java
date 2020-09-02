package expr.creator;

import expr.creator.fpfactories.FPExprFactoryMethod;
import expr.creator.intfactories.IntExprFactoryMethod;
import expr.fps.FPExpression;
import expr.ints.IntExpression;

import java.util.HashMap;
import java.util.Map;

public class FactoryMethodExpressionCreator implements ExpressionCreator {

    private static FactoryMethodExpressionCreator instance = null;

    private Map<String, IntExprFactoryMethod> intFactories = new HashMap<>();
    private Map<String, FPExprFactoryMethod> fpFactories = new HashMap<>();

    public static FactoryMethodExpressionCreator getInstance() {
        if (instance == null) {
            instance = new FactoryMethodExpressionCreator();
        }

        return instance;
    }

    @Override
    public IntExpression create(String operator, int operandA, int operandB) {
        IntExprFactoryMethod factoryMethod = intFactories.get(operator);
        if (factoryMethod == null) {
            throw new IllegalArgumentException("Unknown operator " + operator);
        }

        return factoryMethod.create(operandA, operandB);
    }

    @Override
    public FPExpression create(String operator, double operandA, double operandB) {
        FPExprFactoryMethod factoryMethod = fpFactories.get(operator);
        if (factoryMethod == null) {
            throw new IllegalArgumentException("Unknown operator " + operator);
        }

        return factoryMethod.create(operandA, operandB);
    }

    @Override
    public void registerIntOperator(String operator, IntExprFactoryMethod factoryMethod) {
        intFactories.put(operator, factoryMethod);
    }

    @Override
    public void registerFPOperator(String operator, FPExprFactoryMethod factoryMethod) {
        fpFactories.put(operator, factoryMethod);
    }
}
