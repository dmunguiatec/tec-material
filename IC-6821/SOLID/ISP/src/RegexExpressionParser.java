public class RegexExpressionParser implements ExpressionParser {

    public static final int OPERAND_A = 0;
    public static final int OPERATOR = 1;
    public static final int OPERAND_B = 2;


    @Override
    public Expression buildExpression(String expressionLine) {
        ExpressionCreator expressionCreator = FactoryMethodExpressionCreator.getInstance();

        String[] tokens = expressionLine.split("\\s");
        if (tokens[OPERAND_A].contains(".") || tokens[OPERAND_B].contains(".")) {
            double operandA = Double.parseDouble(tokens[OPERAND_A]);
            double operandB = Double.parseDouble(tokens[OPERAND_B]);

            return expressionCreator.create(tokens[OPERATOR], operandA, operandB);
        } else {
            int operandA = Integer.parseInt(tokens[OPERAND_A]);
            int operandB = Integer.parseInt(tokens[OPERAND_B]);

            return expressionCreator.create(tokens[OPERATOR], operandA, operandB);
        }
    }

    @Override
    public boolean validate(String expressionLine) {
        return expressionLine.matches("\\-?[0-9]+(\\.[0-9]*)?\\s+[\\+\\-\\*]\\s\\-?[0-9]+(\\.[0-9]*)?");
    }
}
