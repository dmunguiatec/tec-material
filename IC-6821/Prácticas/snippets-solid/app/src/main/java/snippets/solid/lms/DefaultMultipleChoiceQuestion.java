package snippets.solid.lms;

public class DefaultMultipleChoiceQuestion implements MultipleChoiceQuestion {

    private int currentSelection;
    private int correctOption;
    private int pointValue;

    public DefaultMultipleChoiceQuestion(int currentSelection, int correctOption, int pointValue) {
        this.currentSelection = currentSelection;
        this.correctOption = correctOption;
        this.pointValue = pointValue;
    }

    @Override
    public int getCurrentSelection() {
        return currentSelection;
    }

    @Override
    public int getCorrectOption() {
        return correctOption;
    }

    @Override
    public int getPointValue() {
        return pointValue;
    }

    @Override
    public int getAwardedPoints() {
        return (getCurrentSelection() == getCorrectOption()) ? getPointValue() : 0;
    }

}
