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
		PCIZQ=9, PCDER=10, PUNTO=11, COMI=12, COMISIMPLE=13, ROPERATOR=14, BOOLEAN=15, 
		CHAR=16, INT=17, STRING=18, AOP=19, MOP=20, UNARY=21, IF=22, ELSE=23, 
		WHILE=24, RETURN=25, PRINT=26, CLASS=27, NEW=28, LENGTH=29, INTLITERAL=30, 
		REALLITERAL=31, TRUE=32, FALSE=33, BOOLITERAL=34, STRINGLITERAL=35, CHARLITERAL=36, 
		ID=37, WS=38;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PyCOMA", "PIZQ", "PDER", "KEYDER", "KEYIZQ", "ASSIGN", "COMA", "UNDERS", 
			"PCIZQ", "PCDER", "PUNTO", "COMI", "COMISIMPLE", "SUMA", "RESTA", "ADMIRACION", 
			"MAYOR", "IGUALES", "DIFERENTE", "MENORIGU", "MAYORIGU", "MENOR", "ROPERATOR", 
			"BOOLEAN", "CHAR", "INT", "STRING", "AND", "OR", "DIVISION", "MULTIPLI", 
			"AOP", "MOP", "UNARY", "IF", "ELSE", "WHILE", "RETURN", "PRINT", "CLASS", 
			"NEW", "LENGTH", "INTLITERAL", "REALLITERAL", "TRUE", "FALSE", "BOOLITERAL", 
			"STRINGLITERAL", "CHARLITERAL", "ID", "PRINTABLE", "DIGIT", "LETTER", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "')'", "'}'", "'{'", "'='", "','", "'_'", "'['", 
			"']'", "'.'", "'\"'", "'''", null, "'boolean'", "'char'", "'int'", "'string'", 
			null, null, null, "'if'", "'else'", "'while'", "'return'", "'print'", 
			"'class'", "'new'", "'length'", null, null, "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PyCOMA", "PIZQ", "PDER", "KEYDER", "KEYIZQ", "ASSIGN", "COMA", 
			"UNDERS", "PCIZQ", "PCDER", "PUNTO", "COMI", "COMISIMPLE", "ROPERATOR", 
			"BOOLEAN", "CHAR", "INT", "STRING", "AOP", "MOP", "UNARY", "IF", "ELSE", 
			"WHILE", "RETURN", "PRINT", "CLASS", "NEW", "LENGTH", "INTLITERAL", "REALLITERAL", 
			"TRUE", "FALSE", "BOOLITERAL", "STRINGLITERAL", "CHARLITERAL", "ID", 
			"WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u017b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3"+
		"\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\5\30\u00a6\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\5!\u00cd\n!\3"+
		"\"\3\"\3\"\5\"\u00d2\n\"\3#\3#\3#\5#\u00d7\n#\3$\3$\3$\3%\3%\3%\3%\3%"+
		"\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3"+
		")\3)\3)\3)\3)\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3,\3,\7,\u0107\n,\f,\16"+
		",\u010a\13,\3-\3-\7-\u010e\n-\f-\16-\u0111\13-\3-\3-\7-\u0115\n-\f-\16"+
		"-\u0118\13-\3-\3-\3-\7-\u011d\n-\f-\16-\u0120\13-\5-\u0122\n-\3.\3.\3"+
		".\3.\3.\3/\3/\3/\3/\3/\3/\3\60\3\60\5\60\u0131\n\60\3\61\3\61\7\61\u0135"+
		"\n\61\f\61\16\61\u0138\13\61\3\61\3\61\3\62\3\62\5\62\u013e\n\62\3\62"+
		"\3\62\3\63\3\63\5\63\u0144\n\63\3\63\3\63\3\63\7\63\u0149\n\63\f\63\16"+
		"\63\u014c\13\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\5\64\u016f\n\64\3\65\3\65\3\66"+
		"\3\66\3\67\6\67\u0176\n\67\r\67\16\67\u0177\3\67\3\67\2\28\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\2\37\2!\2#\2%\2"+
		"\'\2)\2+\2-\2/\20\61\21\63\22\65\23\67\249\2;\2=\2?\2A\25C\26E\27G\30"+
		"I\31K\32M\33O\34Q\35S\36U\37W Y![\"]#_$a%c&e\'g\2i\2k\2m(\3\2\5\3\2\62"+
		";\4\2C\\c|\5\2\13\f\17\17\"\"\2\u01a0\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2/\3\2"+
		"\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2"+
		"\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2m\3\2\2\2\3"+
		"o\3\2\2\2\5q\3\2\2\2\7s\3\2\2\2\tu\3\2\2\2\13w\3\2\2\2\ry\3\2\2\2\17{"+
		"\3\2\2\2\21}\3\2\2\2\23\177\3\2\2\2\25\u0081\3\2\2\2\27\u0083\3\2\2\2"+
		"\31\u0085\3\2\2\2\33\u0087\3\2\2\2\35\u0089\3\2\2\2\37\u008b\3\2\2\2!"+
		"\u008d\3\2\2\2#\u008f\3\2\2\2%\u0091\3\2\2\2\'\u0094\3\2\2\2)\u0097\3"+
		"\2\2\2+\u009a\3\2\2\2-\u009d\3\2\2\2/\u00a5\3\2\2\2\61\u00a7\3\2\2\2\63"+
		"\u00af\3\2\2\2\65\u00b4\3\2\2\2\67\u00b8\3\2\2\29\u00bf\3\2\2\2;\u00c2"+
		"\3\2\2\2=\u00c5\3\2\2\2?\u00c7\3\2\2\2A\u00cc\3\2\2\2C\u00d1\3\2\2\2E"+
		"\u00d6\3\2\2\2G\u00d8\3\2\2\2I\u00db\3\2\2\2K\u00e0\3\2\2\2M\u00e6\3\2"+
		"\2\2O\u00ed\3\2\2\2Q\u00f3\3\2\2\2S\u00f9\3\2\2\2U\u00fd\3\2\2\2W\u0104"+
		"\3\2\2\2Y\u0121\3\2\2\2[\u0123\3\2\2\2]\u0128\3\2\2\2_\u0130\3\2\2\2a"+
		"\u0132\3\2\2\2c\u013b\3\2\2\2e\u0143\3\2\2\2g\u016e\3\2\2\2i\u0170\3\2"+
		"\2\2k\u0172\3\2\2\2m\u0175\3\2\2\2op\7=\2\2p\4\3\2\2\2qr\7*\2\2r\6\3\2"+
		"\2\2st\7+\2\2t\b\3\2\2\2uv\7\177\2\2v\n\3\2\2\2wx\7}\2\2x\f\3\2\2\2yz"+
		"\7?\2\2z\16\3\2\2\2{|\7.\2\2|\20\3\2\2\2}~\7a\2\2~\22\3\2\2\2\177\u0080"+
		"\7]\2\2\u0080\24\3\2\2\2\u0081\u0082\7_\2\2\u0082\26\3\2\2\2\u0083\u0084"+
		"\7\60\2\2\u0084\30\3\2\2\2\u0085\u0086\7$\2\2\u0086\32\3\2\2\2\u0087\u0088"+
		"\7)\2\2\u0088\34\3\2\2\2\u0089\u008a\7-\2\2\u008a\36\3\2\2\2\u008b\u008c"+
		"\7/\2\2\u008c \3\2\2\2\u008d\u008e\7#\2\2\u008e\"\3\2\2\2\u008f\u0090"+
		"\7@\2\2\u0090$\3\2\2\2\u0091\u0092\7?\2\2\u0092\u0093\7?\2\2\u0093&\3"+
		"\2\2\2\u0094\u0095\7#\2\2\u0095\u0096\7?\2\2\u0096(\3\2\2\2\u0097\u0098"+
		"\7>\2\2\u0098\u0099\7?\2\2\u0099*\3\2\2\2\u009a\u009b\7@\2\2\u009b\u009c"+
		"\7?\2\2\u009c,\3\2\2\2\u009d\u009e\7>\2\2\u009e.\3\2\2\2\u009f\u00a6\5"+
		"-\27\2\u00a0\u00a6\5#\22\2\u00a1\u00a6\5%\23\2\u00a2\u00a6\5\'\24\2\u00a3"+
		"\u00a6\5)\25\2\u00a4\u00a6\5+\26\2\u00a5\u009f\3\2\2\2\u00a5\u00a0\3\2"+
		"\2\2\u00a5\u00a1\3\2\2\2\u00a5\u00a2\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a4\3\2\2\2\u00a6\60\3\2\2\2\u00a7\u00a8\7d\2\2\u00a8\u00a9\7q\2\2"+
		"\u00a9\u00aa\7q\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad"+
		"\7c\2\2\u00ad\u00ae\7p\2\2\u00ae\62\3\2\2\2\u00af\u00b0\7e\2\2\u00b0\u00b1"+
		"\7j\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7t\2\2\u00b3\64\3\2\2\2\u00b4\u00b5"+
		"\7k\2\2\u00b5\u00b6\7p\2\2\u00b6\u00b7\7v\2\2\u00b7\66\3\2\2\2\u00b8\u00b9"+
		"\7u\2\2\u00b9\u00ba\7v\2\2\u00ba\u00bb\7t\2\2\u00bb\u00bc\7k\2\2\u00bc"+
		"\u00bd\7p\2\2\u00bd\u00be\7i\2\2\u00be8\3\2\2\2\u00bf\u00c0\7(\2\2\u00c0"+
		"\u00c1\7(\2\2\u00c1:\3\2\2\2\u00c2\u00c3\7~\2\2\u00c3\u00c4\7~\2\2\u00c4"+
		"<\3\2\2\2\u00c5\u00c6\7\61\2\2\u00c6>\3\2\2\2\u00c7\u00c8\7,\2\2\u00c8"+
		"@\3\2\2\2\u00c9\u00cd\5\35\17\2\u00ca\u00cd\5\37\20\2\u00cb\u00cd\5;\36"+
		"\2\u00cc\u00c9\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cb\3\2\2\2\u00cdB"+
		"\3\2\2\2\u00ce\u00d2\5? \2\u00cf\u00d2\5=\37\2\u00d0\u00d2\59\35\2\u00d1"+
		"\u00ce\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d0\3\2\2\2\u00d2D\3\2\2\2"+
		"\u00d3\u00d7\5\35\17\2\u00d4\u00d7\5\37\20\2\u00d5\u00d7\5!\21\2\u00d6"+
		"\u00d3\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7F\3\2\2\2"+
		"\u00d8\u00d9\7k\2\2\u00d9\u00da\7h\2\2\u00daH\3\2\2\2\u00db\u00dc\7g\2"+
		"\2\u00dc\u00dd\7n\2\2\u00dd\u00de\7u\2\2\u00de\u00df\7g\2\2\u00dfJ\3\2"+
		"\2\2\u00e0\u00e1\7y\2\2\u00e1\u00e2\7j\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4"+
		"\7n\2\2\u00e4\u00e5\7g\2\2\u00e5L\3\2\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8"+
		"\7g\2\2\u00e8\u00e9\7v\2\2\u00e9\u00ea\7w\2\2\u00ea\u00eb\7t\2\2\u00eb"+
		"\u00ec\7p\2\2\u00ecN\3\2\2\2\u00ed\u00ee\7r\2\2\u00ee\u00ef\7t\2\2\u00ef"+
		"\u00f0\7k\2\2\u00f0\u00f1\7p\2\2\u00f1\u00f2\7v\2\2\u00f2P\3\2\2\2\u00f3"+
		"\u00f4\7e\2\2\u00f4\u00f5\7n\2\2\u00f5\u00f6\7c\2\2\u00f6\u00f7\7u\2\2"+
		"\u00f7\u00f8\7u\2\2\u00f8R\3\2\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7g\2"+
		"\2\u00fb\u00fc\7y\2\2\u00fcT\3\2\2\2\u00fd\u00fe\7n\2\2\u00fe\u00ff\7"+
		"g\2\2\u00ff\u0100\7p\2\2\u0100\u0101\7i\2\2\u0101\u0102\7v\2\2\u0102\u0103"+
		"\7j\2\2\u0103V\3\2\2\2\u0104\u0108\5i\65\2\u0105\u0107\5i\65\2\u0106\u0105"+
		"\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109"+
		"X\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010f\5i\65\2\u010c\u010e\5i\65\2"+
		"\u010d\u010c\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110"+
		"\3\2\2\2\u0110\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0116\5\27\f\2"+
		"\u0113\u0115\5i\65\2\u0114\u0113\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114"+
		"\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0122\3\2\2\2\u0118\u0116\3\2\2\2\u0119"+
		"\u011a\5\27\f\2\u011a\u011e\5i\65\2\u011b\u011d\5i\65\2\u011c\u011b\3"+
		"\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f"+
		"\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u010b\3\2\2\2\u0121\u0119\3\2"+
		"\2\2\u0122Z\3\2\2\2\u0123\u0124\7v\2\2\u0124\u0125\7t\2\2\u0125\u0126"+
		"\7w\2\2\u0126\u0127\7g\2\2\u0127\\\3\2\2\2\u0128\u0129\7h\2\2\u0129\u012a"+
		"\7c\2\2\u012a\u012b\7n\2\2\u012b\u012c\7u\2\2\u012c\u012d\7g\2\2\u012d"+
		"^\3\2\2\2\u012e\u0131\5[.\2\u012f\u0131\5]/\2\u0130\u012e\3\2\2\2\u0130"+
		"\u012f\3\2\2\2\u0131`\3\2\2\2\u0132\u0136\5\31\r\2\u0133\u0135\5g\64\2"+
		"\u0134\u0133\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137"+
		"\3\2\2\2\u0137\u0139\3\2\2\2\u0138\u0136\3\2\2\2\u0139\u013a\5\31\r\2"+
		"\u013ab\3\2\2\2\u013b\u013d\5\33\16\2\u013c\u013e\5g\64\2\u013d\u013c"+
		"\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0140\5\33\16\2"+
		"\u0140d\3\2\2\2\u0141\u0144\5\21\t\2\u0142\u0144\5k\66\2\u0143\u0141\3"+
		"\2\2\2\u0143\u0142\3\2\2\2\u0144\u014a\3\2\2\2\u0145\u0149\5\21\t\2\u0146"+
		"\u0149\5k\66\2\u0147\u0149\5i\65\2\u0148\u0145\3\2\2\2\u0148\u0146\3\2"+
		"\2\2\u0148\u0147\3\2\2\2\u0149\u014c\3\2\2\2\u014a\u0148\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014bf\3\2\2\2\u014c\u014a\3\2\2\2\u014d\u016f\5i\65\2"+
		"\u014e\u016f\5k\66\2\u014f\u016f\7\"\2\2\u0150\u0151\7$\2\2\u0151\u0152"+
		"\7\"\2\2\u0152\u016f\7$\2\2\u0153\u016f\5!\21\2\u0154\u016f\4%(\2\u0155"+
		"\u016f\5\33\16\2\u0156\u016f\5\5\3\2\u0157\u016f\5\7\4\2\u0158\u016f\5"+
		"? \2\u0159\u016f\5\35\17\2\u015a\u016f\5\17\b\2\u015b\u016f\5\37\20\2"+
		"\u015c\u016f\5\27\f\2\u015d\u016f\5=\37\2\u015e\u016f\7<\2\2\u015f\u016f"+
		"\5\3\2\2\u0160\u016f\5-\27\2\u0161\u016f\5\r\7\2\u0162\u016f\5#\22\2\u0163"+
		"\u016f\4AB\2\u0164\u016f\5\5\3\2\u0165\u016f\7^\2\2\u0166\u016f\5\25\13"+
		"\2\u0167\u016f\7`\2\2\u0168\u016f\5\21\t\2\u0169\u016f\7b\2\2\u016a\u016f"+
		"\5\13\6\2\u016b\u016f\7~\2\2\u016c\u016f\5\t\5\2\u016d\u016f\7\u0080\2"+
		"\2\u016e\u014d\3\2\2\2\u016e\u014e\3\2\2\2\u016e\u014f\3\2\2\2\u016e\u0150"+
		"\3\2\2\2\u016e\u0153\3\2\2\2\u016e\u0154\3\2\2\2\u016e\u0155\3\2\2\2\u016e"+
		"\u0156\3\2\2\2\u016e\u0157\3\2\2\2\u016e\u0158\3\2\2\2\u016e\u0159\3\2"+
		"\2\2\u016e\u015a\3\2\2\2\u016e\u015b\3\2\2\2\u016e\u015c\3\2\2\2\u016e"+
		"\u015d\3\2\2\2\u016e\u015e\3\2\2\2\u016e\u015f\3\2\2\2\u016e\u0160\3\2"+
		"\2\2\u016e\u0161\3\2\2\2\u016e\u0162\3\2\2\2\u016e\u0163\3\2\2\2\u016e"+
		"\u0164\3\2\2\2\u016e\u0165\3\2\2\2\u016e\u0166\3\2\2\2\u016e\u0167\3\2"+
		"\2\2\u016e\u0168\3\2\2\2\u016e\u0169\3\2\2\2\u016e\u016a\3\2\2\2\u016e"+
		"\u016b\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016d\3\2\2\2\u016fh\3\2\2\2"+
		"\u0170\u0171\t\2\2\2\u0171j\3\2\2\2\u0172\u0173\t\3\2\2\u0173l\3\2\2\2"+
		"\u0174\u0176\t\4\2\2\u0175\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0175"+
		"\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017a\b\67\2\2"+
		"\u017an\3\2\2\2\24\2\u00a5\u00cc\u00d1\u00d6\u0108\u010f\u0116\u011e\u0121"+
		"\u0130\u0136\u013d\u0143\u0148\u014a\u016e\u0177\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}