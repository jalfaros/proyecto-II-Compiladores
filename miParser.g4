parser grammar miParser;

options {
    tokenVocab = miScanner;
}

program  : ( statement )*                                                                               #programAST;

statement:
  variableDeclaration PyCOMA
  | classDeclaration PyCOMA
  | assigment PyCOMA
  | arrayAssignment PyCOMA
  | printStatement PyCOMA
  | ifStatement
  | whileStatement
  | returnStatement PyCOMA
  | funtionDeclaration
  | block                                                                                               #statementAST;

block                     : KEYIZQ ( statement )* KEYDER 												#blockAST;

funtionDeclaration        : type ID PIZQ ( formalParams )? PDER block 									#functionDeclAST;

formalParams        : formalParam ( COMA formalParam)* 													#fParamAST;

formalParam         : type ID																															#fParamsAST;

whileStatement      : WHILE PIZQ expression PDER block 													#whileStmmtAST;

ifStatement         : IF PIZQ expression PDER block ( ELSE block )? 									#ifStmntAST;

returnStatement     : RETURN expression 																#returnStmntAST;

printStatement      : PRINT expression 																	#printStmntAST;

classDeclaration    : CLASS ID KEYIZQ (classVariableDeclaration)* KEYDER							    #classDelcAST;

classVariableDeclaration : STYPE ID (ASSIGN expression)?                                                #classVariableDeclAST;

variableDeclaration : type ID (ASSIGN expression)?                                       	            #variableDeclAST;

type                : STYPE | arrayType | ID															#typeDeclAST;

arrayType           : STYPE PCIZQ PCDER																	#arrTypeAST;

assigment           : ID (PUNTO ID)? ASSIGN expression 													#asssignAST;

arrayAssignment     : ID PCIZQ expression PCDER ASSIGN expression 									    #arrAssignAST;

expression       : simpleExpression (ROPERATOR simpleExpression)* 										#expressionAST;

simpleExpression : term (AOP term)* 																	#simpleExpressionAST;

term             : factor (MOP factor)* 																#termAST;

factor           :
    literal                   |
    ID (PUNTO ID)?            |
    funtionCall               |
    arrayLookup               |
    arrayLength               |
    subExpression             |
    arrayAllocationExpression |
    allocationExpression      |
    unary 																								#factoAST;


unary                     : (UNARY) (expression)* 														#unaryAST;

allocationExpression      : NEW ID PIZQ PDER 															#allocationExprAST;

arrayAllocationExpression : NEW STYPE PCIZQ expression PCDER 											#arrAllocationExprAST;

subExpression    : PIZQ expression PDER                                                                 #subExprAST;

funtionCall      : ID PIZQ (actualParams)? PDER 														#functionCallAST;

actualParams     : expression (COMA expression)* 														#actualParamsAST;

arrayLookup      : ID PCIZQ expression                                                                  #arrLookupAST;

arrayLength      : ID PUNTO LENGTH 															            #arrLengthAST;

literal          : INTLITERAL  | REALLITERAL | BOOLITERAL | STRINGLITERAL 								#literalAST;