package analysisContext;

import generated.miParser;
import generated.miParserBaseVisitor;
import org.antlr.v4.runtime.Token;

import java.util.Objects;

public class AnalisisContextual extends miParserBaseVisitor {
    private TablaSimbolos tabla;

   public AnalisisContextual() {

       tabla = new TablaSimbolos();
   }

    @Override
    public Object visitStype(miParser.StypeContext ctx) {

        switch (ctx.getText()) {
            case "int":
                return "int";
            case "char":
                return "char";
            case "string":
                return "string";
            case "boolean":
                return "boolean";
            default:
                System.out.println(ctx.getText() + " no es un tipo de dato válido");
                return null;
        }
    }

    @Override
    public Object visitBooleanLiteral(miParser.BooleanLiteralContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitIntLiteralAST(miParser.IntLiteralASTContext ctx) {
        return "int";
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
            String exprArray = exprType + "[]";

            if (exprType == null) {
                Ident id = tabla.buscar(ctx.expression().getText());
                if( id != null && id.nivel >= tabla.nivelActual){
                    if( !id.type.equals((String) exprType2)){
                        System.out.println("A la variable \"" + ctx.ID() +"\" solo se le puede asignar valores con el mismo tipo de datos.");
                    }else{
                    Ident exist = tabla.buscar(ctx.ID().getText());
                    if (exist == null){
                        Object attr = this.visit(ctx.type());
                        if(attr != null) {
                            tabla.insertar(ctx.ID().getSymbol(), (String) attr, ctx);
                        }
                    }
                    else {
                        System.out.println("Ya hay una variable existente con el nombre \"" + exist.tok.getText() + "\".");
                    }}
                }
                else {
                    System.out.println("La asignación: \"" + ctx.expression().getText() + "\", no corresponde a un tipo de dato!");
                }
            } else if (exprArray.equals(exprType2) ) {
                Ident exist = tabla.buscar(ctx.ID().getText());
                if (exist != null && exist.nivel == tabla.nivelActual){
                    System.out.println("Ya hay una variable existente con el nombre \"" + exist.tok.getText() + "\".");
                }
                else {
                    tabla.insertar(ctx.ID().getSymbol(), (String) exprArray, ctx);
                }
            } else if (!exprType2.equals(exprType)){
                System.out.println("A la variable: "+ctx.ID()+" tipo: <" + ctx.type().getText() + "> no se le puede asignar el tipo <" + exprType + ">.");

            } else {
                Ident exist = tabla.buscar(ctx.ID().getText());
                if (exist == null){
                    Object attr = this.visit(ctx.type());
                    if(attr != null) {
                        tabla.insertar(ctx.ID().getSymbol(), (String) attr, ctx);
                    }
                }
                else {
                    System.out.println("Ya hay una variable existente con el nombre \"" + exist.tok.getText() + "\".");
                }
            }
        }catch (Exception e){
            Ident exist = tabla.buscar(ctx.ID().getText());
            if (exist != null && exist.nivel == tabla.nivelActual){
                System.out.println("Ya hay una variable existente con el nombre \"" + exist.tok.getText() + "\".");
            }
            else {
                Object attr = this.visit(ctx.type());
                if(attr != null) {
                    tabla.insertar(ctx.ID().getSymbol(), (String) attr, ctx);
                }
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

        return this.visit(ctx.arrayType());
    }

    @Override
    public Object visitIdTypeAST(miParser.IdTypeASTContext ctx) {
        //System.out.println("ID DE TYPE");
        return this.visit(ctx.ID());
    }

    @Override
    public Object visitArrTypeAST(miParser.ArrTypeASTContext ctx) {
        return this.visit(ctx.stype());
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

        String simpleExpre1 = (String) this.visit(ctx.expression(0));
        String simpleExpre2 = (String) this.visit(ctx.expression(1));

        Ident id = null;
        id = tabla.buscar(ctx.ID().getText());

        if(id != null){
            String type = id.type.substring(0, id.type.length()-2);
            //Preguntar esto de los niveles porque tengo dudas
            if( id.nivel > tabla.nivelActual){
                System.out.println(ctx.ID() + "No ha sido asignado en este nivel!");

            }else if(simpleExpre1 == null){
                id = tabla.buscar(ctx.expression(0).getText());
                if(id != null) {
                    if (!type.equals(id.type)) {
                        System.out.println("El index ingresado no es un dato valido, debe ser tipo int.");
                }
                }else {
                    System.out.println("El index ingresado no es un dato valido o no ha sido declarado.");
                }
            } else if(!simpleExpre1.equals("int")){
                System.out.println("Para acceder a la posición del arreglo debe ingresar un dato tipo int en el index.");

            }
            else if(!type.equals(simpleExpre2)){
                System.out.println("El array es de tipo \""+id.type+", debe asignar datos del mismo tipo al array.");
            }
        }else {
            System.out.println("El array "+ctx.ID() + " aún no ha sido declarado!");
        }



        return this.visit(ctx.expression(0));
    }

    @Override
    public Object visitExpressionAST(miParser.ExpressionASTContext ctx) {
        String exprType = null;
        String exprType2 = null;
        String operador = null;
        Ident id1 = null;
        Ident id2 = null;

        exprType = (String) this.visit(ctx.simpleExpression(0));

        for (int i = 1; i < ctx.simpleExpression().size(); i++) {
            operador = ctx.ROPERATOR().get(0).toString();

            try {
                exprType2 = (String) this.visit(ctx.simpleExpression(i));

                    if ((operador.equals("<") || operador.equals(">") || operador.equals("<=") || operador.equals(">="))) {
                        assert exprType != null;
                        if (!exprType.equals("int") || !exprType2.equals("int"))
                            System.out.println("Error, <"+exprType+"> y <"+exprType2 +">  incompatibles, sólo se permite tipo int en el operador  \"" + operador + "\".");
                    } else {
                        assert exprType != null;
                        if (!exprType.equals(exprType2) && exprType2 != null) {
                            System.out.println("Error, <"+exprType+"> y <"+exprType2 + "> son incompatibles, sólo se permiten datos de mismo tipo en el operador \"" + operador + "\".");
                        }else {
                            id2 = tabla.buscar(ctx.simpleExpression(i).getText());
                            if(!exprType.equals(id2.type))
                                System.out.println("Error, <"+exprType+"> y <"+id2.type + "> son incompatibles, sólo se permiten datos de mismo tipo en el operador \"" + operador + "\".");
                        }
                    }

            }catch (Exception e){
                //
                id1 = tabla.buscar(ctx.simpleExpression(i-1).getText());
                id2 = tabla.buscar(ctx.simpleExpression(i).getText());
                if(operador.equals("<") || operador.equals(">") || operador.equals("<=") || operador.equals(">=")){
                    {
                        id2 = tabla.buscar(ctx.simpleExpression(i).getText());
                        if(exprType2 != null && exprType != null){
                            if (exprType.equals("boolean") || exprType.equals("char") || exprType.equals("string") || exprType2.equals("string") || (exprType2.equals("boolean") || exprType2.equals("char"))){
                                System.out.println("Error, <"+exprType+"> y <"+exprType2 +"> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + operador + "\".");
                            }else if(!exprType2.equals(exprType)){
                                System.out.println("Error, <"+exprType+"> y <"+exprType2 +"> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + operador + "\".");
                            }
                        }else if( exprType2 == null && exprType != null){
                            if(!exprType.equals(id2.type)){
                                System.out.println("Error, <"+exprType+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                            }else if(exprType.equals("boolean") || exprType.equals("char") || exprType.equals("string")){
                                System.out.println("Error, <"+exprType+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + operador + "\".");
                            }
                        }else if(exprType == null && exprType2 != null){
                            id1 = tabla.buscar(ctx.simpleExpression(i-1).getText());
                            if(!exprType2.equals(id1.type )){
                                System.out.println("Error, <"+id1.type+"> y <"+exprType2 +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                            }else if(exprType2.equals("boolean") || exprType2.equals("char") || exprType2.equals("string")){
                                System.out.println("Error, <"+exprType2+"> y <"+id1.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + operador + "\".");
                            }
                        }else {
                            id1 = tabla.buscar(ctx.simpleExpression(i-1).getText());

                            if(id1.type.equals("char") || id1.type.equals("boolean") || id1.type.equals("string")){
                                System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                            }else if(!id1.type.equals(id2.type)){
                                System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                            }
                        }

                    }} else if(id1 != null && id2 != null ){

                    if (!id2.type.equals(id1.type)){
                        System.out.println("Error,<"+id1.type+"> y <"+ id2.type +"> son tipos de datos incompatibles, sólo se permite datos de igual tipo.");
                    }
                }else if( id1 != null){
                    if( !id1.type.equals(exprType2))
                        System.out.println("Error,<"+id1.type+"> y <"+ exprType2 +"> son tipos de datos incompatibles, sólo se permite datos de igual tipo.");
                }else if( id2 != null){
                    if( !id2.type.equals(exprType2))
                        System.out.println("Error,<"+id2.type+"> y <"+ exprType +"> son tipos de datos incompatibles, sólo se permite datos de igual tipo.");
                }else if(exprType != null) {
                    System.out.println("\""+ctx.simpleExpression(i).getText() + "\" no a sido declarado.");
                }else if(exprType2 != null){
                    System.out.println("\""+ctx.simpleExpression(i-1).getText() +  "\" no a sido declarado.");
                }
            }
        }
        //System.out.println(" operator" + ctx.simpleExpression(0).);
        if (operador == null) {
            return exprType;
        }
        else {
            return null;
        }
    }

    @Override
    public Object visitSimpleExpressionAST(miParser.SimpleExpressionASTContext ctx) {

        String simpleExpre1 = null;
        String simpleExpre2;
        String aOperador = null;
        Ident id1 = null;
        Ident id2 = null;

        simpleExpre1 = (String) this.visit(ctx.term(0));

        for (int i = 1; i < ctx.term().size(); i++) {

            aOperador = ctx.AOP().get(i-1).getText();

            try {
                simpleExpre2 = (String) this.visit(ctx.term(i));

                if (aOperador.equals("||")) {
                    id2 = tabla.buscar(ctx.term(i).getText());
                    if(simpleExpre2 != null && simpleExpre1 != null){
                        if (simpleExpre1.equals("string") || simpleExpre1.equals("char") || (simpleExpre2.equals("string") || simpleExpre2.equals("char"))){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+simpleExpre2 +"> son  incompatibles, sólo se permiten tipos (int o boolean) en el operador  \"" + aOperador + "\".");
                        }else if(!simpleExpre2.equals(simpleExpre1)){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+simpleExpre2 +"> son  incompatibles, sólo se permiten tipos (int o boolean) en el operador  \"" + aOperador + "\".");
                        }
                    }else if( simpleExpre2 == null && simpleExpre1 != null){
                        if(!simpleExpre1.equals(id2.type )){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + aOperador + "\".");
                        }else if(simpleExpre1.equals("string") || simpleExpre1.equals("char")){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o boolean) en el operador  \"" + aOperador + "\".");
                        }
                    }else if(simpleExpre1 == null && simpleExpre2 != null){
                        id1 = tabla.buscar(ctx.term(i-1).getText());
                        if(!simpleExpre2.equals(id1.type )){
                            System.out.println("Error, <"+id1.type+"> y <"+simpleExpre2 +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + aOperador + "\".");
                        }else if(simpleExpre2.equals("string") || simpleExpre2.equals("char")){
                            System.out.println("Error, <"+simpleExpre2+"> y <"+id1.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o boolean) en el operador  \"" + aOperador + "\".");
                        }
                    }else {
                        id1 = tabla.buscar(ctx.term(i-1).getText());

                            if(id1.type.equals("char") || id1.type.equals("string")){
                                System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + aOperador + "\".");
                            }else if(!id1.type.equals(id2.type)){
                                System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + aOperador + "\".");
                            }
                    }

                }else if(aOperador.equals("+") ) {
                    id2 = tabla.buscar(ctx.term(i).getText());
                    if(simpleExpre2 != null && simpleExpre1 != null){
                        if (simpleExpre1.equals("boolean") || simpleExpre1.equals("char") || (simpleExpre2.equals("boolean") || simpleExpre2.equals("char"))){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+simpleExpre2 +"> son  incompatibles, sólo se permiten tipos (int o string) en el operador  \"" + aOperador + "\".");
                        }else if(!simpleExpre2.equals(simpleExpre1)){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+simpleExpre2 +"> son  incompatibles, sólo se permiten tipos (int o string) en el operador  \"" + aOperador + "\".");
                        }
                    }else if( simpleExpre2 == null && simpleExpre1 != null){
                        if(!simpleExpre1.equals(id2.type)){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".");
                        }else if(simpleExpre1.equals("boolean") || simpleExpre1.equals("char")){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o string) en el operador  \"" + aOperador + "\".");
                        }
                    }else if(simpleExpre1 == null && simpleExpre2 != null){
                        id1 = tabla.buscar(ctx.term(i-1).getText());
                        if(!simpleExpre2.equals(id1.type )){
                            System.out.println("Error, <"+id1.type+"> y <"+simpleExpre2 +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".");
                        }else if(simpleExpre2.equals("boolean") || simpleExpre2.equals("char")){
                            System.out.println("Error, <"+simpleExpre2+"> y <"+id1.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o string) en el operador  \"" + aOperador + "\".");
                        }
                    }else {
                        id1 = tabla.buscar(ctx.term(i-1).getText());

                        if(id1.type.equals("char") || id1.type.equals("boolean")){
                            System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".");
                        }else if(!id1.type.equals(id2.type)){
                            System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".");
                        }
                    }
                }else if(aOperador.equals("-")){
                    id2 = tabla.buscar(ctx.term(i).getText());
                    if(simpleExpre2 != null && simpleExpre1 != null){
                        if (simpleExpre1.equals("boolean") || simpleExpre1.equals("char") || simpleExpre1.equals("string") || simpleExpre2.equals("string") || (simpleExpre2.equals("boolean") || simpleExpre2.equals("char"))){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+simpleExpre2 +"> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + aOperador + "\".");
                        }else if(!simpleExpre2.equals(simpleExpre1)){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+simpleExpre2 +"> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + aOperador + "\".");
                        }
                    }else if( simpleExpre2 == null && simpleExpre1 != null){
                        if(!simpleExpre1.equals(id2.type)){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".");
                        }else if(simpleExpre1.equals("boolean") || simpleExpre1.equals("char") || simpleExpre1.equals("string")){
                            System.out.println("Error, <"+simpleExpre1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + aOperador + "\".");
                        }
                    }else if(simpleExpre1 == null && simpleExpre2 != null){
                        id1 = tabla.buscar(ctx.term(i-1).getText());
                        if(!simpleExpre2.equals(id1.type )){
                            System.out.println("Error, <"+id1.type+"> y <"+simpleExpre2 +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".");
                        }else if(simpleExpre2.equals("boolean") || simpleExpre2.equals("char") || simpleExpre2.equals("string")){
                            System.out.println("Error, <"+simpleExpre2+"> y <"+id1.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + aOperador + "\".");
                        }
                    }else {
                        id1 = tabla.buscar(ctx.term(i-1).getText());

                        if(id1.type.equals("char") || id1.type.equals("boolean") || id1.type.equals("string")){
                            System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".");
                        }else if(!id1.type.equals(id2.type)){
                            System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".");
                        }
                    }

                }
            }catch (Exception e){
                System.out.println("El termino: < "+ ctx.term(i).getText() + " > no corresponde a un dato valido!");
            }

        }
        if(aOperador == null) return simpleExpre1;
        return null;

    }

    @Override
    public Object visitTermAST(miParser.TermASTContext ctx) {

        String factor1 = null;
        String factor2 = null;
        String mOperador = null;
        Ident id1 = null;
        Ident id2 = null;

        factor1 = (String) this.visit(ctx.factor(0));
        for (int i = 1; i < ctx.factor().size(); i++) {

            mOperador = ctx.MOP().get(i-1).getText();
            try {
                factor2 = (String) this.visit(ctx.factor(i));

                if(mOperador.equals("*")){
                    id2 = tabla.buscar(ctx.factor(i).getText());
                    if(factor2 != null && factor1 != null){
                        if (factor1.equals("boolean") || factor1.equals("char") || factor1.equals("string") || factor2.equals("string") || (factor2.equals("boolean") || factor2.equals("char"))){
                            System.out.println("Error, <"+factor1+"> y <"+factor2 +"> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + mOperador + "\".");
                        }else if(!factor2.equals(factor1)){
                            System.out.println("Error, <"+factor1+"> y <"+factor2 +"> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + mOperador + "\".");
                        }
                    }else if( factor2 == null && factor1 != null){
                        if(!factor1.equals(id2.type)){
                            System.out.println("Error, <"+factor1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                        }else if(factor1.equals("boolean") || factor1.equals("char") || factor1.equals("string")){
                            System.out.println("Error, <"+factor1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + mOperador + "\".");
                        }
                    }else if(factor1 == null && factor2 != null){
                        id1 = tabla.buscar(ctx.factor(i-1).getText());
                        if(!factor2.equals(id1.type )){
                            System.out.println("Error, <"+id1.type+"> y <"+factor2 +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                        }else if(factor2.equals("boolean") || factor2.equals("char") || factor2.equals("string")){
                            System.out.println("Error, <"+factor2+"> y <"+id1.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + mOperador + "\".");
                        }
                    }else {
                        id1 = tabla.buscar(ctx.factor(i-1).getText());

                        if(id1.type.equals("char") || id1.type.equals("boolean") || id1.type.equals("string")){
                            System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                        }else if(!id1.type.equals(id2.type)){
                            System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                        }
                    }

                } else  if(mOperador.equals("/")){
                    id2 = tabla.buscar(ctx.factor(i).getText());
                    if(factor2 != null && factor1 != null){
                        if (factor1.equals("boolean") || factor1.equals("char") || factor1.equals("string") || factor2.equals("string") || (factor2.equals("boolean") || factor2.equals("char"))){
                            System.out.println("Error, <"+factor1+"> y <"+factor2 +"> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + mOperador + "\".");
                        }else if(!factor2.equals(factor1)){
                            System.out.println("Error, <"+factor1+"> y <"+factor2 +"> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + mOperador + "\".");
                        }
                    }else if( factor2 == null && factor1 != null){
                        if(!factor1.equals(id2.type)){
                            System.out.println("Error, <"+factor1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                        }else if(factor1.equals("boolean") || factor1.equals("char") || factor1.equals("string")){
                            System.out.println("Error, <"+factor1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + mOperador + "\".");
                        }
                    }else if(factor1 == null && factor2 != null){
                        id1 = tabla.buscar(ctx.factor(i-1).getText());
                        if(!factor2.equals(id1.type )){
                            System.out.println("Error, <"+id1.type+"> y <"+factor2 +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                        }else if(factor2.equals("boolean") || factor2.equals("char") || factor2.equals("string")){
                            System.out.println("Error, <"+factor2+"> y <"+id1.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + mOperador + "\".");
                        }
                    }else {
                        id1 = tabla.buscar(ctx.factor(i-1).getText());

                        if(id1.type.equals("char") || id1.type.equals("boolean") || id1.type.equals("string")){
                            System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                        }else if(!id1.type.equals(id2.type)){
                            System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                        }
                    }

                } else if (mOperador.equals("&&")) {
                    id2 = tabla.buscar(ctx.factor(i).getText());
                    if(factor2 != null && factor1 != null){
                        if (factor1.equals("string") || factor1.equals("char") || (factor2.equals("string") || factor2.equals("char"))){
                            System.out.println("Error, <"+factor1+"> y <"+factor2 +"> son  incompatibles, sólo se permiten tipos (int o boolean) en el operador  \"" + mOperador + "\".");
                        }else if(!factor2.equals(factor1)){
                            System.out.println("Error, <"+factor1+"> y <"+factor2 +"> son  incompatibles, sólo se permiten tipos (int o boolean) en el operador  \"" + mOperador + "\".");
                        }
                    }else if( factor2 == null && factor1 != null){
                        if(!factor1.equals(id2.type )){
                            System.out.println("Error, <"+factor1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + mOperador + "\".");
                        }else if(factor1.equals("string") || factor1.equals("char")){
                            System.out.println("Error, <"+factor1+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o boolean) en el operador  \"" + mOperador + "\".");
                        }
                    }else if(factor1 == null && factor2 != null){
                        id1 = tabla.buscar(ctx.factor(i-1).getText());
                        if(!factor2.equals(id1.type )){
                            System.out.println("Error, <"+id1.type+"> y <"+factor2 +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + mOperador + "\".");
                        }else if(factor2.equals("string") || factor2.equals("char")){
                            System.out.println("Error, <"+factor2+"> y <"+id1.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o boolean) en el operador  \"" + mOperador + "\".");
                        }
                    }else {
                        id1 = tabla.buscar(ctx.factor(i-1).getText());

                        if(id1.type.equals("char") || id1.type.equals("string")){
                            System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + mOperador + "\".");
                        }else if(!id1.type.equals(id2.type)){
                            System.out.println("Error, <"+id1.type+"> y <"+id2.type +"> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + mOperador + "\".");
                        }
                    }
                }

            }catch (Exception e){
                System.out.println("El termino: < "+ ctx.factor(i).getText() + " > no corresponde a un dato valido!");
            }


        }

        if(mOperador == null) return factor1;
        return null;
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
        return this.visit(ctx.unary()) ;
    }

    @Override
    public Object visitUnaryAST(miParser.UnaryASTContext ctx) {

        String unaryOperator = (ctx.UNARY().getText());
        String unary2;
        String unary1 = (String) this.visit(ctx.expression(0));
        for (int i = 1; i < ctx.expression().size(); i++) {

            unary2 = (String) this.visit(ctx.expression(i));
        }
        if (unaryOperator == null)return unary1;

        return null;
    }

    @Override
    public Object visitAllocationExprAST(miParser.AllocationExprASTContext ctx) {
        //

        return null;
    }

    @Override
    public Object visitArrAllocationExprAST(miParser.ArrAllocationExprASTContext ctx) {

        Object attr = this.visit(ctx.expression());
        Ident id = null;
        if(attr != null){
            if ( attr != "int"){
                System.out.println("La inicialización de los arreglos deben de ser siempre de tipo int y se esta usando  tipo \""+attr+"\"");
            }
        }else{
            id = tabla.buscar(ctx.expression().getText());
            if(id != null) {
                if (!id.type.equals("int")) {
                    System.out.println("El index ingresado no es un dato valido, debe ser tipo int.");
                }else {
                    return id.type;
                }
            }else {
                System.out.println("Debe ingresar un index valido para el arreglo.");
            }
        }
        /*if(attr == null) {
            assert id != null;
            return id.type;
        }*/
        return this.visit(ctx.expression());
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
