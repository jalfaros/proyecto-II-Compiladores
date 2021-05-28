package interprete;

import generated.miParser;
import generated.miParserBaseVisitor;

import java.util.Stack;

public class Interprete extends miParserBaseVisitor {

    private Stack<Object> pilaExpresiones;
    private Almacen almacenDatos;

    public Interprete(){
        this.pilaExpresiones = new Stack<Object>();
        this.almacenDatos = new Almacen();
    }

    @Override
    public Object visitProgramAST(miParser.ProgramASTContext ctx) {
        return super.visitProgramAST(ctx);
    }

    @Override
    public Object visitVariableDeclStaAST(miParser.VariableDeclStaASTContext ctx) {
        return super.visitVariableDeclStaAST(ctx);
    }

    @Override
    public Object visitClassDeclStaAST(miParser.ClassDeclStaASTContext ctx) {
        return super.visitClassDeclStaAST(ctx);
    }

    @Override
    public Object visitAssignStaAST(miParser.AssignStaASTContext ctx) {
        return super.visitAssignStaAST(ctx);
    }

    @Override
    public Object visitArrAsignStaAST(miParser.ArrAsignStaASTContext ctx) {
        return super.visitArrAsignStaAST(ctx);
    }

    @Override
    public Object visitPrintStaAST(miParser.PrintStaASTContext ctx) {
        return super.visitPrintStaAST(ctx);
    }

    @Override
    public Object visitIfStaAST(miParser.IfStaASTContext ctx) {
        return super.visitIfStaAST(ctx);
    }

    @Override
    public Object visitWhileStaAST(miParser.WhileStaASTContext ctx) {
        return super.visitWhileStaAST(ctx);
    }

    @Override
    public Object visitReturnStaAST(miParser.ReturnStaASTContext ctx) {
        return super.visitReturnStaAST(ctx);
    }

    @Override
    public Object visitFunctionDeclStaAST(miParser.FunctionDeclStaASTContext ctx) {
        return super.visitFunctionDeclStaAST(ctx);
    }

    @Override
    public Object visitBlockStaAST(miParser.BlockStaASTContext ctx) {

        almacenDatos.openScope();
        this.visit(ctx.block());
        almacenDatos.closeScope();
        return null;
    }

    @Override
    public Object visitBlockAST(miParser.BlockASTContext ctx) {

        return super.visitBlockAST(ctx);
    }

    @Override
    public Object visitFunctionDeclAST(miParser.FunctionDeclASTContext ctx) {

        almacenDatos.agregarInstancia(ctx.ID().getText(), null, ctx);
        return null;
    }

    @Override
    public Object visitFParamsAST(miParser.FParamsASTContext ctx) {

        for(miParser.FormalParamContext p: ctx.formalParam()){
            almacenDatos.agregarInstancia((String) this.visit(p), pilaExpresiones.pop());
        }

        return null;
    }

    @Override
    public Object visitFParamAST(miParser.FParamASTContext ctx) {
        return ctx.ID().getText();
    }

    @Override
    public Object visitWhileStmmtAST(miParser.WhileStmmtASTContext ctx) {

        almacenDatos.openScope();
        while ((Boolean) this.visit(ctx.expression())){
            this.visit(ctx.block());
        }
        almacenDatos.closeScope();
        return super.visitWhileStmmtAST(ctx);
    }

    @Override
    public Object visitIfStmntAST(miParser.IfStmntASTContext ctx) {
        almacenDatos.openScope();
        try{
            if(ctx.block(1) != null){
                if((boolean) this.visit(ctx.expression())){
                    this.visit(ctx.block(0));
                }else {
                    this.visit(ctx.block(1));
                }
            }else {
                if((boolean) this.visit(ctx.expression())){
                    this.visit(ctx.block(0));
                }
            }

        }catch (Exception e){
            System.out.println("Error, sólo se permiten expresiones booleanas en el if. \n");
        }
        almacenDatos.closeScope();
        return null;
    }

    @Override
    public Object visitReturnStmntAST(miParser.ReturnStmntASTContext ctx) {
        pilaExpresiones.push(this.visit(ctx.expression()));
        return null;
    }

    @Override
    public Object visitPrintStmntAST(miParser.PrintStmntASTContext ctx) {
        System.out.println(this.visit(ctx.expression()) + " <-- Imprimiendo el valor de "+ ctx.expression().getText());
        return null;
    }

    @Override
    public Object visitClassDelcAST(miParser.ClassDelcASTContext ctx) {
        return super.visitClassDelcAST(ctx);
    }

    @Override
    public Object visitClassVariableDeclAST(miParser.ClassVariableDeclASTContext ctx) {

        return super.visitClassVariableDeclAST(ctx);
    }

    @Override
    public Object visitVariableDeclAST(miParser.VariableDeclASTContext ctx) {
        String tipo = (String) visit(ctx.type());

        if(ctx.ASSIGN() == null){
            switch (tipo) {
                case "int" -> this.almacenDatos.agregarInstancia(ctx.ID().getText(), 0);
                case "char" -> this.almacenDatos.agregarInstancia(ctx.ID().getText(), ' ');
                case "string" -> this.almacenDatos.agregarInstancia(ctx.ID().getText(), "");
                case "boolean" -> this.almacenDatos.agregarInstancia(ctx.ID().getText(), false);
                //Agrego por defecto un array que no tiene instancia
                default -> this.almacenDatos.agregarInstancia(ctx.ID().getText(), new Object[0]);
            }
        }else {
            if(tipo.equals("int") || tipo.equals("char") || tipo.equals("string") || tipo.equals("boolean")){
                this.almacenDatos.agregarInstancia(ctx.ID().getText(), this.visit(ctx.expression()));
            }else if(tipo.equals("int[]") || tipo.equals("char[]") || tipo.equals("string[]") || tipo.equals("boolean[]")){

                this.almacenDatos.agregarInstancia(ctx.ID().getText(),new Object[(Integer) this.visit(ctx.expression())]);
            }
        }
        return super.visitVariableDeclAST(ctx);
    }

    @Override
    public Object visitStypeTypeAST(miParser.StypeTypeASTContext ctx) {
        return super.visitStypeTypeAST(ctx);
    }

    @Override
    public Object visitArrtypeTypeAST(miParser.ArrtypeTypeASTContext ctx) {
        return this.visit(ctx.arrayType());
    }

    @Override
    public Object visitIdTypeAST(miParser.IdTypeASTContext ctx) {
        return super.visitIdTypeAST(ctx);
    }

    @Override
    public Object visitStype(miParser.StypeContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitArrTypeAST(miParser.ArrTypeASTContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitAsssignAST(miParser.AsssignASTContext ctx) {
        if(ctx.PUNTO() == null) {
            //Guardo el nuevo valor que tendrá el array
            if(ctx.expression().getText().contains("new")){
                almacenDatos.setInstancia(ctx.ID(0).getText(), new Object[(Integer) this.visit(ctx.expression())]);
            }else {
                String nombre = ctx.ID(0).getText();
                Object valor = this.visit(ctx.expression());
                almacenDatos.setInstancia(nombre, valor);
            }
        }
        return null;
    }

    @Override
    public Object visitArrAssignAST(miParser.ArrAssignASTContext ctx) {
        String nombre = ctx.ID().getText();
        int index = (Integer) this.visit(ctx.expression(0));
        Object valor = this.visit(ctx.expression(1));

        Object[] value = (Object[]) almacenDatos.getInstancia(ctx.ID().getText()).valor;
        value[index] = valor;
        almacenDatos.setInstancia(nombre, value);
        return super.visitArrAssignAST(ctx);
    }

    @Override
    public Object visitExpressionAST(miParser.ExpressionASTContext ctx) {
        Object e1 = this.visit(ctx.simpleExpression(0));
        for (int i = 1; i < ctx.simpleExpression().size(); i++) {
            String op = ctx.ROPERATOR().get(i-1).getText();
            Object e2 = this.visit(ctx.simpleExpression(i));
            e1 = operarBoolean(e1, e2, op);
        }
        return e1;
    }

    //Funcion para hacer las operaciones booleanas
    private Object operarBoolean(Object e1, Object e2, String op){
        if (op.equals("<")){
            return (Integer) e1 < (Integer) e2;
        }else if(op.equals(">")) {
            return (Integer) e1 > (Integer) e2;
        }else if(op.equals(">=")){
            return (Integer) e1 >= (Integer) e2;
        }else if(op.equals("<=")){
            return (Integer) e1 <= (Integer) e2;
        }else if(op.equals("==")){
            return (e1).equals(e2);
        }else if(op.equals("!=")){
            return !(e1).equals(e2);
        }
        return 3;
    }
    /**
     * Funcion para hacer las operaciones básicas matemáticas
     * @param v1
     * @param v2
     * @param op
     * @return
     */
    private Object operar(Object v1, Object v2, String op){
        if(op.equals("/")){
            try{
                return (Integer) v1 / (Integer) v2;
            }catch (Exception e) {
                System.out.println("Error,no se permite la divición entre cero.");
            } ;

        }else if(op.equals("+")){
            try{
                return (Integer) v1 + (Integer) v2;
            }catch (Exception e) {

                try {
                    return v1 + (String) v2;
                }catch (Exception err){
                    System.out.println("Error, está tratando de sumar o concatenar un dato inválido.");
                }

            }

        }
        return switch (op) {
            case "-" -> (Integer) v1 - (Integer) v2;
            case "*" -> (Integer) v1 * (Integer) v2;
            case "||" -> (Boolean) v1 || (Boolean) v2;
            case "&&" -> (Boolean) v1 && (Boolean) v2;

            default -> null;
        };
    }

    @Override
    public Object visitSimpleExpressionAST(miParser.SimpleExpressionASTContext ctx) {
        Object v1 = this.visit(ctx.term(0));

        for (int i = 1; i < ctx.term().size(); i++) {
            String op = ctx.AOP().get(i-1).getText();
            Object v2 = this.visit(ctx.term(i));
            v1 = operar(v1, v2, op);
        }
        return v1;
    }

    @Override
    public Object visitTermAST(miParser.TermASTContext ctx) {
        Object v1 = this.visit(ctx.factor(0));

        for (int i = 1; i < ctx.factor().size(); i++) {
            String op = ctx.MOP().get(i-1).getText();
            Object v2 = this.visit(ctx.factor(i));
            v1 = operar(v1, v2, op);
        }
        return v1;
    }

    @Override
    public Object visitLiteralFactAST(miParser.LiteralFactASTContext ctx) {
        return this.visit(ctx.literal());//Retornando la visita al literal
    }

    @Override
    public Object visitPuntIdFactAST(miParser.PuntIdFactASTContext ctx) {
        return (almacenDatos.getInstancia(ctx.ID(0).getText())).valor;
    }

    @Override
    public Object visitFuntionCallFactAST(miParser.FuntionCallFactASTContext ctx) {

        return visit(ctx.funtionCall());

    }

    @Override
    public Object visitArrayLokupFactAST(miParser.ArrayLokupFactASTContext ctx) {
        return this.visit(ctx.arrayLookup());
    }

    @Override
    public Object visitLengthFactAST(miParser.LengthFactASTContext ctx) {
        return super.visitLengthFactAST(ctx);
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
        return super.visitAllocaExpreFactAST(ctx);
    }

    @Override
    public Object visitUnaryFactAST(miParser.UnaryFactASTContext ctx) {
        return super.visitUnaryFactAST(ctx);
    }

    @Override
    public Object visitUnaryAST(miParser.UnaryASTContext ctx) {
        return super.visitUnaryAST(ctx);
    }

    @Override
    public Object visitAllocationExprAST(miParser.AllocationExprASTContext ctx) {
        return super.visitAllocationExprAST(ctx);
    }

    @Override
    public Object visitArrAllocationExprAST(miParser.ArrAllocationExprASTContext ctx) {
        return this.visit(ctx.expression());
    }

    @Override
    public Object visitSubExprAST(miParser.SubExprASTContext ctx) {
        return this.visit(ctx.expression());
    }

    @Override
    public Object visitFunctionCallAST(miParser.FunctionCallASTContext ctx) {

        //Busco el metodo
        Almacen.Instancia i = almacenDatos.getInstancia(ctx.ID().getText());

        almacenDatos.openScope();
        if(ctx.actualParams() != null){
            //Lidiar con los parámetros - guardo los valores de los parametros en la pila
            this.visit(ctx.actualParams());

            //Uno los parametros con sus valores y los guardo en el almacen
            visit(((miParser.FunctionDeclASTContext)i.ctx).formalParams());
        }

        //Visitar el cuerpo del método
        visit(((miParser.FunctionDeclASTContext)i.ctx).block());


        almacenDatos.closeScope();


        return pilaExpresiones.pop() ;
    }

    @Override
    public Object visitActualParamsAST(miParser.ActualParamsASTContext ctx) {
        for (int i = ctx.expression().size()-1; i >= 0 ; i--) {
            pilaExpresiones.push(this.visit(ctx.expression(i)));
        }
        return null;
    }

    @Override
    public Object visitArrLookupAST(miParser.ArrLookupASTContext ctx) {

        Object[] value = (Object[]) almacenDatos.getInstancia(ctx.ID().getText()).valor;

        if (value == null){
            System.out.println("Error, el array en ese index aún no ha sido asignado!.");
        }

        try {
            assert value != null;
            return value[(Integer) this.visit(ctx.expression())];
        }catch (Exception e){
            System.out.println("Error, el index "+this.visit(ctx.expression())+" está fuera del rango del array.");
            return null;
        }
    }

    @Override
    public Object visitArrLengthAST(miParser.ArrLengthASTContext ctx) {
        try {
            Object[] v = (Object[]) almacenDatos.getInstancia(ctx.ID().getText()).valor;
            return v.length;
        }catch (Exception e){
            String v = (String) almacenDatos.getInstancia(ctx.ID().getText()).valor;
            return v.length();
        }
    }

    @Override
    public Object visitIntLiteralAST(miParser.IntLiteralASTContext ctx) {
        return Integer.parseInt(ctx.getText());
    }

    @Override
    public Object visitBoolLiteralAST(miParser.BoolLiteralASTContext ctx) {
        return this.visit(ctx.booleanLiteral());
    }

    @Override
    public Object visitStringLiteralAST(miParser.StringLiteralASTContext ctx) {
        return ctx.STRINGLITERAL().getText().substring(1, ctx.STRINGLITERAL().getText().length()-1);
    }

    @Override
    public Object visitCharListeralAST(miParser.CharListeralASTContext ctx) {
        return ctx.CHARLITERAL().getText().charAt(1);
    }

    @Override
    public Object visitBooleanLiteral(miParser.BooleanLiteralContext ctx) {
        if (ctx.getText().equals("true")){
            return true;
        }else {
            return false;
        }
    }
}
