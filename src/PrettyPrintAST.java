
import generated.miParser;
import generated.miParserBaseVisitor;

public class PrettyPrintAST<Object> extends miParserBaseVisitor<Object> {

    @Override
    public Object visitProgramAST(miParser.ProgramASTContext ctx) {
        System.out.println(ctx.getClass().getSimpleName() + "VisitProgramAST");
        for( miParser.StatementContext context : ctx.statement())
            this.visit(context);
        return null;
    }

    @Override
    public Object visitVariableDeclStaAST(miParser.VariableDeclStaASTContext ctx) {

        System.out.println(ctx.getClass().getSimpleName()+"Variable");
        this.visit(ctx.variableDeclaration());
        return null;
    }

    @Override
    public Object visitClassDeclStaAST(miParser.ClassDeclStaASTContext ctx) {

        this.visit(ctx.classDeclaration());
        return null;
    }

    @Override
    public Object visitAssignStaAST(miParser.AssignStaASTContext ctx) {
        System.out.println("Estoy en asignación");
        this.visit(ctx.assigment());
        return null;
    }
    @Override
    public Object visitArrAsignStaAST(miParser.ArrAsignStaASTContext ctx) {

        this.visit(ctx.arrayAssignment());
        return null;
    }

    @Override
    public Object visitPrintStaAST(miParser.PrintStaASTContext ctx) {

        visit(ctx. printStatement());
        return null;
    }

    @Override
    public Object visitIfStaAST(miParser.IfStaASTContext ctx) {

        visit(ctx.ifStatement());
        return null;
    }

    @Override
    public Object visitWhileStaAST(miParser.WhileStaASTContext ctx) {
        visit(ctx.whileStatement());
        return null;
    }

    @Override
    public Object visitReturnStaAST(miParser.ReturnStaASTContext ctx) {

        visit(ctx.returnStatement());
        return null;
    }

    @Override
    public Object visitFunctionDeclStaAST(miParser.FunctionDeclStaASTContext ctx) {

        visit(ctx.funtionDeclaration());
        return null;
    }

    @Override
    public Object visitBlockStaAST(miParser.BlockStaASTContext ctx) {

        visit(ctx.block());
        return null;
    }

    @Override
    public Object visitBlockAST(miParser.BlockASTContext ctx) {

        for(miParser.StatementContext c: ctx.statement())
            this.visit(c);
        return null;
    }

    /**
     * En este tengo duda con el formal Params ya que lleva sigo de interrogación
     * @param ctx
     * @return
     */
    @Override
    public Object visitFunctionDeclAST(miParser.FunctionDeclASTContext ctx) {
        this.visit(ctx.type());
        this.visit(ctx.formalParams());
        this.visit(ctx.block());
        return null;
    }

    @Override
    public Object visitFParamsAST(miParser.FParamsASTContext ctx) {
        for(miParser.FormalParamContext c: ctx.formalParam()){
            this.visit(c);
        }
        return null;
    }

    @Override
    public Object visitFParamAST(miParser.FParamASTContext ctx) {
        this.visit(ctx.type());
        return null;
    }

    @Override
    public Object visitWhileStmmtAST(miParser.WhileStmmtASTContext ctx) {
        this.visit(ctx.expression());
        this.visit(ctx.block());
        return null;
    }

    @Override
    public Object visitIfStmntAST(miParser.IfStmntASTContext ctx) {
        this.visit(ctx.expression());
        this.visit(ctx.block(0));
        this.visit(ctx.block(1));
        return null;
    }

    @Override
    public Object visitReturnStmntAST(miParser.ReturnStmntASTContext ctx) {
        this.visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitPrintStmntAST(miParser.PrintStmntASTContext ctx) {
        this.visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitClassDelcAST(miParser.ClassDelcASTContext ctx) {
        for(miParser.ClassVariableDeclarationContext c : ctx.classVariableDeclaration())
            this.visit(c);
        return null;
    }

    @Override
    public Object visitClassVariableDeclAST(miParser.ClassVariableDeclASTContext ctx) {
        this.visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitVariableDeclAST(miParser.VariableDeclASTContext ctx) {

        this.visit(ctx.type());
        this.visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitStypeTypeAST(miParser.StypeTypeASTContext ctx) {
        System.out.println("STYPE DE TYPE");
        return null;
    }

    @Override
    public Object visitArrtypeTypeAST(miParser.ArrtypeTypeASTContext ctx) {
        this.visit(ctx.arrayType());
        return null;
    }

    @Override
    public Object visitIdTypeAST(miParser.IdTypeASTContext ctx) {
        System.out.println("ID DE TYPE");
        return null;
    }

    @Override
    public Object visitArrTypeAST(miParser.ArrTypeASTContext ctx) {
        System.out.println("arrayType");
        return null;
    }

    @Override
    public Object visitAsssignAST(miParser.AsssignASTContext ctx) {
        this.visit(ctx.expression());
        return null;
    }

    /**
     * Aqui porque sino lleva if porque asi
     * @param ctx
     * @return
     */
    @Override
    public Object visitArrAssignAST(miParser.ArrAssignASTContext ctx) {
        this.visit(ctx.expression(0));
        this.visit(ctx.expression(1));
        return null;
    }

    @Override
    public Object visitExpressionAST(miParser.ExpressionASTContext ctx) {
        for (miParser.SimpleExpressionContext c: ctx.simpleExpression())
            this.visit(c);
        return null;
    }

    @Override
    public Object visitSimpleExpressionAST(miParser.SimpleExpressionASTContext ctx) {
        for (miParser.TermContext c: ctx.term())
            this.visit(c);
        return null;
    }

    @Override
    public Object visitTermAST(miParser.TermASTContext ctx) {

        for (miParser.FactorContext c: ctx.factor())
            this.visit(c);
        return null;
    }

    @Override
    public Object visitLiteralFactAST(miParser.LiteralFactASTContext ctx) {
        this.visit(ctx.literal());
        return null;
    }

    @Override
    public Object visitPuntIdFactAST(miParser.PuntIdFactASTContext ctx) {
        System.out.println("FACTOR ID PUNTO ID");
        return null;
    }

    @Override
    public Object visitFuntionCallFactAST(miParser.FuntionCallFactASTContext ctx) {
        this.visit(ctx.funtionCall());
        return null;
    }

    @Override
    public Object visitArrayLokupFactAST(miParser.ArrayLokupFactASTContext ctx) {
        this.visit(ctx.arrayLookup());
        return null;
    }

    @Override
    public Object visitLengthFactAST(miParser.LengthFactASTContext ctx) {
        this.visit(ctx.arrayLength());
        return null;
    }


    @Override
    public Object visitArrayAlloExpreFactAST(miParser.ArrayAlloExpreFactASTContext ctx) {
        this.visit(ctx.arrayAllocationExpression());
        return null;
    }

    @Override
    public Object visitAllocaExpreFactAST(miParser.AllocaExpreFactASTContext ctx) {
        this.visit(ctx.allocationExpression());
        return null;
    }

    @Override
    public Object visitUnaryFactAST(miParser.UnaryFactASTContext ctx) {
        this.visit(ctx.unary());
        return null;
    }

    @Override
    public Object visitUnaryAST(miParser.UnaryASTContext ctx) {
        for (miParser.ExpressionContext c: ctx.expression())
            this.visit(c);
        return null;
    }

    @Override
    public Object visitAllocationExprAST(miParser.AllocationExprASTContext ctx) {
        System.out.println("NEW ID AllocationExpresion");
        return null;
    }

    @Override
    public Object visitArrAllocationExprAST(miParser.ArrAllocationExprASTContext ctx) {
        this.visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitSubExprAST(miParser.SubExprASTContext ctx) {
        this.visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitFunctionCallAST(miParser.FunctionCallASTContext ctx) {
        this.visit(ctx.actualParams());
        return null;
    }

    @Override
    public Object visitActualParamsAST(miParser.ActualParamsASTContext ctx) {
        for (miParser.ExpressionContext c: ctx.expression())
            this.visit(c);
        return null;
    }

    @Override
    public Object visitArrLookupAST(miParser.ArrLookupASTContext ctx) {
        this.visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitArrLengthAST(miParser.ArrLengthASTContext ctx) {
        System.out.println("ID PUNTO arrayLength");
        return null;
    }
}
