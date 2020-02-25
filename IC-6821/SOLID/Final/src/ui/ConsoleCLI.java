package ui;

import expr.Expression;
import expr.parser.ExpressionParser;
import expr.parser.RegexExpressionParser;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleCLI implements CLI {

    private ExpressionParser expressionParser;

    public ConsoleCLI(ExpressionParser expressionParser) {
        this.expressionParser = expressionParser;
    }

    @Override
    public void repl() {
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if ("salir".equals(line)) {
                done = true;
                System.out.println("Finalizando el proceso");
            } else if (!expressionParser.validate(line)) {
                System.out.println("Expresión inválida: error de sintaxis");
            } else {
                Expression<?> expression = expressionParser.buildExpression(line);
                Object value = expression.evaluate();
                System.out.println(value);
            }
        }
    }
}
