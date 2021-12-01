package expr.repl;

import expr.expression.Expression;
import expr.parser.ExpressionParser;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleREPL {

    public void loop() {
        ExpressionParser expressionParser = new ExpressionParser();
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if ("salir".equals(line)) {
                done = true;
                System.out.println("Finalizando el proceso");
            } else {
                Optional<Expression> expression = expressionParser.parse(line);
                if (expression.isEmpty()) {
                    System.out.println("Expresión inválida: error de sintaxis");
                }

                System.out.println(expression.get().evaluate());
            }
        }
    }
}
