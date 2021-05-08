    /*
    Scanner - II Proyecto - Compiladores e Intérpretes
    Jose Ignacio Alfaro Solano - Warner Fidel Hurtado Laguna
*/

lexer grammar miScanner;

PyCOMA : ';'   ;
PIZQ   : '('   ;
PDER   : ')'   ;
KEYDER : '}'   ;
KEYIZQ : '{'   ;
ASSIGN : '='   ;
COMA   : ','   ;
UNDERS : '_'   ;
PCIZQ  : '['   ;
PCDER  : ']'   ;
PUNTO  : '.'                                                                ;
COMI   : '"'                                                                ;
COMISIMPLE : '\''                                                           ;

fragment SUMA        : '+'                                                           ;
fragment RESTA       : '-'                                                           ;
fragment ADMIRACION    : '!'                                                         ;

fragment MAYOR : '>'     ;
fragment IGUALES : '=='  ;
fragment DIFERENTE : '!=' ;
fragment MENORIGU : '<=' ;
fragment MAYORIGU : '>=' ;
fragment MENOR : '<'                                                        ;

ROPERATOR   : MENOR | MAYOR | IGUALES | DIFERENTE | MENORIGU | MAYORIGU ;

BOOLEAN: 'boolean';
CHAR   : 'char';
INT    : 'int';
STRING : 'string';

fragment AND         : '&&'                                                      ;
fragment OR          : '||'                                                      ;
fragment DIVISION    : '/'                                                       ;
fragment MULTIPLI    : '*'                                                       ;

AOP       : SUMA | RESTA | OR                      ;
MOP       : MULTIPLI | DIVISION | AND              ;
UNARY      : SUMA | RESTA | ADMIRACION             ;

IF      : 'if'    ;
ELSE   : 'else'      ;
WHILE  : 'while'  ;

RETURN : 'return'  ;
PRINT  : 'print'  ;
CLASS  : 'class'    ;
NEW    : 'new'      ;
LENGTH : 'length'   ;

INTLITERAL       : DIGIT (DIGIT)* ;

//REALLITERAL      : DIGIT (DIGIT)* PUNTO (DIGIT)* | PUNTO DIGIT (DIGIT)* ;

TRUE: 'true';
FALSE: 'false';

STRINGLITERAL    : COMI (PRINTABLE)* COMI ;

CHARLITERAL      : COMISIMPLE (PRINTABLE)? COMISIMPLE;

ID : (UNDERS | LETTER) (UNDERS | LETTER | DIGIT)* ;

fragment PRINTABLE :  DIGIT | LETTER | ' ' | '" "' | ADMIRACION | '#' | '$' | '%' | '&' |
                     COMISIMPLE | PIZQ | PDER | MULTIPLI | SUMA | COMA | RESTA | PUNTO      |
                     DIVISION | ':' | PyCOMA | MENOR | ASSIGN | MAYOR | '?' | '@'     |
                     PIZQ | '\\' | PCDER | '^' | UNDERS | '`' | KEYIZQ | '|' | KEYDER |
                     '~';

fragment DIGIT : [0-9];
fragment LETTER :                           'A' | 'B' | 'C' | 'D' | 'E' | 'F'| 'G' | 'H' | 'I' | 'J' | 'K'
                                        |   'L' | 'M' | 'N' | 'O' | 'P' | 'Q' | 'R' | 'S' | 'T' | 'U' | 'V'
                                        |   'W' | 'X' | 'Y' | 'Z' | 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g'
                                        |   'h' | 'i' | 'j' | 'k' | 'l' | 'm' | 'n' | 'o' | 'p' | 'q' | 'r'
                                        |   's' | 't' | 'u' | 'v' | 'w' | 'x' | 'y' | 'z';
WS: [ \t\n\r]+ -> skip;