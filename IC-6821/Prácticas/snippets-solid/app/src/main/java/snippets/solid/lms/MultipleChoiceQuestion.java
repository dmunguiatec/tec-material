package snippets.solid.lms;

public interface MultipleChoiceQuestion extends Gradeable {
    int getCurrentSelection();

    int getCorrectOption();

    int getPointValue();
}
