package ac.tec.ic5701.grammarparser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapBuilderListener extends GrammarBaseListener {
    private boolean processingRightHand = false;
    private Rule currentRule = null;
    private List<Rule> rules;

    @Override
    public void enterGram(@NotNull GrammarParser.GramContext ctx) {
        rules = new ArrayList<Rule>();
    }

    @Override
    public void exitLeftHand(@NotNull GrammarParser.LeftHandContext ctx) {
        String label = ctx.NOTERMINAL().getSymbol().getText();
        Symbol leftHand = new Symbol(false, label);
        Rule rule = new Rule(leftHand);
        rules.add(rule);

        currentRule = rule;
    }

    @Override
    public void enterRightHand(@NotNull GrammarParser.RightHandContext ctx) {
        processingRightHand = true;
    }

    @Override
    public void exitRightHand(@NotNull GrammarParser.RightHandContext ctx) {
        currentRule = null;
        processingRightHand = false;
    }

    @Override
    public void visitTerminal(@NotNull TerminalNode node) {
        if (processingRightHand) {
            currentRule.getRightHand().add(new Symbol(
                    GrammarParser.TERMINAL == node.getSymbol().getType(),
                    node.getSymbol().getText()
            ));
        }
    }

    public List<Rule> getRules() {
        return rules;
    }
}
