package expr;

import expr.expression.DefaultExpressionFactory;
import expr.expression.ints.AddExpressionFactory;
import expr.expression.ints.SubExpressionFactory;
import expr.expression.ints.MulExpressionFactory;
import expr.expression.ints.QuotExpressionFactory;
import expr.expression.fps.FPAddExpressionFactory;
import expr.expression.fps.FPSubExpressionFactory;
import expr.expression.fps.FPMulExpressionFactory;
import expr.expression.fps.FPDivExpressionFactory;
import expr.repl.ConsoleREPL;
import expr.repl.REPL;

public class ExpressionEvaluator {

    public static void main(String[] args) {
        try {
            initFactories();

            REPL repl = new ConsoleREPL();
            repl.loop();

        } catch (Exception e) {
            System.out.printf("Error inesperado: %s %s", e.getClass().getName(), e.getMessage());
        }
    }

    private static void initFactories() {
        DefaultExpressionFactory.registerIntFactory("+", new AddExpressionFactory());
        DefaultExpressionFactory.registerIntFactory("-", new SubExpressionFactory());
        DefaultExpressionFactory.registerIntFactory("*", new MulExpressionFactory());
        DefaultExpressionFactory.registerIntFactory("/", new QuotExpressionFactory());

        DefaultExpressionFactory.registerFPFactory("-", new FPSubExpressionFactory());
        DefaultExpressionFactory.registerFPFactory("+", new FPAddExpressionFactory());
        DefaultExpressionFactory.registerFPFactory("*", new FPMulExpressionFactory());
        DefaultExpressionFactory.registerFPFactory("/", new FPDivExpressionFactory());
    }

}
