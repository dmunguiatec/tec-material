package expr.repl;

import expr.expression.Expression;
import expr.expression.ExpressionType;
import expr.parser.ExpressionParser;
import expr.parser.DefaultExpressionParser;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleREPL implements REPL {

    private ExpressionParser expressionParser;

    public ConsoleREPL() {
        this.expressionParser = new DefaultExpressionParser();
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
                final Optional<Expression> optionalExpression = expressionParser.parse(line);
                if (optionalExpression.isEmpty()) {
                    System.out.println("Expresión inválida");
                } else {
                    final Expression expression = optionalExpression.get();
                    if (expression.getType() == ExpressionType.INT) {
                        System.out.println(expression.eval());
                    } else {
                        System.out.println(expression.evalFP());
                    }
                }
            }
        }
    }
}
