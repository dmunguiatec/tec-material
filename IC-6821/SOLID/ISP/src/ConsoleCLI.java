import java.util.Optional;
import java.util.Scanner;

public class ConsoleCLI implements CLI {
    @Override
    public void repl() {
        ExpressionParser expressionParser = new RegexExpressionParser();
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

                Optional result = Optional.empty();
                switch (expression.getType()) {
                    case INT: result = Optional.of(expression.evaluateInt()); break;
                    case FP: result = Optional.of(expression.evaluteFP()); break;
                }

                if (result.isPresent()) {
                    System.out.println(result.get());
                } else {
                    System.out.println("Expresión inválida: tipo desconocido");
                }
            }
        }
    }
}
