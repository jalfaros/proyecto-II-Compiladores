// Generated from C:/Users/warne/Desktop/proyecto-II-Compiladores\miScanner.g4 by ANTLR 4.9.1
package generated;
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
		PCIZQ=9, PCDER=10, PUNTO=11, COMI=12, ROPERATOR=13, BOOLEAN=14, CHAR=15, 
		INT=16, STRING=17, AOP=18, MOP=19, UNARY=20, IF=21, ELSE=22, WHILE=23, 
		RETURN=24, PRINT=25, CLASS=26, NEW=27, LENGTH=28, INTLITERAL=29, REALLITERAL=30, 
		TRUE=31, FALSE=32, BOOLITERAL=33, STRINGLITERAL=34, ID=35, WS=36;
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
			"BOOLEAN", "CHAR", "INT", "STRING", "AND", "OR", "DIVISION", "MULTIPLI", 
			"AOP", "MOP", "UNARY", "IF", "ELSE", "WHILE", "RETURN", "PRINT", "CLASS", 
			"NEW", "LENGTH", "INTLITERAL", "REALLITERAL", "TRUE", "FALSE", "BOOLITERAL", 
			"STRINGLITERAL", "ID", "PRINTABLE", "DIGIT", "LETTER", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "')'", "'}'", "'{'", "'='", "','", "'_'", "'['", 
			"']'", "'.'", "'\"'", null, "'boolean'", "'char'", "'int'", "'string'", 
			null, null, null, "'if'", "'else'", "'while'", "'return'", "'print'", 
			"'class'", "'new'", "'length'", null, null, "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PyCOMA", "PIZQ", "PDER", "KEYDER", "KEYIZQ", "ASSIGN", "COMA", 
			"UNDERS", "PCIZQ", "PCDER", "PUNTO", "COMI", "ROPERATOR", "BOOLEAN", 
			"CHAR", "INT", "STRING", "AOP", "MOP", "UNARY", "IF", "ELSE", "WHILE", 
			"RETURN", "PRINT", "CLASS", "NEW", "LENGTH", "INTLITERAL", "REALLITERAL", 
			"TRUE", "FALSE", "BOOLITERAL", "STRINGLITERAL", "ID", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u016e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3"+
		"\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00a0\n\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\36\3\36\3\37\3\37\3 \3 \3 \5 \u00c7\n \3!\3!\3!\5!\u00cc\n!\3\"\3\""+
		"\3\"\5\"\u00d1\n\"\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&"+
		"\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3*"+
		"\3*\3*\3*\3*\3*\3*\3+\3+\7+\u0101\n+\f+\16+\u0104\13+\3,\3,\7,\u0108\n"+
		",\f,\16,\u010b\13,\3,\3,\7,\u010f\n,\f,\16,\u0112\13,\3,\3,\3,\7,\u0117"+
		"\n,\f,\16,\u011a\13,\5,\u011c\n,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3/\3"+
		"/\5/\u012b\n/\3\60\3\60\7\60\u012f\n\60\f\60\16\60\u0132\13\60\3\60\3"+
		"\60\3\61\3\61\5\61\u0138\n\61\3\61\3\61\3\61\7\61\u013d\n\61\f\61\16\61"+
		"\u0140\13\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3"+
		"\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3"+
		"\62\3\62\3\62\3\62\3\62\3\62\3\62\5\62\u0162\n\62\3\63\3\63\3\64\3\64"+
		"\3\65\6\65\u0169\n\65\r\65\16\65\u016a\3\65\3\65\2\2\66\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\2\35\2\37\2!\2#\2%\2\'"+
		"\2)\2+\2-\17/\20\61\21\63\22\65\23\67\29\2;\2=\2?\24A\25C\26E\27G\30I"+
		"\31K\32M\33O\34Q\35S\36U\37W Y![\"]#_$a%c\2e\2g\2i&\3\2\5\3\2\62;\4\2"+
		"C\\c|\5\2\13\f\17\17\"\"\2\u0191\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2"+
		"E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3"+
		"\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2"+
		"\2\2_\3\2\2\2\2a\3\2\2\2\2i\3\2\2\2\3k\3\2\2\2\5m\3\2\2\2\7o\3\2\2\2\t"+
		"q\3\2\2\2\13s\3\2\2\2\ru\3\2\2\2\17w\3\2\2\2\21y\3\2\2\2\23{\3\2\2\2\25"+
		"}\3\2\2\2\27\177\3\2\2\2\31\u0081\3\2\2\2\33\u0083\3\2\2\2\35\u0085\3"+
		"\2\2\2\37\u0087\3\2\2\2!\u0089\3\2\2\2#\u008b\3\2\2\2%\u008e\3\2\2\2\'"+
		"\u0091\3\2\2\2)\u0094\3\2\2\2+\u0097\3\2\2\2-\u009f\3\2\2\2/\u00a1\3\2"+
		"\2\2\61\u00a9\3\2\2\2\63\u00ae\3\2\2\2\65\u00b2\3\2\2\2\67\u00b9\3\2\2"+
		"\29\u00bc\3\2\2\2;\u00bf\3\2\2\2=\u00c1\3\2\2\2?\u00c6\3\2\2\2A\u00cb"+
		"\3\2\2\2C\u00d0\3\2\2\2E\u00d2\3\2\2\2G\u00d5\3\2\2\2I\u00da\3\2\2\2K"+
		"\u00e0\3\2\2\2M\u00e7\3\2\2\2O\u00ed\3\2\2\2Q\u00f3\3\2\2\2S\u00f7\3\2"+
		"\2\2U\u00fe\3\2\2\2W\u011b\3\2\2\2Y\u011d\3\2\2\2[\u0122\3\2\2\2]\u012a"+
		"\3\2\2\2_\u012c\3\2\2\2a\u0137\3\2\2\2c\u0161\3\2\2\2e\u0163\3\2\2\2g"+
		"\u0165\3\2\2\2i\u0168\3\2\2\2kl\7=\2\2l\4\3\2\2\2mn\7*\2\2n\6\3\2\2\2"+
		"op\7+\2\2p\b\3\2\2\2qr\7\177\2\2r\n\3\2\2\2st\7}\2\2t\f\3\2\2\2uv\7?\2"+
		"\2v\16\3\2\2\2wx\7.\2\2x\20\3\2\2\2yz\7a\2\2z\22\3\2\2\2{|\7]\2\2|\24"+
		"\3\2\2\2}~\7_\2\2~\26\3\2\2\2\177\u0080\7\60\2\2\u0080\30\3\2\2\2\u0081"+
		"\u0082\7$\2\2\u0082\32\3\2\2\2\u0083\u0084\7-\2\2\u0084\34\3\2\2\2\u0085"+
		"\u0086\7/\2\2\u0086\36\3\2\2\2\u0087\u0088\7#\2\2\u0088 \3\2\2\2\u0089"+
		"\u008a\7@\2\2\u008a\"\3\2\2\2\u008b\u008c\7?\2\2\u008c\u008d\7?\2\2\u008d"+
		"$\3\2\2\2\u008e\u008f\7#\2\2\u008f\u0090\7?\2\2\u0090&\3\2\2\2\u0091\u0092"+
		"\7>\2\2\u0092\u0093\7?\2\2\u0093(\3\2\2\2\u0094\u0095\7@\2\2\u0095\u0096"+
		"\7?\2\2\u0096*\3\2\2\2\u0097\u0098\7>\2\2\u0098,\3\2\2\2\u0099\u00a0\5"+
		"+\26\2\u009a\u00a0\5!\21\2\u009b\u00a0\5#\22\2\u009c\u00a0\5%\23\2\u009d"+
		"\u00a0\5\'\24\2\u009e\u00a0\5)\25\2\u009f\u0099\3\2\2\2\u009f\u009a\3"+
		"\2\2\2\u009f\u009b\3\2\2\2\u009f\u009c\3\2\2\2\u009f\u009d\3\2\2\2\u009f"+
		"\u009e\3\2\2\2\u00a0.\3\2\2\2\u00a1\u00a2\7d\2\2\u00a2\u00a3\7q\2\2\u00a3"+
		"\u00a4\7q\2\2\u00a4\u00a5\7n\2\2\u00a5\u00a6\7g\2\2\u00a6\u00a7\7c\2\2"+
		"\u00a7\u00a8\7p\2\2\u00a8\60\3\2\2\2\u00a9\u00aa\7e\2\2\u00aa\u00ab\7"+
		"j\2\2\u00ab\u00ac\7c\2\2\u00ac\u00ad\7t\2\2\u00ad\62\3\2\2\2\u00ae\u00af"+
		"\7k\2\2\u00af\u00b0\7p\2\2\u00b0\u00b1\7v\2\2\u00b1\64\3\2\2\2\u00b2\u00b3"+
		"\7u\2\2\u00b3\u00b4\7v\2\2\u00b4\u00b5\7t\2\2\u00b5\u00b6\7k\2\2\u00b6"+
		"\u00b7\7p\2\2\u00b7\u00b8\7i\2\2\u00b8\66\3\2\2\2\u00b9\u00ba\7(\2\2\u00ba"+
		"\u00bb\7(\2\2\u00bb8\3\2\2\2\u00bc\u00bd\7~\2\2\u00bd\u00be\7~\2\2\u00be"+
		":\3\2\2\2\u00bf\u00c0\7\61\2\2\u00c0<\3\2\2\2\u00c1\u00c2\7,\2\2\u00c2"+
		">\3\2\2\2\u00c3\u00c7\5\33\16\2\u00c4\u00c7\5\35\17\2\u00c5\u00c7\59\35"+
		"\2\u00c6\u00c3\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c5\3\2\2\2\u00c7@"+
		"\3\2\2\2\u00c8\u00cc\5=\37\2\u00c9\u00cc\5;\36\2\u00ca\u00cc\5\67\34\2"+
		"\u00cb\u00c8\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2\2\2\u00ccB\3"+
		"\2\2\2\u00cd\u00d1\5\33\16\2\u00ce\u00d1\5\35\17\2\u00cf\u00d1\5\37\20"+
		"\2\u00d0\u00cd\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00cf\3\2\2\2\u00d1D"+
		"\3\2\2\2\u00d2\u00d3\7k\2\2\u00d3\u00d4\7h\2\2\u00d4F\3\2\2\2\u00d5\u00d6"+
		"\7g\2\2\u00d6\u00d7\7n\2\2\u00d7\u00d8\7u\2\2\u00d8\u00d9\7g\2\2\u00d9"+
		"H\3\2\2\2\u00da\u00db\7y\2\2\u00db\u00dc\7j\2\2\u00dc\u00dd\7k\2\2\u00dd"+
		"\u00de\7n\2\2\u00de\u00df\7g\2\2\u00dfJ\3\2\2\2\u00e0\u00e1\7t\2\2\u00e1"+
		"\u00e2\7g\2\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\7w\2\2\u00e4\u00e5\7t\2\2"+
		"\u00e5\u00e6\7p\2\2\u00e6L\3\2\2\2\u00e7\u00e8\7r\2\2\u00e8\u00e9\7t\2"+
		"\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7p\2\2\u00eb\u00ec\7v\2\2\u00ecN\3\2"+
		"\2\2\u00ed\u00ee\7e\2\2\u00ee\u00ef\7n\2\2\u00ef\u00f0\7c\2\2\u00f0\u00f1"+
		"\7u\2\2\u00f1\u00f2\7u\2\2\u00f2P\3\2\2\2\u00f3\u00f4\7p\2\2\u00f4\u00f5"+
		"\7g\2\2\u00f5\u00f6\7y\2\2\u00f6R\3\2\2\2\u00f7\u00f8\7n\2\2\u00f8\u00f9"+
		"\7g\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7i\2\2\u00fb\u00fc\7v\2\2\u00fc"+
		"\u00fd\7j\2\2\u00fdT\3\2\2\2\u00fe\u0102\5e\63\2\u00ff\u0101\5e\63\2\u0100"+
		"\u00ff\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103V\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0109\5e\63\2\u0106\u0108"+
		"\5e\63\2\u0107\u0106\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u0110\5\27"+
		"\f\2\u010d\u010f\5e\63\2\u010e\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110"+
		"\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u011c\3\2\2\2\u0112\u0110\3\2"+
		"\2\2\u0113\u0114\5\27\f\2\u0114\u0118\5e\63\2\u0115\u0117\5e\63\2\u0116"+
		"\u0115\3\2\2\2\u0117\u011a\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2"+
		"\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011b\u0105\3\2\2\2\u011b"+
		"\u0113\3\2\2\2\u011cX\3\2\2\2\u011d\u011e\7v\2\2\u011e\u011f\7t\2\2\u011f"+
		"\u0120\7w\2\2\u0120\u0121\7g\2\2\u0121Z\3\2\2\2\u0122\u0123\7h\2\2\u0123"+
		"\u0124\7c\2\2\u0124\u0125\7n\2\2\u0125\u0126\7u\2\2\u0126\u0127\7g\2\2"+
		"\u0127\\\3\2\2\2\u0128\u012b\5Y-\2\u0129\u012b\5[.\2\u012a\u0128\3\2\2"+
		"\2\u012a\u0129\3\2\2\2\u012b^\3\2\2\2\u012c\u0130\5\31\r\2\u012d\u012f"+
		"\5c\62\2\u012e\u012d\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130"+
		"\u0131\3\2\2\2\u0131\u0133\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u0134\5\31"+
		"\r\2\u0134`\3\2\2\2\u0135\u0138\5\21\t\2\u0136\u0138\5g\64\2\u0137\u0135"+
		"\3\2\2\2\u0137\u0136\3\2\2\2\u0138\u013e\3\2\2\2\u0139\u013d\5\21\t\2"+
		"\u013a\u013d\5g\64\2\u013b\u013d\5e\63\2\u013c\u0139\3\2\2\2\u013c\u013a"+
		"\3\2\2\2\u013c\u013b\3\2\2\2\u013d\u0140\3\2\2\2\u013e\u013c\3\2\2\2\u013e"+
		"\u013f\3\2\2\2\u013fb\3\2\2\2\u0140\u013e\3\2\2\2\u0141\u0162\5e\63\2"+
		"\u0142\u0162\5g\64\2\u0143\u0162\7\"\2\2\u0144\u0145\7$\2\2\u0145\u0146"+
		"\7\"\2\2\u0146\u0162\7$\2\2\u0147\u0162\5\37\20\2\u0148\u0162\4%)\2\u0149"+
		"\u0162\5\5\3\2\u014a\u0162\5\7\4\2\u014b\u0162\5=\37\2\u014c\u0162\5\33"+
		"\16\2\u014d\u0162\5\17\b\2\u014e\u0162\5\35\17\2\u014f\u0162\5\27\f\2"+
		"\u0150\u0162\5;\36\2\u0151\u0162\7<\2\2\u0152\u0162\5\3\2\2\u0153\u0162"+
		"\5+\26\2\u0154\u0162\5\r\7\2\u0155\u0162\5!\21\2\u0156\u0162\4AB\2\u0157"+
		"\u0162\5\5\3\2\u0158\u0162\7^\2\2\u0159\u0162\5\25\13\2\u015a\u0162\7"+
		"`\2\2\u015b\u0162\5\21\t\2\u015c\u0162\7b\2\2\u015d\u0162\5\13\6\2\u015e"+
		"\u0162\7~\2\2\u015f\u0162\5\t\5\2\u0160\u0162\7\u0080\2\2\u0161\u0141"+
		"\3\2\2\2\u0161\u0142\3\2\2\2\u0161\u0143\3\2\2\2\u0161\u0144\3\2\2\2\u0161"+
		"\u0147\3\2\2\2\u0161\u0148\3\2\2\2\u0161\u0149\3\2\2\2\u0161\u014a\3\2"+
		"\2\2\u0161\u014b\3\2\2\2\u0161\u014c\3\2\2\2\u0161\u014d\3\2\2\2\u0161"+
		"\u014e\3\2\2\2\u0161\u014f\3\2\2\2\u0161\u0150\3\2\2\2\u0161\u0151\3\2"+
		"\2\2\u0161\u0152\3\2\2\2\u0161\u0153\3\2\2\2\u0161\u0154\3\2\2\2\u0161"+
		"\u0155\3\2\2\2\u0161\u0156\3\2\2\2\u0161\u0157\3\2\2\2\u0161\u0158\3\2"+
		"\2\2\u0161\u0159\3\2\2\2\u0161\u015a\3\2\2\2\u0161\u015b\3\2\2\2\u0161"+
		"\u015c\3\2\2\2\u0161\u015d\3\2\2\2\u0161\u015e\3\2\2\2\u0161\u015f\3\2"+
		"\2\2\u0161\u0160\3\2\2\2\u0162d\3\2\2\2\u0163\u0164\t\2\2\2\u0164f\3\2"+
		"\2\2\u0165\u0166\t\3\2\2\u0166h\3\2\2\2\u0167\u0169\t\4\2\2\u0168\u0167"+
		"\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b"+
		"\u016c\3\2\2\2\u016c\u016d\b\65\2\2\u016dj\3\2\2\2\23\2\u009f\u00c6\u00cb"+
		"\u00d0\u0102\u0109\u0110\u0118\u011b\u012a\u0130\u0137\u013c\u013e\u0161"+
		"\u016a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}