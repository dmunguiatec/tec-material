package expr.parser;

import expr.expression.Expression;

import java.util.Optional;

public interface ExpressionParser {
    Optional<Expression> parse(String expressionLine);
}
