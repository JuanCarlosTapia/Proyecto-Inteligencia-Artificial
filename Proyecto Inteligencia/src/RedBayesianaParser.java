// Generated from RedBayesiana.txt by ANTLR 4.6
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RedBayesianaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, COMMENT=7, WS=8, NUM=9, 
		APOS=10, ID=11;
	public static final int
		RULE_descripcion = 0, RULE_expresion = 1, RULE_logica = 2, RULE_evento = 3, 
		RULE_parametro = 4;
	public static final String[] ruleNames = {
		"descripcion", "expresion", "logica", "evento", "parametro"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'P('", "')'", "'='", "'!'", "','", "'|'", null, null, null, "'''"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "COMMENT", "WS", "NUM", "APOS", 
		"ID"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RedBayesiana.txt"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RedBayesianaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DescripcionContext extends ParserRuleContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public DescripcionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_descripcion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RedBayesianaListener ) ((RedBayesianaListener)listener).enterDescripcion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RedBayesianaListener ) ((RedBayesianaListener)listener).exitDescripcion(this);
		}
	}

	public final DescripcionContext descripcion() throws RecognitionException {
		DescripcionContext _localctx = new DescripcionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_descripcion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(10);
				expresion();
				}
				}
				setState(15);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class ExpresionContext extends ParserRuleContext {
		public ParametroContext parametro() {
			return getRuleContext(ParametroContext.class,0);
		}
		public TerminalNode NUM() { return getToken(RedBayesianaParser.NUM, 0); }
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RedBayesianaListener ) ((RedBayesianaListener)listener).enterExpresion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RedBayesianaListener ) ((RedBayesianaListener)listener).exitExpresion(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		ExpresionContext _localctx = new ExpresionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expresion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(T__0);
			setState(17);
			parametro();
			setState(18);
			match(T__1);
			setState(19);
			match(T__2);
			setState(20);
			match(NUM);
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

	public static class LogicaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RedBayesianaParser.ID, 0); }
		public LogicaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RedBayesianaListener ) ((RedBayesianaListener)listener).enterLogica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RedBayesianaListener ) ((RedBayesianaListener)listener).exitLogica(this);
		}
	}

	public final LogicaContext logica() throws RecognitionException {
		LogicaContext _localctx = new LogicaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_logica);
		try {
			setState(25);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				match(ID);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(23);
				match(T__3);
				setState(24);
				match(ID);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class EventoContext extends ParserRuleContext {
		public List<LogicaContext> logica() {
			return getRuleContexts(LogicaContext.class);
		}
		public LogicaContext logica(int i) {
			return getRuleContext(LogicaContext.class,i);
		}
		public EventoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RedBayesianaListener ) ((RedBayesianaListener)listener).enterEvento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RedBayesianaListener ) ((RedBayesianaListener)listener).exitEvento(this);
		}
	}

	public final EventoContext evento() throws RecognitionException {
		EventoContext _localctx = new EventoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_evento);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			logica();
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(28);
				match(T__4);
				setState(29);
				logica();
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class ParametroContext extends ParserRuleContext {
		public List<EventoContext> evento() {
			return getRuleContexts(EventoContext.class);
		}
		public EventoContext evento(int i) {
			return getRuleContext(EventoContext.class,i);
		}
		public ParametroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RedBayesianaListener ) ((RedBayesianaListener)listener).enterParametro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RedBayesianaListener ) ((RedBayesianaListener)listener).exitParametro(this);
		}
	}

	public final ParametroContext parametro() throws RecognitionException {
		ParametroContext _localctx = new ParametroContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parametro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			evento();
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(36);
				match(T__5);
				setState(37);
				evento();
				}
			}

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\r+\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\7\2\16\n\2\f\2\16\2\21\13\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\5\4\34\n\4\3\5\3\5\3\5\7\5!\n\5\f\5\16\5$\13"+
		"\5\3\6\3\6\3\6\5\6)\n\6\3\6\2\2\7\2\4\6\b\n\2\2)\2\17\3\2\2\2\4\22\3\2"+
		"\2\2\6\33\3\2\2\2\b\35\3\2\2\2\n%\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16"+
		"\21\3\2\2\2\17\r\3\2\2\2\17\20\3\2\2\2\20\3\3\2\2\2\21\17\3\2\2\2\22\23"+
		"\7\3\2\2\23\24\5\n\6\2\24\25\7\4\2\2\25\26\7\5\2\2\26\27\7\13\2\2\27\5"+
		"\3\2\2\2\30\34\7\r\2\2\31\32\7\6\2\2\32\34\7\r\2\2\33\30\3\2\2\2\33\31"+
		"\3\2\2\2\34\7\3\2\2\2\35\"\5\6\4\2\36\37\7\7\2\2\37!\5\6\4\2 \36\3\2\2"+
		"\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#\t\3\2\2\2$\"\3\2\2\2%(\5\b\5\2&\'"+
		"\7\b\2\2\')\5\b\5\2(&\3\2\2\2()\3\2\2\2)\13\3\2\2\2\6\17\33\"(";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}