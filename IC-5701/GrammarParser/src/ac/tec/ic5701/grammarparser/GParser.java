package ac.tec.ic5701.grammarparser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public class GParser {
    public List<Rule> parse(Reader inputReader) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(inputReader);
        GrammarLexer lexer = new GrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        ParseTree tree = parser.gram();
        MapBuilderListener listener = new MapBuilderListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        return listener.getRules();
    }
}
