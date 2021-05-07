package analysisContext;

import generated.miParser;
import generated.miParserBaseVisitor;

public class AnalisisContextual extends miParserBaseVisitor {
    private TablaSimbolos tabla;

   public AnalisisContextual(){

       tabla = new TablaSimbolos();
    }

    @Override
    public Object visitStype(miParser.StypeContext ctx) {

       if(ctx.getText().equals("int")){
           return "int";
       }else if( ctx.getText().equals("char")){
           return "char";
       }else if( ctx.getText().equals("string")){
        return "string";
       }else if( ctx.getText().equals("boolean")){
        return "boolean";
    }else {
           System.out.println(ctx.getText() + " no es un tipo de dato válido");
           return null;
       }
    }

    @Override
    public Object visitIntLiteralAST(miParser.IntLiteralASTContext ctx) {
        return "int";
    }

    @Override
    public Object visitRealLiteralAST(miParser.RealLiteralASTContext ctx) {
        return "real";
    }

    @Override
    public Object visitBoolLiteralAST(miParser.BoolLiteralASTContext ctx) {
        return "boolean";
    }

    @Override
    public Object visitStringLiteralAST(miParser.StringLiteralASTContext ctx) {
        return "string";
    }

    @Override
    public Object visitCharListeralAST(miParser.CharListeralASTContext ctx) {
        return "char";
    }

    @Override
    public Object visitProgramAST(miParser.ProgramASTContext ctx) {
        //System.out.println(ctx.getClass().getSimpleName() + "VisitProgramAST");
        tabla.openScope();
        for( miParser.StatementContext context : ctx.statement())
            this.visit(context);
        tabla.imprimir();
        tabla.closeScope();

        return null;
    }

    @Override
    public Object visitVariableDeclStaAST(miParser.VariableDeclStaASTContext ctx) {

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

        //Object exprType =
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

        this.visit(ctx.stype());
        this.visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitVariableDeclAST(miParser.VariableDeclASTContext ctx) {

        try {
            Object exprType = (String) this.visit(ctx.expression());
            Object exprType2 = (String) (ctx.type().getText());
            if (exprType == null) {
                System.out.println("La asignación: <" + ctx.expression().getText() + ">, no corresponde a un tipo de dato!");
            } else if (!exprType2.equals(exprType)) {
                System.out.println("A la variable: "+ctx.ID()+" tipo: <" + ctx.type().getText() + "> no se le puede asignar el tipo <" + exprType + ">.");
            }else {
                Object attr = this.visit(ctx.type());
                if(attr != null) {

                    //System.out.println(ctx.ID() + " El ID " + attr);

                    tabla.insertar(ctx.ID().getSymbol(), (String) attr, ctx);
                }
            }
        }catch (Exception e){
            Object attr = this.visit(ctx.type());
            if(attr != null) {

               // System.out.println(ctx.ID() + " El ID " + attr);

                tabla.insertar(ctx.ID().getSymbol(), (String) attr, ctx);
            }
        }

        return null;
    }

    @Override
    public Object visitStypeTypeAST(miParser.StypeTypeASTContext ctx) {

        return this.visit(ctx.stype());
    }

    @Override
    public Object visitArrtypeTypeAST(miParser.ArrtypeTypeASTContext ctx) {
        this.visit(ctx.arrayType());
        return null;
    }

    @Override
    public Object visitIdTypeAST(miParser.IdTypeASTContext ctx) {
        //System.out.println("ID DE TYPE");
        return this.visit(ctx.ID());
    }

    @Override
    public Object visitArrTypeAST(miParser.ArrTypeASTContext ctx) {
        //System.out.println("arrayType");
        return null;
    }

    @Override
    public Object visitAsssignAST(miParser.AsssignASTContext ctx) {

        Ident id = tabla.buscar(ctx.ID(0).getText());

        if(id != null){

            Object exprType = this.visit(ctx.expression());

            if(exprType == null){
                System.out.println("La asignación: <" +ctx.expression().getText()+">, no corresponde a un tipo de dato!");
            }
            else if (id.type != exprType){
                System.out.println("Los tipos son imcompatibles para la asignación entre: <"+ id.type +"> y <"+exprType+">.");
            }
        }else {
            System.out.println("\""+ctx.ID(0).getText() + "\" no ha sido declarado!!!");
        }

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

        String exprType;
        String exprType2;

        exprType = (String) this.visit(ctx.simpleExpression(0));

        for (int i = 1; i < ctx.simpleExpression().size(); i++) {


            exprType2 = (String) this.visit(ctx.simpleExpression(i));
            //System.out.println(ctx.simpleExpression(i).getText() + "Probando");
            //Aqui debemos de validar todo esto
            //Validar si lo que vienen son strings y si se quieren multiplicar y todo eso

            if(!exprType.equals(exprType2))
                System.out.println("Error, tipos de datos incompatibles, sólo se permite tipo int en el operador relacional");
        }

        return exprType;
    }

    @Override
    public Object visitSimpleExpressionAST(miParser.SimpleExpressionASTContext ctx) {

        String term2;
        String term1 = (String) this.visit(ctx.term(0));
        for (int i = 1; i < ctx.term().size(); i++) {

            term2 = (String) this.visit(ctx.term(i));
        }
        return term1;

    }

    @Override
    public Object visitTermAST(miParser.TermASTContext ctx) {

        String factor2;
        String factor1 = (String) this.visit(ctx.factor(0));
        for (int i = 1; i < ctx.factor().size(); i++) {

            factor2 = (String) this.visit(ctx.factor(i));
        }
        return factor1;
    }

    @Override
    public Object visitLiteralFactAST(miParser.LiteralFactASTContext ctx) {

        return this.visit(ctx.literal());
    }

    @Override
    public Object visitPuntIdFactAST(miParser.PuntIdFactASTContext ctx) {
        //System.out.println("FACTOR ID PUNTO ID");
        return null;
    }

    @Override
    public Object visitFuntionCallFactAST(miParser.FuntionCallFactASTContext ctx) {
        this.visit(ctx.funtionCall());
        return this.visit(ctx.funtionCall());
    }

    @Override
    public Object visitArrayLokupFactAST(miParser.ArrayLokupFactASTContext ctx) {

        return this.visit(ctx.arrayLookup());
    }

    @Override
    public Object visitLengthFactAST(miParser.LengthFactASTContext ctx) {
        return  this.visit(ctx.arrayLength());
    }

    @Override
    public Object visitSubExpressionFactAST(miParser.SubExpressionFactASTContext ctx) {
        return this.visit(ctx.subExpression());
    }

    @Override
    public Object visitArrayAlloExpreFactAST(miParser.ArrayAlloExpreFactASTContext ctx) {

        return this.visit(ctx.arrayAllocationExpression());
    }

    @Override
    public Object visitAllocaExpreFactAST(miParser.AllocaExpreFactASTContext ctx) {

        return this.visit(ctx.allocationExpression());
    }

    @Override
    public Object visitUnaryFactAST(miParser.UnaryFactASTContext ctx) {
        return this.visit(ctx.unary());
    }

    @Override
    public Object visitUnaryAST(miParser.UnaryASTContext ctx) {

        String expression2;
        String expression = (String) this.visit(ctx.expression(0));
        for (int i = 1; i < ctx.expression().size(); i++) {

            expression2 = (String) this.visit(ctx.expression(i));
        }
        return expression;
    }

    @Override
    public Object visitAllocationExprAST(miParser.AllocationExprASTContext ctx) {
        //System.out.println("NEW ID AllocationExpresion");
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
        //System.out.println("ID PUNTO arrayLength");
        return null;
    }

}
