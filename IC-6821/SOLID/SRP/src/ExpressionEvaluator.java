import java.util.Optional;
import java.util.Scanner;

public class ExpressionEvaluator {
    public static void main(String[] args) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        while (!done) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if ("exit".equals(line)) {
                done = true;
            } else {
                Expression expression = expressionEvaluator.parse(line);
                Expression.Operation operation = expression.getOperation();

                Optional<Integer> result = Optional.empty();
                if (Expression.Operation.ADD.equals(operation)) {
                    result = Optional.of(expression.getOperandA() + expression.getOperandB());
                } else if (Expression.Operation.SUB.equals(operation)) {
                    result = Optional.of(expression.getOperandA() - expression.getOperandB());
                } else if (Expression.Operation.MUL.equals(operation)) {
                    result = Optional.of(expression.getOperandA() * expression.getOperandB());
                }

                if (result.isPresent()) {
                    System.out.println(result.get());
                } else {
                    System.out.println("Unexpected error");
                }
            }
        }

        System.out.println("Finishing process");
    }

    private Expression parse(String inputLine) {
        String[] tokens = inputLine.split("\\s");
        Integer operandA = Integer.parseInt(tokens[0]),
                operandB = Integer.parseInt(tokens[2]);

        Expression.Operation operation = Expression.Operation.NOOP;
        switch (tokens[1]) {
            case "+": operation = Expression.Operation.ADD; break;
            case "-": operation = Expression.Operation.SUB; break;
            case "*": operation = Expression.Operation.MUL; break;
        }

        return new Expression(operation, operandA, operandB);
    }
}
