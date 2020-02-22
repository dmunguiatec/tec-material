import java.util.Optional;
import java.util.Scanner;

public class ConsoleCLI implements CLI {
    @Override
    public void repl() {
        ExpressionParser expressionParser = new SimpleExpressionParser();
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if ("exit".equals(line)) {
                done = true;
            } else {
                Expression expression = expressionParser.buildExpression(line);
                Optional<Integer> result = expression.evaluate();

                if (result.isPresent()) {
                    System.out.println(result.get());
                } else {
                    System.out.println("Unexpected error");
                }
            }
        }

        System.out.println("Finishing process");
    }
}
