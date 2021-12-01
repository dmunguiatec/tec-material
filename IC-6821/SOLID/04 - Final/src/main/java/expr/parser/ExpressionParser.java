package expr.parser;

import expr.expression.Expression;

import java.util.Optional;

public interface ExpressionParser {
    Optional<? extends Expression<?>> parse(String expressionLine);
}
