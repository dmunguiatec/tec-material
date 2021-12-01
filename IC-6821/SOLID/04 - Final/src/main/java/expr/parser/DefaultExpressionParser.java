package expr.parser;

import expr.expression.Expression;
import expr.expression.ExpressionFactory;

import java.util.Optional;

public class DefaultExpressionParser implements ExpressionParser {

    private static final int OPERAND_A = 0;
    private static final int OPERATOR = 1;
    private static final int OPERAND_B = 2;

    private final ExpressionFactory expressionFactory;

    public DefaultExpressionParser(ExpressionFactory expressionFactory) {
        this.expressionFactory = expressionFactory;
    }

    @Override
    public Optional<? extends Expression<?>> parse(String expressionLine) {

        if (!validate(expressionLine)) {
            return Optional.empty();
        }

        String[] tokens = expressionLine.split("\\s");
        if (hasFPOperands(tokens)) {
            double operandA = Double.parseDouble(tokens[OPERAND_A]);
            double operandB = Double.parseDouble(tokens[OPERAND_B]);

            return expressionFactory.create(tokens[OPERATOR], operandA, operandB);
        } else {
            int operandA = Integer.parseInt(tokens[OPERAND_A]);
            int operandB = Integer.parseInt(tokens[OPERAND_B]);

            return expressionFactory.create(tokens[OPERATOR], operandA, operandB);
        }
    }

    private boolean hasFPOperands(String[] tokens) {
        return tokens[OPERAND_A].contains(".") || tokens[OPERAND_B].contains(".");
    }

    private boolean validate(String expressionLine) {
        return expressionLine.matches("\\-?[0-9]+(\\.[0-9]*)?\\s+[\\+\\-\\*\\/]\\s\\-?[0-9]+(\\.[0-9]*)?");
    }
}
