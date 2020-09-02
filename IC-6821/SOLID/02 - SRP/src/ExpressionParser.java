public class ExpressionParser {
    public Expression buildExpression(String expressionLine) {
        String[] tokens = expressionLine.split("\\s");
        Integer operandA = Integer.parseInt(tokens[0]),
            operandB = Integer.parseInt(tokens[2]);

        Operation operation = Operation.NOOP;
        switch (tokens[1]) {
            case "+": operation = Operation.ADD; break;
            case "-": operation = Operation.SUB; break;
            case "*": operation = Operation.MUL; break;
        }

        return new Expression(operation, operandA, operandB);
    }

    public boolean validate(String expressionLine) {
        return expressionLine.matches("\\-?[0-9]+\\s+[\\+\\-\\*]\\s\\-?[0-9]+");
    }
}
