public interface Expression {
    ExpressionType getType();
    int evaluateInt();
    double evaluateFP();
}
