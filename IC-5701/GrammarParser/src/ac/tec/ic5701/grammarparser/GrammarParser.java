// Generated from /home/diegomunguia/Dropbox/TEC/II-2014/IC-5701 Compiladores e Intérpretes/GeneradoresParser/Grammar.g4 by ANTLR 4.4.1-dev
package ac.tec.ic5701.grammarparser;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarParser extends Parser {
//	static { RuntimeMetaData.checkVersion("4.4.1-dev", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__1=1, T__0=2, TERMINAL=3, NOTERMINAL=4, WS=5;
	public static final String[] tokenNames = {
		"<INVALID>", "'->'", "';'", "TERMINAL", "NOTERMINAL", "WS"
	};
	public static final int
		RULE_gram = 0, RULE_grule = 1, RULE_leftHand = 2, RULE_rightHand = 3;
	public static final String[] ruleNames = {
		"gram", "grule", "leftHand", "rightHand"
	};

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class GramContext extends ParserRuleContext {
		public List<GruleContext> grule() {
			return getRuleContexts(GruleContext.class);
		}
		public GruleContext grule(int i) {
			return getRuleContext(GruleContext.class,i);
		}
		public GramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gram; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGram(this);
		}
	}

	public final GramContext gram() throws RecognitionException {
		GramContext _localctx = new GramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_gram);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(9); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(8); grule();
				}
				}
				setState(11); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NOTERMINAL );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GruleContext extends ParserRuleContext {
		public LeftHandContext leftHand() {
			return getRuleContext(LeftHandContext.class,0);
		}
		public RightHandContext rightHand() {
			return getRuleContext(RightHandContext.class,0);
		}
		public GruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGrule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGrule(this);
		}
	}

	public final GruleContext grule() throws RecognitionException {
		GruleContext _localctx = new GruleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_grule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13); leftHand();
			setState(14); match(T__1);
			setState(15); rightHand();
			setState(16); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftHandContext extends ParserRuleContext {
		public TerminalNode NOTERMINAL() { return getToken(GrammarParser.NOTERMINAL, 0); }
		public LeftHandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftHand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterLeftHand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitLeftHand(this);
		}
	}

	public final LeftHandContext leftHand() throws RecognitionException {
		LeftHandContext _localctx = new LeftHandContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_leftHand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18); match(NOTERMINAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RightHandContext extends ParserRuleContext {
		public List<TerminalNode> TERMINAL() { return getTokens(GrammarParser.TERMINAL); }
		public List<TerminalNode> NOTERMINAL() { return getTokens(GrammarParser.NOTERMINAL); }
		public TerminalNode NOTERMINAL(int i) {
			return getToken(GrammarParser.NOTERMINAL, i);
		}
		public TerminalNode TERMINAL(int i) {
			return getToken(GrammarParser.TERMINAL, i);
		}
		public RightHandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightHand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRightHand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRightHand(this);
		}
	}

	public final RightHandContext rightHand() throws RecognitionException {
		RightHandContext _localctx = new RightHandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_rightHand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20);
				_la = _input.LA(1);
				if ( !(_la==TERMINAL || _la==NOTERMINAL) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TERMINAL || _la==NOTERMINAL );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\7\34\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\6\2\f\n\2\r\2\16\2\r\3\3\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\5\6\5\30\n\5\r\5\16\5\31\3\5\2\2\6\2\4\6\b\2\3\3\2\5\6\31\2\13"+
		"\3\2\2\2\4\17\3\2\2\2\6\24\3\2\2\2\b\27\3\2\2\2\n\f\5\4\3\2\13\n\3\2\2"+
		"\2\f\r\3\2\2\2\r\13\3\2\2\2\r\16\3\2\2\2\16\3\3\2\2\2\17\20\5\6\4\2\20"+
		"\21\7\3\2\2\21\22\5\b\5\2\22\23\7\4\2\2\23\5\3\2\2\2\24\25\7\6\2\2\25"+
		"\7\3\2\2\2\26\30\t\2\2\2\27\26\3\2\2\2\30\31\3\2\2\2\31\27\3\2\2\2\31"+
		"\32\3\2\2\2\32\t\3\2\2\2\4\r\31";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}