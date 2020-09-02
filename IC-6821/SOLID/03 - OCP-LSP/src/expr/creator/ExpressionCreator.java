package expr.creator;

import expr.Expression;
import expr.creator.fpfactories.FPExprFactoryMethod;
import expr.creator.intfactories.IntExprFactoryMethod;

public interface ExpressionCreator {
    Expression create(String operator, int operandA, int operandB);
    Expression create(String operator, double operandA, double operandB);
    void registerIntOperator(String operator, IntExprFactoryMethod factoryMethod);
    void registerFPOperator(String operator, FPExprFactoryMethod factoryMethod);
}
