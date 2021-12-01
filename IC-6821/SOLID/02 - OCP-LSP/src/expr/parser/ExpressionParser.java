package expr.parser;

import expr.expression.Expression;
import expr.expression.Operation;

import java.util.Optional;

public class ExpressionParser {
    public Optional<Expression> parse(String expressionLine) {
        try {
            if (!validate(expressionLine)) {
                return Optional.empty();
            }

            final String[] tokens = expressionLine.split("\\s");
            final Integer operandA = Integer.parseInt(tokens[0]);
            final Integer operandB = Integer.parseInt(tokens[2]);

            Operation operation;
            switch (tokens[1]) {
                case "+":
                    operation = Operation.ADD;
                    break;
                case "-":
                    operation = Operation.SUB;
                    break;
                case "*":
                    operation = Operation.MUL;
                    break;
                default:
                    operation = null;
                    break;
            }

            return (operation == null) ? Optional.empty() : Optional.of(new Expression(operation, operandA, operandB));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private boolean validate(String expressionLine) {
        return expressionLine.matches("\\-?[0-9]+\\s+[\\+\\-\\*]\\s+\\-?[0-9]+");
    }
}
