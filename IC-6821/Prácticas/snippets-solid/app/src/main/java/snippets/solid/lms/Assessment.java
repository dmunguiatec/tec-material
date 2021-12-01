package snippets.solid.lms;

import java.util.List;

public interface Assessment {
    List<Gradeable> getQuestions();

    int getTotalPoints();
}
