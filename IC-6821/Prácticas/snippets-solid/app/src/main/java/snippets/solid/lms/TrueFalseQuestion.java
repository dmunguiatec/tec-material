package snippets.solid.lms;

public interface TrueFalseQuestion extends Gradeable {
    boolean getCorrectOption();
    boolean getSelectedOption();
}
