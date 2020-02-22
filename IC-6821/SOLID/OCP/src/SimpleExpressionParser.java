public class SimpleExpressionParser implements ExpressionParser {
    @Override
    public Expression buildExpression(String expressionLine) {
        String[] tokens = expressionLine.split("\\s");
        Integer operandA = Integer.parseInt(tokens[0]),
            operandB = Integer.parseInt(tokens[2]);

        BinaryOperation operation = BinaryOperation.NOOP;
        switch (tokens[1]) {
            case "+": operation = BinaryOperation.ADD; break;
            case "-": operation = BinaryOperation.SUB; break;
            case "*": operation = BinaryOperation.MUL; break;
        }

        return new BinaryExpression(operation, operandA, operandB);

    }
}
