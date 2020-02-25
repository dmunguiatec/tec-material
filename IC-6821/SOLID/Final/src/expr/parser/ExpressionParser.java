package expr.parser;

import expr.Expression;

public interface ExpressionParser {
    Expression<?> buildExpression(String expressionLine);

    boolean validate(String expressionLine);
}
