import java.util.Optional;

public class BinaryExpression implements Expression {

    private BinaryOperation operation;
    private Integer operandA;
    private Integer operandB;

    public BinaryExpression(BinaryOperation operation, Integer operandA, Integer operandB) {
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
    }

    @Override
    public Optional<Integer> evaluate() {
        Optional<Integer> result = Optional.empty();

        if (BinaryOperation.ADD.equals(this.operation)) {
            result = Optional.of(operandA + operandB);
        } else if (BinaryOperation.SUB.equals(this.operation)) {
            result = Optional.of(operandA - operandB);
        } else if (BinaryOperation.MUL.equals(this.operation)) {
            result = Optional.of(operandA * operandB);
        }

        return result;
    }
}
