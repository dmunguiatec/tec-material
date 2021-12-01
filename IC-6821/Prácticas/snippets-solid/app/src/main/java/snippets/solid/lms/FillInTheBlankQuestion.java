package snippets.solid.lms;

public interface FillInTheBlankQuestion extends Gradeable {
    String getStudentAnswer();

    String getCorrectAnswer();

    int getPointValue();
}
