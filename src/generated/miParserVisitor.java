// Generated from C:/Users/warne/Desktop/proyecto-II-Compiladores\miParser.g4 by ANTLR 4.9.1
package generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link miParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface miParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code programAST}
	 * labeled alternative in {@link miParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramAST(miParser.ProgramASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableDeclStaAST}
	 * labeled alternative in {@link miParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclStaAST(miParser.VariableDeclStaASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDeclStaAST}
	 * labeled alternative in {@link miParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclStaAST(miParser.ClassDeclStaASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStaAST}
	 * labeled alternative in {@link miParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStaAST(miParser.AssignStaASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrAsignStaAST}
	 * labeled alternative in {@link miParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrAsignStaAST(miParser.ArrAsignStaASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStaAST}
	 * labeled alternative in {@link miParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStaAST(miParser.PrintStaASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStaAST}
	 * labeled alternative in {@link miParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStaAST(miParser.IfStaASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStaAST}
	 * labeled alternative in {@link miParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStaAST(miParser.WhileStaASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStaAST}
	 * labeled alternative in {@link miParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStaAST(miParser.ReturnStaASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionDeclStaAST}
	 * labeled alternative in {@link miParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclStaAST(miParser.FunctionDeclStaASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStaAST}
	 * labeled alternative in {@link miParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStaAST(miParser.BlockStaASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockAST}
	 * labeled alternative in {@link miParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockAST(miParser.BlockASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionDeclAST}
	 * labeled alternative in {@link miParser#funtionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclAST(miParser.FunctionDeclASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fParamsAST}
	 * labeled alternative in {@link miParser#formalParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFParamsAST(miParser.FParamsASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fParamAST}
	 * labeled alternative in {@link miParser#formalParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFParamAST(miParser.FParamASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStmmtAST}
	 * labeled alternative in {@link miParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmmtAST(miParser.WhileStmmtASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStmntAST}
	 * labeled alternative in {@link miParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmntAST(miParser.IfStmntASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStmntAST}
	 * labeled alternative in {@link miParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmntAST(miParser.ReturnStmntASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStmntAST}
	 * labeled alternative in {@link miParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmntAST(miParser.PrintStmntASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDelcAST}
	 * labeled alternative in {@link miParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDelcAST(miParser.ClassDelcASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classVariableDeclAST}
	 * labeled alternative in {@link miParser#classVariableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassVariableDeclAST(miParser.ClassVariableDeclASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableDeclAST}
	 * labeled alternative in {@link miParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclAST(miParser.VariableDeclASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stypeTypeAST}
	 * labeled alternative in {@link miParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStypeTypeAST(miParser.StypeTypeASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrtypeTypeAST}
	 * labeled alternative in {@link miParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrtypeTypeAST(miParser.ArrtypeTypeASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idTypeAST}
	 * labeled alternative in {@link miParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdTypeAST(miParser.IdTypeASTContext ctx);
	/**
	 * Visit a parse tree produced by {@link miParser#stype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStype(miParser.StypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrTypeAST}
	 * labeled alternative in {@link miParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrTypeAST(miParser.ArrTypeASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code asssignAST}
	 * labeled alternative in {@link miParser#assigment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsssignAST(miParser.AsssignASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrAssignAST}
	 * labeled alternative in {@link miParser#arrayAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrAssignAST(miParser.ArrAssignASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAST}
	 * labeled alternative in {@link miParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAST(miParser.ExpressionASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleExpressionAST}
	 * labeled alternative in {@link miParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpressionAST(miParser.SimpleExpressionASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code termAST}
	 * labeled alternative in {@link miParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermAST(miParser.TermASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalFactAST}
	 * labeled alternative in {@link miParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralFactAST(miParser.LiteralFactASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code puntIdFactAST}
	 * labeled alternative in {@link miParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPuntIdFactAST(miParser.PuntIdFactASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funtionCallFactAST}
	 * labeled alternative in {@link miParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuntionCallFactAST(miParser.FuntionCallFactASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayLokupFactAST}
	 * labeled alternative in {@link miParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLokupFactAST(miParser.ArrayLokupFactASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lengthFactAST}
	 * labeled alternative in {@link miParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthFactAST(miParser.LengthFactASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subExpressionFactAST}
	 * labeled alternative in {@link miParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubExpressionFactAST(miParser.SubExpressionFactASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAlloExpreFactAST}
	 * labeled alternative in {@link miParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAlloExpreFactAST(miParser.ArrayAlloExpreFactASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocaExpreFactAST}
	 * labeled alternative in {@link miParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocaExpreFactAST(miParser.AllocaExpreFactASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryFactAST}
	 * labeled alternative in {@link miParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryFactAST(miParser.UnaryFactASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryAST}
	 * labeled alternative in {@link miParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryAST(miParser.UnaryASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allocationExprAST}
	 * labeled alternative in {@link miParser#allocationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllocationExprAST(miParser.AllocationExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrAllocationExprAST}
	 * labeled alternative in {@link miParser#arrayAllocationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrAllocationExprAST(miParser.ArrAllocationExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subExprAST}
	 * labeled alternative in {@link miParser#subExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubExprAST(miParser.SubExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallAST}
	 * labeled alternative in {@link miParser#funtionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallAST(miParser.FunctionCallASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code actualParamsAST}
	 * labeled alternative in {@link miParser#actualParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualParamsAST(miParser.ActualParamsASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrLookupAST}
	 * labeled alternative in {@link miParser#arrayLookup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrLookupAST(miParser.ArrLookupASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrLengthAST}
	 * labeled alternative in {@link miParser#arrayLength}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrLengthAST(miParser.ArrLengthASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intLiteralAST}
	 * labeled alternative in {@link miParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiteralAST(miParser.IntLiteralASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code realLiteralAST}
	 * labeled alternative in {@link miParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealLiteralAST(miParser.RealLiteralASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolLiteralAST}
	 * labeled alternative in {@link miParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteralAST(miParser.BoolLiteralASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteralAST}
	 * labeled alternative in {@link miParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteralAST(miParser.StringLiteralASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charListeralAST}
	 * labeled alternative in {@link miParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharListeralAST(miParser.CharListeralASTContext ctx);
}