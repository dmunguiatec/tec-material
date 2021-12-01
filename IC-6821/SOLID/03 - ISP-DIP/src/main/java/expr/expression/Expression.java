package expr.expression;

public interface Expression {
    Integer eval();
    Double evalFP();
    ExpressionType getType();
}
