package expr;

import expr.repl.ConsoleREPL;

public class ExpressionEvaluator {
    public static void main(String[] args) {
        ConsoleREPL cli = new ConsoleREPL();
        cli.loop();
    }
}
