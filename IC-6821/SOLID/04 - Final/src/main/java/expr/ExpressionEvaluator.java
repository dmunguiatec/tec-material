package expr;

import expr.repl.REPL;

public class ExpressionEvaluator {

    public static void main(String[] args) {
        try {
            ExpressionEvaluatorConfig config = new ExpressionEvaluatorConfig();
            REPL repl = config.repl();
            repl.loop();
        } catch (Exception e) {
            System.out.printf("Error inesperado: %s %s", e.getClass().getName(), e.getMessage());
        }
    }
}
