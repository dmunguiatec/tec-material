package ac.tec.ic5701.grammarparser;

public class Symbol {
    private boolean terminal;
    private String label;

    public Symbol(boolean terminal, String label) {
        this.terminal = terminal;
        this.label = label;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;

        if (terminal != symbol.terminal) return false;
        if (!label.equals(symbol.label)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (terminal ? 1 : 0);
        result = 31 * result + label.hashCode();
        return result;
    }
}
