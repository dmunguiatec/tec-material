public interface ExpressionCreator {
    Expression create(String operator, int operandA, int operandB);
    Expression create(String operator, double operandA, double operandB);
    void registerIntOperator(String operator, IntExprFactoryMethod factoryMethod);
    void registerFPOperator(String operator, FPExprFactoryMethod factoryMethod);
}
