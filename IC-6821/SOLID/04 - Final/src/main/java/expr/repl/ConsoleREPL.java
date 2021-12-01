package expr.repl;

import expr.expression.Expression;
import expr.parser.ExpressionParser;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleREPL implements REPL {

    private final ExpressionParser expressionParser;

    public ConsoleREPL(ExpressionParser expressionParser) {
        this.expressionParser = expressionParser;
    }

    @Override
    public void loop() {
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if ("salir".equals(line)) {
                done = true;
                System.out.println("Finalizando el proceso");
            } else {
                final Optional<? extends Expression<?>> optionalExpression = expressionParser.parse(line);
                if (optionalExpression.isEmpty()) {
                    System.out.println("Expresión inválida");
                } else {
                    final Expression<?> expression = optionalExpression.get();
                    System.out.println(expression.eval());
                }
            }
        }
    }
}
