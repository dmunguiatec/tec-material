package expr;

import expr.expression.DefaultExpressionFactory;
import expr.expression.ExpressionFactory;
import expr.expression.fps.FPAddExpressionFactory;
import expr.expression.fps.FPDivExpressionFactory;
import expr.expression.fps.FPExpressionFactory;
import expr.expression.fps.FPMulExpressionFactory;
import expr.expression.fps.FPSubExpressionFactory;
import expr.expression.ints.AddExpressionFactory;
import expr.expression.ints.IntExpressionFactory;
import expr.expression.ints.MulExpressionFactory;
import expr.expression.ints.QuotExpressionFactory;
import expr.expression.ints.SubExpressionFactory;
import expr.parser.DefaultExpressionParser;
import expr.parser.ExpressionParser;
import expr.repl.ConsoleREPL;
import expr.repl.REPL;

import java.util.HashMap;
import java.util.Map;

public class ExpressionEvaluatorConfig {
    public REPL repl() {
        return new ConsoleREPL(expressionParser());
    }

    public ExpressionParser expressionParser() {
        return new DefaultExpressionParser(expressionFactory());
    }

    public ExpressionFactory expressionFactory() {

        final Map<String, IntExpressionFactory> intFactories = new HashMap<>();
        intFactories.put("+", new AddExpressionFactory());
        intFactories.put("-", new SubExpressionFactory());
        intFactories.put("*", new MulExpressionFactory());
        intFactories.put("/", new QuotExpressionFactory());

        final Map<String, FPExpressionFactory> fpFactories = new HashMap<>();
        fpFactories.put("+", new FPAddExpressionFactory());
        fpFactories.put("-", new FPSubExpressionFactory());
        fpFactories.put("*", new FPMulExpressionFactory());
        fpFactories.put("/", new FPDivExpressionFactory());

        return new DefaultExpressionFactory(intFactories, fpFactories);
    }
}
