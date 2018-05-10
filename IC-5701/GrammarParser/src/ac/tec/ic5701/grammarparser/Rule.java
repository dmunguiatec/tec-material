package ac.tec.ic5701.grammarparser;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private Symbol leftHand;
    private List<Symbol> rightHand;

    public Rule(Symbol leftHand) {
        this.leftHand = leftHand;
        this.rightHand = new ArrayList<Symbol>();
    }

    public Symbol getLeftHand() {
        return leftHand;
    }

    public List<Symbol> getRightHand() {
        return rightHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (!leftHand.equals(rule.leftHand)) return false;
        if (!rightHand.equals(rule.rightHand)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = leftHand.hashCode();
        result = 31 * result + rightHand.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(leftHand.getLabel()).append(" -> ");
        for (Symbol symbol : rightHand) {
            builder.append(symbol.getLabel()).append(' ');
        }

        return builder.toString();
    }
}
