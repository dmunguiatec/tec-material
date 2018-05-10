// Generated from /home/diegomunguia/Dropbox/TEC/II-2014/IC-5701 Compiladores e Intérpretes/GeneradoresParser/Grammar.g4 by ANTLR 4.4.1-dev
package ac.tec.ic5701.grammarparser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
//	static { RuntimeMetaData.checkVersion("4.4.1-dev", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__1=1, T__0=2, TERMINAL=3, NOTERMINAL=4, WS=5;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'"
	};
	public static final String[] ruleNames = {
		"T__1", "T__0", "TERMINAL", "NOTERMINAL", "EPSILON", "WS"
	};


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\7\62\b\1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\3\3\3\3\4\3\4\7"+
		"\4\27\n\4\f\4\16\4\32\13\4\3\4\3\4\6\4\36\n\4\r\4\16\4\37\3\4\5\4#\n\4"+
		"\3\5\6\5&\n\5\r\5\16\5\'\3\5\5\5+\n\5\3\6\3\6\3\7\3\7\3\7\3\7\3\30\2\b"+
		"\3\3\5\4\7\5\t\6\13\2\r\7\3\2\5\3\2C\\\3\2c|\5\2\13\f\17\17\"\"\66\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\r\3\2\2\2\3\17\3\2\2\2"+
		"\5\22\3\2\2\2\7\"\3\2\2\2\t%\3\2\2\2\13,\3\2\2\2\r.\3\2\2\2\17\20\7/\2"+
		"\2\20\21\7@\2\2\21\4\3\2\2\2\22\23\7=\2\2\23\6\3\2\2\2\24\30\7)\2\2\25"+
		"\27\13\2\2\2\26\25\3\2\2\2\27\32\3\2\2\2\30\31\3\2\2\2\30\26\3\2\2\2\31"+
		"\33\3\2\2\2\32\30\3\2\2\2\33#\7)\2\2\34\36\t\2\2\2\35\34\3\2\2\2\36\37"+
		"\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 #\3\2\2\2!#\5\13\6\2\"\24\3\2\2\2\""+
		"\35\3\2\2\2\"!\3\2\2\2#\b\3\2\2\2$&\t\3\2\2%$\3\2\2\2&\'\3\2\2\2\'%\3"+
		"\2\2\2\'(\3\2\2\2(*\3\2\2\2)+\7)\2\2*)\3\2\2\2*+\3\2\2\2+\n\3\2\2\2,-"+
		"\7\u03b7\2\2-\f\3\2\2\2./\t\4\2\2/\60\3\2\2\2\60\61\b\7\2\2\61\16\3\2"+
		"\2\2\b\2\30\37\"\'*\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}