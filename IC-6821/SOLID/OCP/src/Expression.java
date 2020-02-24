import java.util.Optional;

public class Expression {

    private Operation operation;
    private Integer operandA;
    private Integer operandB;

    public Expression(Operation operation, Integer operandA, Integer operandB) {
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    public Optional<Integer> evaluate() {
        Optional<Integer> result = Optional.empty();

        if (Operation.ADD.equals(this.operation)) {
            result = Optional.of(operandA + operandB);
        } else if (Operation.SUB.equals(this.operation)) {
            result = Optional.of(operandA - operandB);
        } else if (Operation.MUL.equals(this.operation)) {
            result = Optional.of(operandA * operandB);
        }

        return result;
    }
}
