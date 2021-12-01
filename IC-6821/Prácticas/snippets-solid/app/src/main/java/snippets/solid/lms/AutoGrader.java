package snippets.solid.lms;

import java.util.List;

public class AutoGrader {
    private Assessment assessment;

    public AutoGrader(Assessment assessment) {
        this.assessment = assessment;
    }

    public double grade() {
        int points = 0;
        final List<Gradeable> questions = assessment.getQuestions();
        for (Gradeable question : questions) {
            points += question.getAwardedPoints();
        }

        return (double) points / assessment.getTotalPoints();
    }
}

