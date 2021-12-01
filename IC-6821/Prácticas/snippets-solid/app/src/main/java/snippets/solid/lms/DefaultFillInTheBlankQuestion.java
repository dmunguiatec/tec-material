package snippets.solid.lms;

public class DefaultFillInTheBlankQuestion implements FillInTheBlankQuestion {
    private String studentAnswer;
    private String correctAnswer;
    private int pointValue;

    public DefaultFillInTheBlankQuestion(String studentAnswer, String correctAnswer, int pointValue) {
        this.studentAnswer = studentAnswer;
        this.correctAnswer = correctAnswer;
        this.pointValue = pointValue;
    }

    @Override
    public String getStudentAnswer() {
        return studentAnswer;
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public int getPointValue() {
        return pointValue;
    }

    @Override
    public int getAwardedPoints() {
        return (getStudentAnswer().equals(getCorrectAnswer())) ? getPointValue() : 0;
    }
}
