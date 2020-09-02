package expr.creator;

import expr.creator.fpfactories.FPExprFactoryMethod;
import expr.creator.intfactories.IntExprFactoryMethod;
import expr.fps.FPExpression;
import expr.ints.IntExpression;

public interface ExpressionCreator {
    IntExpression create(String operator, int operandA, int operandB);
    FPExpression create(String operator, double operandA, double operandB);
    void registerIntOperator(String operator, IntExprFactoryMethod factoryMethod);
    void registerFPOperator(String operator, FPExprFactoryMethod factoryMethod);
}
