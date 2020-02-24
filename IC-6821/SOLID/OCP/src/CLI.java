import java.util.Optional;
import java.util.Scanner;

public class CLI {
    public void repl() {
        ExpressionParser expressionParser = new ExpressionParser();
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
                Expression expression = expressionParser.buildExpression(line);
                Optional<Integer> result = expression.evaluate();

                if (result.isPresent()) {
                    System.out.println(result.get());
                } else {
                    System.out.println("Error inesperado");
                }
            }
        }
    }
}
