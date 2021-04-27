// Generated from C:/Users/Huawei D14/Desktop/compiladores_2_Ignacio_Warner\miScanner.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class miScanner extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PyCOMA=1, PIZQ=2, PDER=3, KEYDER=4, KEYIZQ=5, ASSIGN=6, COMA=7, UNDERS=8, 
		PCIZQ=9, PCDER=10, PUNTO=11, COMI=12, ROPERATOR=13, STYPE=14, AOP=15, 
		MOP=16, UNARY=17, IF=18, ELSE=19, WHILE=20, RETURN=21, PRINT=22, CLASS=23, 
		NEW=24, LENGTH=25, INTLITERAL=26, REALLITERAL=27, BOOLITERAL=28, STRINGLITERAL=29, 
		ID=30, WS=31;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PyCOMA", "PIZQ", "PDER", "KEYDER", "KEYIZQ", "ASSIGN", "COMA", "UNDERS", 
			"PCIZQ", "PCDER", "PUNTO", "COMI", "SUMA", "RESTA", "ADMIRACION", "MAYOR", 
			"IGUALES", "DIFERENTE", "MENORIGU", "MAYORIGU", "MENOR", "ROPERATOR", 
			"STYPE", "AND", "OR", "DIVISION", "MULTIPLI", "AOP", "MOP", "UNARY", 
			"IF", "ELSE", "WHILE", "RETURN", "PRINT", "CLASS", "NEW", "LENGTH", "INTLITERAL", 
			"REALLITERAL", "BOOLITERAL", "STRINGLITERAL", "ID", "PRINTABLE", "DIGIT", 
			"LETTER", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "')'", "'}'", "'{'", "'='", "','", "'_'", "'['", 
			"']'", "'.'", "'\"'", null, null, null, null, null, "'if'", "'else'", 
			"'while'", "'return'", "'print'", "'class'", "'new'", "'length'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PyCOMA", "PIZQ", "PDER", "KEYDER", "KEYIZQ", "ASSIGN", "COMA", 
			"UNDERS", "PCIZQ", "PCDER", "PUNTO", "COMI", "ROPERATOR", "STYPE", "AOP", 
			"MOP", "UNARY", "IF", "ELSE", "WHILE", "RETURN", "PRINT", "CLASS", "NEW", 
			"LENGTH", "INTLITERAL", "REALLITERAL", "BOOLITERAL", "STRINGLITERAL", 
			"ID", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public miScanner(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "miScanner.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u015e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0096"+
		"\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00ac\n\30\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\5\35\u00bb\n\35\3\36\3\36"+
		"\3\36\5\36\u00c0\n\36\3\37\3\37\3\37\5\37\u00c5\n\37\3 \3 \3 \3!\3!\3"+
		"!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3"+
		"$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\7(\u00f5"+
		"\n(\f(\16(\u00f8\13(\3)\3)\7)\u00fc\n)\f)\16)\u00ff\13)\3)\3)\7)\u0103"+
		"\n)\f)\16)\u0106\13)\3)\3)\3)\7)\u010b\n)\f)\16)\u010e\13)\5)\u0110\n"+
		")\3*\3*\3*\3*\3*\3*\3*\3*\3*\5*\u011b\n*\3+\3+\7+\u011f\n+\f+\16+\u0122"+
		"\13+\3+\3+\3,\3,\5,\u0128\n,\3,\3,\3,\7,\u012d\n,\f,\16,\u0130\13,\3-"+
		"\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-"+
		"\3-\3-\3-\3-\3-\3-\3-\3-\5-\u0152\n-\3.\3.\3/\3/\3\60\6\60\u0159\n\60"+
		"\r\60\16\60\u015a\3\60\3\60\2\2\61\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\2\35\2\37\2!\2#\2%\2\'\2)\2+\2-\17/\20\61\2"+
		"\63\2\65\2\67\29\21;\22=\23?\24A\25C\26E\27G\30I\31K\32M\33O\34Q\35S\36"+
		"U\37W Y\2[\2]\2_!\3\2\5\3\2\62;\4\2C\\c|\5\2\13\f\17\17\"\"\2\u0184\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?"+
		"\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2"+
		"\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2"+
		"\2_\3\2\2\2\3a\3\2\2\2\5c\3\2\2\2\7e\3\2\2\2\tg\3\2\2\2\13i\3\2\2\2\r"+
		"k\3\2\2\2\17m\3\2\2\2\21o\3\2\2\2\23q\3\2\2\2\25s\3\2\2\2\27u\3\2\2\2"+
		"\31w\3\2\2\2\33y\3\2\2\2\35{\3\2\2\2\37}\3\2\2\2!\177\3\2\2\2#\u0081\3"+
		"\2\2\2%\u0084\3\2\2\2\'\u0087\3\2\2\2)\u008a\3\2\2\2+\u008d\3\2\2\2-\u0095"+
		"\3\2\2\2/\u00ab\3\2\2\2\61\u00ad\3\2\2\2\63\u00b0\3\2\2\2\65\u00b3\3\2"+
		"\2\2\67\u00b5\3\2\2\29\u00ba\3\2\2\2;\u00bf\3\2\2\2=\u00c4\3\2\2\2?\u00c6"+
		"\3\2\2\2A\u00c9\3\2\2\2C\u00ce\3\2\2\2E\u00d4\3\2\2\2G\u00db\3\2\2\2I"+
		"\u00e1\3\2\2\2K\u00e7\3\2\2\2M\u00eb\3\2\2\2O\u00f2\3\2\2\2Q\u010f\3\2"+
		"\2\2S\u011a\3\2\2\2U\u011c\3\2\2\2W\u0127\3\2\2\2Y\u0151\3\2\2\2[\u0153"+
		"\3\2\2\2]\u0155\3\2\2\2_\u0158\3\2\2\2ab\7=\2\2b\4\3\2\2\2cd\7*\2\2d\6"+
		"\3\2\2\2ef\7+\2\2f\b\3\2\2\2gh\7\177\2\2h\n\3\2\2\2ij\7}\2\2j\f\3\2\2"+
		"\2kl\7?\2\2l\16\3\2\2\2mn\7.\2\2n\20\3\2\2\2op\7a\2\2p\22\3\2\2\2qr\7"+
		"]\2\2r\24\3\2\2\2st\7_\2\2t\26\3\2\2\2uv\7\60\2\2v\30\3\2\2\2wx\7$\2\2"+
		"x\32\3\2\2\2yz\7-\2\2z\34\3\2\2\2{|\7/\2\2|\36\3\2\2\2}~\7#\2\2~ \3\2"+
		"\2\2\177\u0080\7@\2\2\u0080\"\3\2\2\2\u0081\u0082\7?\2\2\u0082\u0083\7"+
		"?\2\2\u0083$\3\2\2\2\u0084\u0085\7#\2\2\u0085\u0086\7?\2\2\u0086&\3\2"+
		"\2\2\u0087\u0088\7>\2\2\u0088\u0089\7?\2\2\u0089(\3\2\2\2\u008a\u008b"+
		"\7@\2\2\u008b\u008c\7?\2\2\u008c*\3\2\2\2\u008d\u008e\7>\2\2\u008e,\3"+
		"\2\2\2\u008f\u0096\5+\26\2\u0090\u0096\5!\21\2\u0091\u0096\5#\22\2\u0092"+
		"\u0096\5%\23\2\u0093\u0096\5\'\24\2\u0094\u0096\5)\25\2\u0095\u008f\3"+
		"\2\2\2\u0095\u0090\3\2\2\2\u0095\u0091\3\2\2\2\u0095\u0092\3\2\2\2\u0095"+
		"\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096.\3\2\2\2\u0097\u0098\7d\2\2\u0098"+
		"\u0099\7q\2\2\u0099\u009a\7q\2\2\u009a\u009b\7n\2\2\u009b\u009c\7g\2\2"+
		"\u009c\u009d\7c\2\2\u009d\u00ac\7p\2\2\u009e\u009f\7e\2\2\u009f\u00a0"+
		"\7j\2\2\u00a0\u00a1\7c\2\2\u00a1\u00ac\7t\2\2\u00a2\u00a3\7k\2\2\u00a3"+
		"\u00a4\7p\2\2\u00a4\u00ac\7v\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7v\2\2"+
		"\u00a7\u00a8\7t\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ac"+
		"\7i\2\2\u00ab\u0097\3\2\2\2\u00ab\u009e\3\2\2\2\u00ab\u00a2\3\2\2\2\u00ab"+
		"\u00a5\3\2\2\2\u00ac\60\3\2\2\2\u00ad\u00ae\7(\2\2\u00ae\u00af\7(\2\2"+
		"\u00af\62\3\2\2\2\u00b0\u00b1\7~\2\2\u00b1\u00b2\7~\2\2\u00b2\64\3\2\2"+
		"\2\u00b3\u00b4\7\61\2\2\u00b4\66\3\2\2\2\u00b5\u00b6\7,\2\2\u00b68\3\2"+
		"\2\2\u00b7\u00bb\5\33\16\2\u00b8\u00bb\5\35\17\2\u00b9\u00bb\5\63\32\2"+
		"\u00ba\u00b7\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00b9\3\2\2\2\u00bb:\3"+
		"\2\2\2\u00bc\u00c0\5\67\34\2\u00bd\u00c0\5\65\33\2\u00be\u00c0\5\61\31"+
		"\2\u00bf\u00bc\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0<"+
		"\3\2\2\2\u00c1\u00c5\5\33\16\2\u00c2\u00c5\5\35\17\2\u00c3\u00c5\5\37"+
		"\20\2\u00c4\u00c1\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c3\3\2\2\2\u00c5"+
		">\3\2\2\2\u00c6\u00c7\7k\2\2\u00c7\u00c8\7h\2\2\u00c8@\3\2\2\2\u00c9\u00ca"+
		"\7g\2\2\u00ca\u00cb\7n\2\2\u00cb\u00cc\7u\2\2\u00cc\u00cd\7g\2\2\u00cd"+
		"B\3\2\2\2\u00ce\u00cf\7y\2\2\u00cf\u00d0\7j\2\2\u00d0\u00d1\7k\2\2\u00d1"+
		"\u00d2\7n\2\2\u00d2\u00d3\7g\2\2\u00d3D\3\2\2\2\u00d4\u00d5\7t\2\2\u00d5"+
		"\u00d6\7g\2\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7w\2\2\u00d8\u00d9\7t\2\2"+
		"\u00d9\u00da\7p\2\2\u00daF\3\2\2\2\u00db\u00dc\7r\2\2\u00dc\u00dd\7t\2"+
		"\2\u00dd\u00de\7k\2\2\u00de\u00df\7p\2\2\u00df\u00e0\7v\2\2\u00e0H\3\2"+
		"\2\2\u00e1\u00e2\7e\2\2\u00e2\u00e3\7n\2\2\u00e3\u00e4\7c\2\2\u00e4\u00e5"+
		"\7u\2\2\u00e5\u00e6\7u\2\2\u00e6J\3\2\2\2\u00e7\u00e8\7p\2\2\u00e8\u00e9"+
		"\7g\2\2\u00e9\u00ea\7y\2\2\u00eaL\3\2\2\2\u00eb\u00ec\7n\2\2\u00ec\u00ed"+
		"\7g\2\2\u00ed\u00ee\7p\2\2\u00ee\u00ef\7i\2\2\u00ef\u00f0\7v\2\2\u00f0"+
		"\u00f1\7j\2\2\u00f1N\3\2\2\2\u00f2\u00f6\5[.\2\u00f3\u00f5\5[.\2\u00f4"+
		"\u00f3\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2"+
		"\2\2\u00f7P\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fd\5[.\2\u00fa\u00fc"+
		"\5[.\2\u00fb\u00fa\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd"+
		"\u00fe\3\2\2\2\u00fe\u0100\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0104\5\27"+
		"\f\2\u0101\u0103\5[.\2\u0102\u0101\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102"+
		"\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0110\3\2\2\2\u0106\u0104\3\2\2\2\u0107"+
		"\u0108\5\27\f\2\u0108\u010c\5[.\2\u0109\u010b\5[.\2\u010a\u0109\3\2\2"+
		"\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u0110"+
		"\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u00f9\3\2\2\2\u010f\u0107\3\2\2\2\u0110"+
		"R\3\2\2\2\u0111\u0112\7v\2\2\u0112\u0113\7t\2\2\u0113\u0114\7w\2\2\u0114"+
		"\u011b\7g\2\2\u0115\u0116\7h\2\2\u0116\u0117\7c\2\2\u0117\u0118\7n\2\2"+
		"\u0118\u0119\7u\2\2\u0119\u011b\7g\2\2\u011a\u0111\3\2\2\2\u011a\u0115"+
		"\3\2\2\2\u011bT\3\2\2\2\u011c\u0120\5\31\r\2\u011d\u011f\5Y-\2\u011e\u011d"+
		"\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121"+
		"\u0123\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u0124\5\31\r\2\u0124V\3\2\2\2"+
		"\u0125\u0128\5\21\t\2\u0126\u0128\5]/\2\u0127\u0125\3\2\2\2\u0127\u0126"+
		"\3\2\2\2\u0128\u012e\3\2\2\2\u0129\u012d\5\21\t\2\u012a\u012d\5]/\2\u012b"+
		"\u012d\5[.\2\u012c\u0129\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012b\3\2\2"+
		"\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012fX"+
		"\3\2\2\2\u0130\u012e\3\2\2\2\u0131\u0152\5[.\2\u0132\u0152\5]/\2\u0133"+
		"\u0152\7\"\2\2\u0134\u0135\7$\2\2\u0135\u0136\7\"\2\2\u0136\u0152\7$\2"+
		"\2\u0137\u0152\5\37\20\2\u0138\u0152\4%)\2\u0139\u0152\5\5\3\2\u013a\u0152"+
		"\5\7\4\2\u013b\u0152\5\67\34\2\u013c\u0152\5\33\16\2\u013d\u0152\5\17"+
		"\b\2\u013e\u0152\5\35\17\2\u013f\u0152\5\27\f\2\u0140\u0152\5\65\33\2"+
		"\u0141\u0152\7<\2\2\u0142\u0152\5\3\2\2\u0143\u0152\5+\26\2\u0144\u0152"+
		"\5\r\7\2\u0145\u0152\5!\21\2\u0146\u0152\4AB\2\u0147\u0152\5\5\3\2\u0148"+
		"\u0152\7^\2\2\u0149\u0152\5\25\13\2\u014a\u0152\7`\2\2\u014b\u0152\5\21"+
		"\t\2\u014c\u0152\7b\2\2\u014d\u0152\5\13\6\2\u014e\u0152\7~\2\2\u014f"+
		"\u0152\5\t\5\2\u0150\u0152\7\u0080\2\2\u0151\u0131\3\2\2\2\u0151\u0132"+
		"\3\2\2\2\u0151\u0133\3\2\2\2\u0151\u0134\3\2\2\2\u0151\u0137\3\2\2\2\u0151"+
		"\u0138\3\2\2\2\u0151\u0139\3\2\2\2\u0151\u013a\3\2\2\2\u0151\u013b\3\2"+
		"\2\2\u0151\u013c\3\2\2\2\u0151\u013d\3\2\2\2\u0151\u013e\3\2\2\2\u0151"+
		"\u013f\3\2\2\2\u0151\u0140\3\2\2\2\u0151\u0141\3\2\2\2\u0151\u0142\3\2"+
		"\2\2\u0151\u0143\3\2\2\2\u0151\u0144\3\2\2\2\u0151\u0145\3\2\2\2\u0151"+
		"\u0146\3\2\2\2\u0151\u0147\3\2\2\2\u0151\u0148\3\2\2\2\u0151\u0149\3\2"+
		"\2\2\u0151\u014a\3\2\2\2\u0151\u014b\3\2\2\2\u0151\u014c\3\2\2\2\u0151"+
		"\u014d\3\2\2\2\u0151\u014e\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0150\3\2"+
		"\2\2\u0152Z\3\2\2\2\u0153\u0154\t\2\2\2\u0154\\\3\2\2\2\u0155\u0156\t"+
		"\3\2\2\u0156^\3\2\2\2\u0157\u0159\t\4\2\2\u0158\u0157\3\2\2\2\u0159\u015a"+
		"\3\2\2\2\u015a\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015c\3\2\2\2\u015c"+
		"\u015d\b\60\2\2\u015d`\3\2\2\2\24\2\u0095\u00ab\u00ba\u00bf\u00c4\u00f6"+
		"\u00fd\u0104\u010c\u010f\u011a\u0120\u0127\u012c\u012e\u0151\u015a\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}