package analysisContext;

import generated.miParser;
import generated.miParserBaseVisitor;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnalisisContextual extends miParserBaseVisitor {
    
    private TablaSimbolos tabla;
    private TablaSimbolClass tablaClass;
    private List<String> classes = new ArrayList<String>();
    List<String> types = new ArrayList<String>();
    private String arrTyp = null;
    public String errors;
    private String funcType = null;

    public static class ParamDec {
        String name;
        List<Ident.Params> param;

        public ParamDec(String name, List<Ident.Params> param) {
            this.name = name;
            this.param = param;
        }
    }

   public AnalisisContextual() {

       tabla = TablaSimbolos.getInstance();
       tablaClass = new TablaSimbolClass(); // Falta hacer este singletone

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
                errors +=(ctx.getText() + " no es un tipo de dato válido\n");
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

        for (int i = 0; i < ctx.statement().size(); i++) {
            this.visit(ctx.statement(i));
        }
        tabla.imprimir();
        tablaClass.imprimir();

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
    public Object visitArrAssignAST(miParser.ArrAssignASTContext ctx) {

        String simpleExpre1 = (String) this.visit(ctx.expression(0));
        String simpleExpre2 = (String) this.visit(ctx.expression(1));

        Ident id = null;
        id = tabla.buscar(ctx.ID().getText());

        if(id != null){
            String type = id.type.substring(0, id.type.length()-2);

            if(!id.initialited){
                errors += ("Error, el array <" + id.tok.getText() +"> No ha sido inicializado.\n");
            }else if(!simpleExpre1.equals("int")){
                errors += ("Error, para acceder a la posición del arreglo debe ingresar un dato tipo int en el index.\n");

            }else if (ctx.expression(1).getText().contains(".")){
                String[] parts = ctx.expression(1).getText().split("\\.");
                String part1 = parts[0]; // 123
                String part2 = parts[1]; // 654321

                Ident idExist = tabla.buscar(part1);

                if(idExist != null) {//Busco en la tabla local si existe
                    if (idExist.initialited){ //Viendo si la vara fue inicializada
                        String clase = idExist.type;
                        idExist = tablaClass.buscarClaseYVar(clase,part2); // busco la clase en la tabla de clases
                        if (idExist != null) {
                            if (clase.equals(idExist.className)) {//valida que la variable sea de esa clase al existir.
                                //Valido que exista esa varible
                                if (idExist.type.equals(type)) { //Valido que lo que se va a asignar sean iguales
                                    if (!idExist.initialited) {//Valido que este inicializada
                                        errors+= ("Error la variable que desea asignar no ha sido inicializado.\n");
                                    }
                                } else {
                                    errors += ("Error, el array es tipo <" + id.type + "> y está tratando de inicializarlo con otro tipo.\n");
                                }
                            } else {
                                errors += ("Error, el dato que quiere asignar no existe.\n");
                            }

                        }else {
                            errors += ("Error, no se encuentra <" + part2 + "> en < " + part1 + " >.\n");
                        }
                    } else{ errors += ("Error, <" + part1 + "> no a sido inicializada.\n");
                    }

                }else errors +=("Error, está asignando a <"+ctx.ID().getText()+"> un dato invalido.\n");

            } else if(!type.equals(simpleExpre2)){
                errors +=("El array es de tipo \""+id.type+", debe asignar datos del mismo tipo al array.\n");
            }
        }else {
            errors +=("Error, el array "+ctx.ID() + " aún no ha sido declarado!\n");
        }

        return this.visit(ctx.expression(0));

    }

    @Override
    public Object visitPrintStaAST(miParser.PrintStaASTContext ctx) {

        if(this.visit(ctx.printStatement()) == null){
            errors +=("Error, está intentando imprimir algo que no existe!\n");
        }

        return null;
    }

    @Override
    public Object visitIfStaAST(miParser.IfStaASTContext ctx) {
        tabla.openScope();
        this.visit(ctx.ifStatement());
        tabla.closeScope();
        return null;
    }

    @Override
    public Object visitWhileStaAST(miParser.WhileStaASTContext ctx) {

        tabla.openScope();
        this.visit(ctx.whileStatement());
        tabla.closeScope();

        System.out.println("\n imprimiendo desde el while.");
        tabla.imprimir();
        return null;
    }

    @Override
    public Object visitReturnStaAST(miParser.ReturnStaASTContext ctx) {
       this.visit(ctx.returnStatement());
        return null;
    }

    @Override
    public Object visitFunctionDeclStaAST(miParser.FunctionDeclStaASTContext ctx) {

        this.visit(ctx.funtionDeclaration());
        return null;
    }

    @Override
    public Object visitBlockStaAST(miParser.BlockStaASTContext ctx) {

        return this.visit(ctx.block());
    }

    @Override
    public Object visitBlockAST(miParser.BlockASTContext ctx) {
        for (miParser.StatementContext c: ctx.statement())
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

        Object id = null;

        if(ctx.type() != null){
            id = this.visit(ctx.type());
            funcType = (String) id;
            if (id.equals("int") || id.equals("boolean") || id.equals("char") || id.equals("string")){
                Ident t = tabla.buscar(ctx.ID().getText());
                if (t == null){
                    tabla.agregarParams(ctx.ID().getSymbol(), (String) id, ctx, (List<Ident.Params>) this.visit(ctx.formalParams()));
                    t = tabla.buscar(ctx.ID().getText());
                    t.initialited = true;
                }else{
                    errors += ("Error, ya existe una función con este nombre.\n");
                }

            }else{
                errors +=("Error, no se permite este tipo de dato en las funciones.\n");
            }
        }

        tabla.openScope();
        if(ctx.formalParams() != null){
            this.visit(ctx.formalParams());
        }
        this.visit(ctx.block());
        tabla.closeScope();

        //Validando que el método tenga return
        if(!ctx.block().getText().contains("return")){
            errors += "Error, el método debe de tener un retorno.\n";
        }
        funcType = null;

        return ctx;
    }

    @Override

    public Object visitFParamsAST(miParser.FParamsASTContext ctx) {
        List<Ident.Params> param = new ArrayList<>();
        for (int i = 0; i < ctx.formalParam().size(); i++) {

            param.add((Ident.Params) this.visit(ctx.formalParam(i)));
        }
        ctx.cantParams = ctx.formalParam().size();
        return param;
    }

    @Override
    public Object visitFParamAST(miParser.FParamASTContext ctx) {

        Object type = this.visit(ctx.type());

        if (type == null)
            errors += "Error, el tipo de dato del parámetro <"+ctx.ID().getText()+"> no es válido.\n";

        tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), null);
        return new Ident.Params( ctx.ID().getText(), (String) type);
    }

    @Override
    public Object visitWhileStmmtAST(miParser.WhileStmmtASTContext ctx) {

        Object expr = this.visit(ctx.expression());

        if(expr == null ){
            errors +=("Error, el operador en el \"while\" es invalido.\n");
        }

        this.visit(ctx.expression());
        this.visit(ctx.block());

        return null;
    }

    @Override
    public Object visitIfStmntAST(miParser.IfStmntASTContext ctx) {
        Object expr = this.visit(ctx.expression());

        if(expr == null ){
            errors +=("Error, el operador en el \"if\" es invalido.\n");
        }

        this.visit(ctx.block(0));

        if(ctx.block(1) !=  null){

            this.visit(ctx.block(1));
        }
        tabla.imprimir();


        return null;
    }

    @Override
    public Object visitReturnStmntAST(miParser.ReturnStmntASTContext ctx) {
        Object val = this.visit(ctx.expression());

        if(val == null){
            errors +=("Error, está retornando un dato invalido.\n");
        }else if(funcType != null && (!funcType.equals(val))){
            errors +=("Error, debe de retornar el mismo tipo de la función.\n");
            return null;
        }


        return null;
    }

    @Override
    public Object visitPrintStmntAST(miParser.PrintStmntASTContext ctx) {

        return this.visit(ctx.expression());
    }


    miParser.ClassVariableDeclASTContext ctxVar = null;
    @Override
    public Object visitClassDelcAST(miParser.ClassDelcASTContext ctx) {


        Ident existClass = tablaClass.buscarClase(ctx.ID().getText());
        if(existClass == null){
            classes.add(ctx.ID().getText());
        }
        Ident id;

        for (int i = 0; i < ctx.classVariableDeclaration().size(); i++) {
            this.visit(ctx.classVariableDeclaration(i));

            if (ctxVar.ASSIGN() != null){
                id = tablaClass.buscar(ctxVar.ID().getText());
                if(id != null){
                    if (!id.className.equals(ctx.ID().getText())){

                        id = tablaClass.buscarClaseYVar(ctx.ID().getText(),ctxVar.expression().getText());
                        if( this.visit(ctxVar.expression()) != null){
                            if (this.visit(ctxVar.expression()).equals(ctxVar.stype().getText())){
                                tablaClass.insertar(ctxVar.ID().getSymbol(), ctxVar.stype().getText(), ctxVar, ctx.ID().getText());
                                id = tablaClass.buscar(ctxVar.ID().getText());
                                id.initialited = true;
                            }else errors +=("Esta tratando de asignar a <"+ctxVar.expression().getText()+"> un tipo de dato distinto.\n");
                        }else if (id != null){
                            if (id.type.equals(ctxVar.stype().getText())){
                                tablaClass.insertar(ctxVar.ID().getSymbol(), ctxVar.stype().getText(), ctxVar, ctx.ID().getText());
                                id = tablaClass.buscar(ctxVar.ID().getText());
                                id.initialited = true;
                            }else errors +=("Esta tratando de asignar a <"+ctxVar.expression().getText()+"> un tipo de dato distinto.\n");

                        } else errors +=("Error, el dato que le quiere asignar a <"+ctxVar.ID()+"> en la clase <"+ctx.ID().getText() +">, no es un tipo de dato valido.\n");

                    }else{
                        errors +=("Error, ya existe en la clase <"+ctx.ID().getText() +"> una variable con el nombre <"+ ctxVar.ID().getText() + ">.\n");
                    }
                }else {
                    id = tablaClass.buscarClaseYVar(ctx.ID().getText(),ctxVar.expression().getText());
                    if( this.visit(ctxVar.expression()) != null){
                        if (this.visit(ctxVar.expression()).equals(ctxVar.stype().getText())){
                            tablaClass.insertar(ctxVar.ID().getSymbol(), ctxVar.stype().getText(), ctxVar, ctx.ID().getText());
                            id = tablaClass.buscar(ctxVar.ID().getText());
                            id.initialited = true;
                        }else errors +=("Esta tratando de asignar a <"+ctxVar.expression().getText()+"> un tipo de dato distinto.\n");
                    }else if (id != null){
                        if (id.type.equals(ctxVar.stype().getText())){
                            tablaClass.insertar(ctxVar.ID().getSymbol(), ctxVar.stype().getText(), ctxVar, ctx.ID().getText());
                            id = tablaClass.buscar(ctxVar.ID().getText());
                            id.initialited = true;
                        }else errors +=("Esta tratando de asignar a <"+ctxVar.expression().getText()+"> un tipo de dato distinto.\n");

                    } else errors +=("Error, el dato que le quiere asignar a <"+ctxVar.ID()+"> en la clase <"+ctx.ID().getText() +">, no es un tipo de dato valido.\n");

                }

            }else {
                id = tablaClass.buscar(ctxVar.ID().getText());
                if(id != null){
                    if (!id.className.equals(ctx.ID().getText())){
                        tablaClass.insertar(ctxVar.ID().getSymbol(), ctxVar.stype().getText(), ctxVar, ctx.ID().getText());
                    }else{
                        errors +=("Error, ya existe en la clase <"+ctx.ID().getText() +"> una variable con el nombre <"+ ctxVar.ID().getText() + ">.\n");
                    }
                }else {
                    tablaClass.insertar(ctxVar.ID().getSymbol(), ctxVar.stype().getText(), ctxVar, ctx.ID().getText());
                }
            }
        }
        tablaClass.imprimir();
        return null;
    }

    @Override
    public Object visitClassVariableDeclAST(miParser.ClassVariableDeclASTContext ctx) {

        ctxVar = ctx;
        return null;
    }

    @Override
    public Object visitVariableDeclAST(miParser.VariableDeclASTContext ctx) {

        Ident idExist = tabla.buscar(ctx.ID().getText());
        boolean classExist = false;

        //Validando si es un tipo de clase
        for (String c: classes) {
            if (c.equals(ctx.type().getText()))
                classExist = true;
        }

        //VALIDANDO SOLO LA DECLARACION
       // if (ctx.ASSIGN() == null && idExist == null){ ASI ES A COMO ESTABA ANTES, HACIENDO PRUEBAS
        if (ctx.ASSIGN() == null){

            if(ctx.type().getText().equals("boolean[]") || ctx.type().getText().equals("char[]") || ctx.type().getText().equals("int[]") || ctx.type().getText().equals("string[]")){
                Ident id = tabla.buscar(ctx.ID().getText());
                if (id != null)
                    errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                else {
                    tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx); }

            }else if (ctx.type().getText().equals("boolean") || ctx.type().getText().equals("char") || ctx.type().getText().equals("int") || ctx.type().getText().equals("string")){
                Ident id = tabla.buscar(ctx.ID().getText());
                if (id != null)
                    errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                else {
                    tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx); }

            }else if(classExist){
                Ident id = tabla.buscar(ctx.ID().getText());
                if (id != null)
                    errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                else {
                    tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx); }

            }else errors +=("Error, el tipo de dato <"+ctx.type().getText()+"> no corresponde a ningún tipo de dato.\n");

        //VALIDANDO DECLARACION Y ASIGNACION
            //}else if(ctx.ASSIGN() != null && idExist == null){ ASI ES A COMO ESTABA ANTES
        }else if(ctx.ASSIGN() != null){
            //VALIDANDO ARRAYS
            if(ctx.type().getText().equals("boolean[]") || ctx.type().getText().equals("char[]") || ctx.type().getText().equals("int[]") || ctx.type().getText().equals("string[]")){
                if (this.visit(ctx.expression()) != null){
                    try {
                        if (ctx.expression().getText().substring(0, 3).equals("new")) {
                            if (ctx.type().getText().equals((ctx.expression().getText().substring(3, ctx.type().getText().length() +1 )) + "[]" )) {
                                if(this.visit(ctx.expression()).equals("int")) {
                                    Ident id = tabla.buscar(ctx.ID().getText());
                                    if (id != null)
                                        errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                                    else {
                                        tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                                        idExist = tabla.buscar(ctx.ID().getText());
                                        idExist.initialited = true;}
                                } else System.out.println();
                            }else {
                                errors +=("Error, el array es tipo <"+ctx.type().getText()+"> y está tratando de inicializarlo con otro tipo.\n");
                            }

                        }else if(ctx.type().getText().equals(this.visit(ctx.expression()))){
                            idExist = tabla.buscar(ctx.expression().getText());
                            if(idExist.initialited){
                                Ident id = tabla.buscar(ctx.ID().getText());
                                if (id != null)
                                    errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                                else {
                                    tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                                    idExist = tabla.buscar(ctx.ID().getText());
                                    idExist.initialited = true;}
                            }else errors +=("Error, el arreglo <"+ctx.expression().getText()+"> aún no ha sido inicializado.\n");

                        }else errors +=("Error, el array es tipo <"+ctx.type().getText()+"> y está tratando de asignar otro tipo.\n");

                    } catch (Exception e) {
                        if(ctx.type().getText().equals(this.visit(ctx.expression()))){
                            idExist = tabla.buscar(ctx.expression().getText());
                            if(idExist.initialited){

                                Ident id = tabla.buscar(ctx.ID().getText());
                                if (id != null)
                                    errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                                else {
                                    tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                                    idExist = tabla.buscar(ctx.ID().getText());
                                    idExist.initialited = true;}
                            }else errors +=("Error, el arreglo <"+ctx.expression().getText()+"> aún no ha sido inicializado.\n");

                        }else errors +=("Error, el array es tipo <"+ctx.type().getText()+"> y está tratando de inicializarlo con otro tipo.\n");
                    }
                }else if (ctx.expression().getText().contains(".")) {
                    if(arrTyp != null){
                        Ident id = tabla.buscar(ctx.ID().getText());
                        if (id != null)
                            errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                        else {
                            tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                            idExist = tabla.buscar(ctx.ID().getText());
                            idExist.initialited = true;}
                    }
                }

                else errors +=("Error, el dato que trata de asignar a <"+ctx.ID().getText()+"> aún no ha sido declarado.\n");

            //VALIDANDO VARIABLES
            }else if ((ctx.type().getText().equals("boolean") || ctx.type().getText().equals("char") || ctx.type().getText().equals("int") || ctx.type().getText().equals("string"))){
                if (ctx.type().getText().equals(this.visit(ctx.expression()))){
                    Ident id = tabla.buscar(ctx.expression().getText());
                        if(id != null) {
                            if (!id.initialited) {
                                errors +=("Error, la variable <" + ctx.ID().getText()+ "> no puede ser asignada porque <"+id.tok.getText()+"> no ha sido inicializada.\n");
                            }
                        }else {
                            id = tabla.buscar(ctx.ID().getText());
                            if (id != null)
                                errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                            else {
                                tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                                idExist = tabla.buscar(ctx.ID().getText());
                                idExist.initialited = true;}
                        }


                //------------------------------------------------------------------------------------
                }else if (ctx.expression().getText().contains(".")){
                    String[] parts = ctx.expression().getText().split("\\.");
                    String part1 = parts[0]; // 123
                    String part2 = parts[1]; // 654321

                    idExist = tabla.buscar(part1);
                    if(idExist != null) {//Busco en la tabla local si existe
                        if (idExist.initialited){ //Viendo si la vara fue inicializada
                            String clase = idExist.type;
                            idExist = tablaClass.buscarClaseYVar(clase,part2); // busco la clase en la tabla de clases
                            if (idExist != null) {
                                if (clase.equals(idExist.className)) {//valida que la variable sea de esa clase al existir.
                                    //Valido que exista esa varible
                                    if (idExist.type.equals(ctx.type().getText())) { //Valido que lo que se va a asignar sean iguales
                                        if (idExist.initialited) {//Valido que este inicializada

                                            Ident id = tabla.buscar(ctx.ID().getText());
                                            if (id != null)
                                                errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                                            else {
                                                tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                                                idExist = tabla.buscar(ctx.ID().getText());
                                                idExist.initialited = true;}

                                        } else
                                            errors +=("Error, <" + part1 + "> no a sido inicializada.\n");
                                    } else
                                        errors +=("Error, la variable es tipo <" + ctx.type().getText() + "> y está tratando de inicializarlo con otro tipo.\n");
                                } else errors +=("Error, el dato que quiere asignar no existe.\n");
                            }else errors +=("Error, no se encuentra <"+part2+"> en < "+part1+" >.\n");
                        }else errors +=("Error, <"+part1+"> no a sido inicializada.\n");
                    }else errors +=("Error, está asignando a <"+ctx.ID().getText()+"> un dato invalido.\n");

                }else errors +=("Error, el dato que se quiere asignar a <"+ctx.ID().getText()+"> no es un dato valido.\n");
                //----------------------------------------------------------------------------------------

            }else if(classExist){
                if (ctx.expression().getText().substring(0, 3).equals("new")) {
                    if (ctx.type().getText().equals((ctx.expression().getText().substring(3, ctx.expression().getText().length() -2 ))  )) {

                        Ident id = tabla.buscar(ctx.ID().getText());
                        if (id != null)
                            errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                        else {
                            tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                            idExist = tabla.buscar(ctx.ID().getText());
                            idExist.initialited = true;}
                    }else {
                        errors +=("Error, la variable es tipo <"+ctx.type().getText()+"> y está tratando de usar un dato invalido sin inicializar.\n");
                    }

                }else errors +=("Error, la variable es tipo <"+ctx.type().getText()+"> y está tratando de usar un dato invalido sin inicializar.\n");


            }else errors +=("Error, el tipo de dato <"+ctx.type().getText()+"> no corresponde a ningún tipo de dato.\n");

        }else if (ctx.type().getText().equals("boolean") || ctx.type().getText().equals("char") || ctx.type().getText().equals("int") || ctx.type().getText().equals("string")) {
            System.out.println(ctx.expression().getText() + " probando");
            if (ctx.type().getText().equals(this.visit(ctx.expression()))){
                Ident id = tabla.buscar(ctx.expression().getText());
                if(id != null) {
                    if (!id.initialited) {
                        errors +=("Error, la variable <" + ctx.ID().getText()+ "> no puede ser asignada porque <"+id.tok.getText()+"> no ha sido inicializada.\n");
                    }
                }else {
                    id = tabla.buscar(ctx.ID().getText());
                    if (id != null)
                        errors +=("Error, la variable <"+id.tok.getText()+"> ya existe.\n");
                    else{
                        tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                        idExist = tabla.buscar(ctx.ID().getText());
                        idExist.initialited = true;}
                }

            }

        }else {
            errors +=("Error, la variable <"+ctx.ID().getText()+"> ya está siendo utilizada por otro tipo.\n");
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

        if(ctx.PUNTO() != null && ctx.ID(1) != null){
            String part1 = ctx.ID(0).getText();
            String part2 = ctx.ID(1).getText();

            Ident idExist = tabla.buscar(part1);
            Ident id = null;

            if(idExist != null) {//Busco en la tabla local si existe
                if (idExist.initialited){ //Viendo si la vara fue inicializada
                    String clase = idExist.type;
                    idExist = tablaClass.buscarClaseYVar(clase,part2); // busco la clase en la tabla de clases
                    if (idExist != null) {
                        if (clase.equals(idExist.className)) {//valida que la variable sea de esa clase al existir.

                            if(this.visit(ctx.expression()) != null){
                                id = tabla.buscar(ctx.expression().getText());
                                if(idExist.type.equals(this.visit(ctx.expression()))){
                                    if(id != null) {
                                        if (!id.initialited) {
                                            errors +=("Error, la variable <" + id.tok.getText() + "> no puede ser asignada porque no se le ha asignado ningún dato.\n");
                                        }
                                    }else {
                                        idExist = tablaClass.buscarClaseYVar(idExist.className,part2);
                                        idExist.initialited = true;
                                    }
                                }else {
                                    if (!idExist.type.equals(this.visit(ctx.expression()))){
                                        errors +=("Error, no se le puede asignar a un tipo <"+idExist.type +"> un tipo <"+this.visit(ctx.expression())+">.\n");
                                    }
                                }
                            }else{
                                if (ctx.expression().getText().contains(".")) {
                                    String[] parts = ctx.expression().getText().split("\\.");
                                    String point1 = parts[0]; // 123
                                    String point2 = parts[1]; // 654321
                                    Ident expre1 = idExist;

                                    idExist = tabla.buscar(point1);
                                    if(idExist != null) {//Busco en la tabla local si existe
                                        if (idExist.initialited){ //Viendo si la vara fue inicializada
                                            clase = idExist.type;
                                            idExist = tablaClass.buscarClaseYVar(clase,point2); // busco la clase en la tabla de clases
                                            if (idExist != null) {
                                                if (clase.equals(idExist.className)) {//valida que la variable sea de esa clase al existir.
                                                    //Valido que exista esa varible
                                                    if (idExist.type.equals(expre1.type)) { //Valido que lo que se va a asignar sean iguales
                                                        if (idExist.initialited) {//Valido que este inicializada
                                                            idExist = tablaClass.buscarClaseYVar(expre1.className,part2);
                                                            idExist.initialited = true;
                                                        } else
                                                            errors +=("Error, <" + idExist.tok.getText() + "> no a sido inicializada.\n");
                                                    } else
                                                        errors +=("Error, la variable es tipo <" + expre1.type + "> y está tratando de inicializarlo con otro tipo.\n");
                                                } else errors +=("Error, el dato que quiere asignar no existe.\n");
                                            }else errors +=("Error, no se encuentra <"+point2+"> en < "+point1+" >.\n");
                                        }else errors +=("Error, <"+point1+"> no a sido inicializada.");
                                    }else errors +=("Error, está asignando a <"+expre1.tok.getText()+"> un dato invalido.\n");

                                }else errors +=("Error, a <"+part1+"."+part2+"> no se puede asignar un dato que no es válido.\n");
                            }
                        } else errors +=("Error, el dato que quiere asignar no existe.\n");
                    }else errors +=("Error, no se encuentra <"+part2+"> en < "+part1+" >.\n");
                }else errors +=("Error, <"+part1+"> no a sido inicializada.\n");
            }else errors +=("Error, está asignando a <"+ctx.ID(0).getText()+"> un dato invalido.\n");
            return null;
        }

        Ident id = tabla.buscar(ctx.ID(0).getText());

        if (id != null && ctx.PUNTO() == null) {
            Object exprType = this.visit(ctx.expression() );
            Ident idExp2 = tabla.buscar(ctx.expression().getText());


            if (idExp2 != null) {
                if (idExp2.type.substring(idExp2.type.length() - 2, idExp2.type.length()).equals("[]"))
                    if (!idExp2.initialited)
                        errors +=("Error, el array \"" + ctx.expression().getText() + "\" no ha sido inicializado.\n");
                    else if (!id.initialited)
                        errors +=("Error, el array \"" + ctx.ID(0).getText() + "\" no ha sido inicializado.\n");

                    else if (!id.type.equals(idExp2.type))
                        errors +=("Error, el array \"" + ctx.expression().getText() + "\" no es del mismo tipo.\n");

            } else if (id.type.substring(id.type.length() - 2, id.type.length()).equals("[]")) {

                try {
                    String test = (ctx.expression().getText().substring(0, 3));
                    if (test.equals("new")) {
                        String test2 = (ctx.expression().getText().substring(3, 1 + id.type.length()));
                        if (!id.type.equals((test2 + "[]"))) {
                            errors +=("Error, el array es tipo \"" + id.type + "\" y está tratando de inicializar con un tipo de dato diferente\n");
                        } else if (exprType.equals("int")) {
                            //errors +=("Agregando la inicializacion, en el asssingAST a: " + ctx.ID(0).getText()+".\n");
                            id.initialited = true;
                        }
                    } else if (!id.initialited)
                        errors +=("El arreglo \" " + ctx.ID(0).getText() + " \" aún no ha sido inicializado.\n");
                    else if(!exprType.equals((id.type.substring(0,id.type.length()-2)))){
                        errors +=("Error, al arreglo no se le permite asignar de esta manera.\n");
                    }
                } catch (Exception e) {
                    errors +=("Error, no es la manera correcta para asignar a un array.\n");
                }


            } else if (exprType == null) {
                idExp2 = tabla.buscar(ctx.expression().getText());
                if (ctx.expression().getText().contains(".")) {
                    String[] parts = ctx.expression().getText().split("\\.");
                    String point1 = parts[0]; // 123
                    String point2 = parts[1]; // 654321
                    Ident idExist = null;
                    idExist = tabla.buscar(point1);
                    if(idExist != null) {//Busco en la tabla local si existe
                        if (idExist.initialited){ //Viendo si la vara fue inicializada
                            String clase = idExist.type;
                            idExist = tablaClass.buscarClaseYVar(clase,point2); // busco la clase en la tabla de clases
                            if (idExist != null) {
                                if (clase.equals(idExist.className)) {//valida que la variable sea de esa clase al existir.
                                    //Valido que exista esa varible
                                    if (idExist.type.equals(id.type)) { //Valido que lo que se va a asignar sean iguales
                                        if (idExist.initialited) {//Valido que este inicializada
                                            idExist = tablaClass.buscarClaseYVar(clase,point2);
                                            idExist.initialited = true;
                                        } else
                                            errors +=("Error, <" + idExist.tok.getText() + "> no a sido inicializada.\n");
                                    } else
                                        errors +=("Error, la variable es tipo <" + id.type + "> y está tratando de inicializarlo con otro tipo.\n");
                                } else errors +=("Error, el dato que quiere asignar no existe.\n");
                            }else errors +=("Error, no se encuentra <"+point2+"> en < "+point1+" >.\n");
                        }else errors +=("Error, <"+point1+"> no a sido inicializada.");
                    }else errors +=("Error, está asignando a <"+id.tok.getText()+"> un dato invalido.\n");

                }else if (idExp2 == null) {
                    errors +=(" <" + ctx.expression().getText() + ">, no corresponde a un tipo de dato o no ha sido declaradooo!\n");
                } else if (!idExp2.type.equals(id.type)) {
                    errors +=("La asignación: <" + ctx.expression().getText() + ">, no corresponde al mismo tipo de dato!\n");
                }
            } else if (ctx.expression().getText().contains(id.type)){
                try {
                    String test = (ctx.expression().getText().substring(0, 3));
                    if (test.equals("new")) {
                        String test2 = (ctx.expression().getText().substring(3, ctx.expression().getText().length() - 2 ));
                        if (!id.type.equals((test2))) {
                            errors +=("Error, la variable es tipo \"" + id.type + "\" y está tratando de inicializar con un tipo de dato diferente\n");
                        } else{
                            id.initialited = true;
                        }
                    } else if (!id.initialited)
                        errors +=("El arreglo \" " + ctx.ID(0).getText() + " \" aún no ha sido inicializado.\n");
                } catch (Exception e) {
                    errors +=("Error, no es la manera correcta para asignar a la variable.\n");
                }
            } else if (!id.type.equals(exprType)) {
                errors +=("Los tipos son imcompatibles para la asignación entre: <" + id.type + "> y <" + exprType + ">.\n");
            }

        } else if (id == null) {
            errors +=("Error, \"" + ctx.ID(0).getText() + "\" no ha sido declarado.\n");
        }

        return null;
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
                id2 = tabla.buscar(ctx.simpleExpression(i).getText());
                id1 = tabla.buscar(ctx.simpleExpression(i - 1).getText());


                if (operador.equals("<") || operador.equals(">") || operador.equals("<=") || operador.equals(">=")) {

                    if (exprType2 != null && exprType != null) {
                        if (exprType.equals("boolean") || exprType.equals("char") || exprType.equals("string") || exprType2.equals("string") || (exprType2.equals("boolean") || exprType2.equals("char"))) {
                            errors +=("Error, <" + exprType + "> y <" + exprType2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + operador + "\".\n");
                        } else if (!exprType2.equals(exprType)) {
                            errors +=("Error, <" + exprType + "> y <" + exprType2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + operador + "\".\n");
                        } else return "boolean";

                    } else if (exprType2 == null && exprType != null) {
                        if (!exprType.equals(id2.type)) {
                            errors +=("Error, <" + exprType + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".\n");
                        } else if (exprType.equals("boolean") || exprType.equals("char") || exprType.equals("string")) {
                            errors +=("Error, <" + exprType + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + operador + "\".\n");
                        } else return "boolean";

                    } else if (exprType2 != null) {
                        if (!exprType2.equals(id1.type)) {
                            errors +=("Error, <" + id1.type + "> y <" + exprType2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".\n");
                        } else if (exprType2.equals("boolean") || exprType2.equals("char") || exprType2.equals("string")) {
                            errors +=("Error, <" + exprType2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + operador + "\".\n");
                        } else return "boolean";

                    } else {
                        if (id1.type.equals("char") || id1.type.equals("boolean") || id1.type.equals("string")) {
                            errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".\n");
                        } else if (!id1.type.equals(id2.type)) {
                            errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".\n");
                        } else return "boolean";
                    }

                } else if (operador.equals("!=") || operador.equals("==")) {
                    if (exprType2 != null && exprType != null) {
                        if (!exprType2.equals(exprType)) {
                            errors +=("Error,<" + exprType2 + "> y <" + exprType + "> son tipos de datos incompatibles, sólo se permite datos de igual tipo.\n");
                        } else return "boolean";

                    } else if (exprType2 == null && exprType != null) {
                            if (!exprType.equals(id2.type)) {
                                errors +=("Error, <" + exprType + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".\n");
                        } else return "boolean";

                    } else if (exprType2 != null) {
                             if (!exprType2.equals(id1.type)) {
                                 errors +=("Error, <" + id1.type + "> y <" + exprType2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".\n");
                        } else return "boolean";

                    } else {
                         if (!id1.type.equals(id2.type)) {
                             errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".\n");
                        } else return "boolean";
                    }

                }

            } catch (Exception e) {
                errors +=("Error, está operando con datos que no existen.\n");
            }
        }

        //return this.visit(ctx.simpleExpression(0));
        //System.out.println(" operator" + ctx.simpleExpression(0).);
        id1 = tabla.buscar(ctx.simpleExpression(0).getText());
        if (id1 != null && exprType == null){
            return id1.type;
        }
        if (operador == null) return exprType;
        return null;
    }

    @Override
    public Object visitSimpleExpressionAST(miParser.SimpleExpressionASTContext ctx) {

        String simpleExpre1 = null;
        String simpleExpre2 = null;
        String aOperador = null;
        Ident id1 = null;
        Ident id2 = null;

        simpleExpre1 = (String) this.visit(ctx.term(0));

        for (int i = 1; i < ctx.term().size(); i++) {

            aOperador = ctx.AOP().get(i-1).getText();
            id1 = tabla.buscar(ctx.term(i - 1).getText());
            id2 = tabla.buscar(ctx.term(i).getText());


            try {
                simpleExpre2 = (String) this.visit(ctx.term(i));

                switch (aOperador) {
                    case "||" -> {
                        if (simpleExpre2 != null && simpleExpre1 != null) {
                            if (!simpleExpre1.equals("boolean") || !simpleExpre2.equals("boolean")) {
                                errors +=("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (boolean) en el operador  \"" + aOperador + "\".\n");
                            } else return "boolean";

                        } else if (simpleExpre2 == null && simpleExpre1 != null) {
                            if (!simpleExpre1.equals(id2.type)) {
                                errors +=("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (boolean) en el operador  \"" + aOperador + "\".\n");
                            } else if (!simpleExpre1.equals("boolean")) {
                                errors +=("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (boolean) en el operador  \"" + aOperador + "\".\n");
                            } else return "boolean";

                        } else if (simpleExpre2 != null) {
                             if (!simpleExpre2.equals(id1.type)) {
                                 errors +=("Error, <" + id1.type + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (boolean) en el operador  \"" + aOperador + "\".\n");
                            } else if (!simpleExpre2.equals("boolean")) {
                                 errors +=("Error, <" + simpleExpre2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (boolean) en el operador  \"" + aOperador + "\".\n");
                            } else return "boolean";

                        } else {
                            if (!id1.type.equals("boolean")) {
                                errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (boolean) en el operador  \"" + aOperador + "\".\n");
                            } else if (!id1.type.equals(id2.type)) {
                                errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (boolean) en el operador  \"" + aOperador + "\".\n");
                            } else return "boolean";
                        }
                    }
                    case "+" -> {
                        if (simpleExpre2 != null && simpleExpre1 != null) {
                            if (simpleExpre1.equals("boolean") || simpleExpre1.equals("char") || (simpleExpre2.equals("boolean") || simpleExpre2.equals("char"))) {
                                errors +=("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (int o string) en el operador  \"" + aOperador + "\".\n");
                            } else if (!simpleExpre2.equals(simpleExpre1)) {
                                errors +=("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (int o string) en el operador  \"" + aOperador + "\".\n");
                            } else if (simpleExpre1.equals("int")) return "int";
                            else return "string";

                        } else if (simpleExpre2 == null && simpleExpre1 != null) {
                             if (!simpleExpre1.equals(id2.type)) {
                                 errors +=("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".\n");
                            } else if (simpleExpre1.equals("boolean") || simpleExpre1.equals("char")) {
                                 errors +=("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o string) en el operador  \"" + aOperador + "\".\n");
                            } else if (simpleExpre1.equals("int")) return "int";
                            else return "string";

                        } else if (simpleExpre2 != null) {
                             if (!simpleExpre2.equals(id1.type)) {
                                 errors +=("Error, <" + id1.type + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".\n");
                            } else if (simpleExpre2.equals("boolean") || simpleExpre2.equals("char")) {
                                 errors +=("Error, <" + simpleExpre2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o string) en el operador  \"" + aOperador + "\".\n");
                            } else if (simpleExpre2.equals("int")) return "int";
                            else return "string";

                        } else {
                            if (id1.type.equals("char") || id1.type.equals("boolean")) {
                                errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".\n");
                            } else if (!id1.type.equals(id2.type)) {
                                errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".\n");
                            } else if (id1.type.equals("int")) return "int";
                            else return "string";
                        }
                    }
                    case "-" -> {
                        if (simpleExpre2 != null && simpleExpre1 != null) {
                            if (simpleExpre1.equals("boolean") || simpleExpre1.equals("char") || simpleExpre1.equals("string") || simpleExpre2.equals("string") || (simpleExpre2.equals("boolean") || simpleExpre2.equals("char"))) {
                                errors +=("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + aOperador + "\".\n");
                            } else if (!simpleExpre2.equals(simpleExpre1)) {
                                errors +=("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + aOperador + "\".\n");
                            } else return "int";

                        } else if (simpleExpre2 == null && simpleExpre1 != null) {
                            if (!simpleExpre1.equals(id2.type)) {
                                errors +=("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".\n");
                            } else if (simpleExpre1.equals("boolean") || simpleExpre1.equals("char") || simpleExpre1.equals("string")) {
                                errors +=("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + aOperador + "\".\n");
                            } else return "int";

                        } else if (simpleExpre2 != null) {
                            if (!simpleExpre2.equals(id1.type)) {
                                errors +=("Error, <" + id1.type + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".\n");
                            } else if (simpleExpre2.equals("boolean") || simpleExpre2.equals("char") || simpleExpre2.equals("string")) {
                                errors +=("Error, <" + simpleExpre2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + aOperador + "\".\n");
                            } else return "int";

                        } else {
                            if (id1.type.equals("char") || id1.type.equals("boolean") || id1.type.equals("string")) {
                                errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".\n");
                            } else if (!id1.type.equals(id2.type)) {
                                errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".\n");
                            } else return "int";
                        }
                    }
                }
            }catch (Exception e){
                errors +=("Error operando con datos no validos!\n");
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
            id1 = tabla.buscar(ctx.factor(i-1).getText());
            id2 = tabla.buscar(ctx.factor(i).getText());

            try {
                factor2 = (String) this.visit(ctx.factor(i));

                switch (mOperador) {
                    case "*":

                    case "/":
                        if (factor2 != null && factor1 != null) {
                            if (factor1.equals("boolean") || factor1.equals("char") || factor1.equals("string") || factor2.equals("string") || (factor2.equals("boolean") || factor2.equals("char"))) {
                                errors +=("Error, <" + factor1 + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + mOperador + "\".\n");
                            } else if (!factor2.equals(factor1)) {
                                errors +=("Error, <" + factor1 + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + mOperador + "\".\n");
                            } else return "int";

                        } else if (factor2 == null && factor1 != null) {
                            if (!factor1.equals(id2.type)) {
                                errors +=("Error, <" + factor1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".\n");
                            } else if (factor1.equals("boolean") || factor1.equals("char") || factor1.equals("string")) {
                                errors +=("Error, <" + factor1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + mOperador + "\".\n");
                            } else return "int";

                        } else if (factor2 != null) {
                            if (!factor2.equals(id1.type)) {
                                errors +=("Error, <" + id1.type + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".\n");
                            } else if (factor2.equals("boolean") || factor2.equals("char") || factor2.equals("string")) {
                                errors +=("Error, <" + factor2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + mOperador + "\".\n");
                            } else return "int";

                        } else {
                            if (id1.type.equals("char") || id1.type.equals("boolean") || id1.type.equals("string")) {
                                errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".\n");
                            } else if (!id1.type.equals(id2.type)) {
                                errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".\n");
                            } else return "int";
                        }

                        break;

                    case "&&":
                        if (factor2 != null && factor1 != null) {
                            if (factor1.equals("string") || factor1.equals("char") || (factor2.equals("string") || factor2.equals("char")  || factor2.equals("int"))) {
                                errors +=("Error, <" + factor1 + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos (boolean) en el operador  \"" + mOperador + "\".\n");
                            } else if (!factor2.equals(factor1)) {
                                errors +=("Error, <" + factor1 + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos (boolean) en el operador  \"" + mOperador + "\".\n");
                            } else return "boolean";

                        } else if (factor2 == null && factor1 != null) {
                            if (!factor1.equals(id2.type)) {
                                errors +=("Error, <" + factor1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (boolean) en el operador  \"" + mOperador + "\".\n");
                            } else if (!factor1.equals("boolean")) {
                                errors +=("Error, <" + factor1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (boolean) en el operador  \"" + mOperador + "\".\n");
                            } else return "boolean";

                        } else if (factor2 != null) {
                            if (!factor2.equals(id1.type)) {
                                errors +=("Error, <" + id1.type + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (boolean) en el operador  \"" + mOperador + "\".\n");
                            } else if (!factor2.equals("boolean")) {
                                errors +=("Error, <" + factor2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (boolean) en el operador  \"" + mOperador + "\".\n");
                            } else return "boolean";

                        } else {
                            if (!id1.type.equals("boolean")) {
                                errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (boolean) en el operador  \"" + mOperador + "\".\n");
                            } else if (!id1.type.equals(id2.type)) {
                                errors +=("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (boolean) en el operador  \"" + mOperador + "\".\n");
                            } else return "boolean";
                        }
                        break;
                }

            }catch (Exception e){

                errors +=("Error, está operando con datos no validos.\n");
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
        return null;
    }

    @Override
    public Object visitFuntionCallFactAST(miParser.FuntionCallFactASTContext ctx) {

        return this.visit(ctx.funtionCall());
    }

    @Override
    public Object visitSubExpressionFactAST(miParser.SubExpressionFactASTContext ctx) {

        return this.visit(ctx.subExpression());
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

        return unary1;
    }

    @Override
    public Object visitAllocationExprAST(miParser.AllocationExprASTContext ctx) {
        return ctx.ID().getText();
    }

    @Override
    public Object visitArrAllocationExprAST(miParser.ArrAllocationExprASTContext ctx) {
        Object attr = this.visit(ctx.expression());
        Ident id = null;
        if(attr != null){
            if (!((String) attr).equals("int")){
                errors +=("Error, la inicialización de los arreglos deben de ser siempre de tipo int y se esta usando  tipo \""+attr+"\"\n");
            }
        }else if (ctx.expression().getText().contains(".")) {
            Ident idExp2 = tabla.buscar(ctx.expression().getText());
            String[] parts = ctx.expression().getText().split("\\.");
            String point1 = parts[0]; // 123
            String point2 = parts[1]; // 654321
            Ident idExist = null;
            idExist = tabla.buscar(point1);
            if (idExist != null) {//Busco en la tabla local si existe
                if (idExist.initialited) { //Viendo si la vara fue inicializada
                    String clase = idExist.type;
                    idExist = tablaClass.buscarClaseYVar(clase, point2); // busco la clase en la tabla de clases
                    if (idExist != null) {
                        if (clase.equals(idExist.className)) {//valida que la variable sea de esa clase al existir.
                            //Valido que exista esa varible
                            if (idExist.type.equals("int")) { //Valido que lo que se va a asignar sean iguales
                                if (!idExist.initialited) {//Valido que este inicializada
                                    errors +=("Error, <" + idExist.tok.getText() + "> no a sido inicializada.\n");
                                    arrTyp = null;
                                }else {
                                    arrTyp = ctx.expression().getText();
                                }

                            } else
                                errors +=("Error, esta tratando de inicializar el array con un tipo <"+ idExist.type +">.\n");
                        } else errors +=("Error, el dato que quiere asignar al tamaño del array no existe.\n");
                    } else errors +=("Error, no se encuentra <" + point2 + "> en < " + point1 + " >.\n");
                } else errors +=("Error, <" + point1 + "> no a sido inicializado.\n");
            } else errors +=("Error, está asignando al array de tipo <" + ctx.stype().getText() + "> un dato invalido.\n");

        } else{
            id = tabla.buscar(ctx.expression().getText());
            if(id != null) {
                if (!id.type.equals("int")) {
                    errors +=("El index ingresado no es un dato valido, debe ser tipo int.\n");
                }else {
                    return id.type;
                }
            }else {
                errors +=("Debe ingresar un index valido para el arreglo.\n");
            }
        }

        return this.visit(ctx.expression());
    }

    @Override
    public Object visitSubExprAST(miParser.SubExprASTContext ctx) {
        return this.visit(ctx.expression());
    }

    @Override
    public Object visitFunctionCallAST(miParser.FunctionCallASTContext ctx) {

        Ident id = tabla.buscar(ctx.ID().getText());
        if (id == null){
            errors +=("Error, <"+ctx.ID().getText()+"> No no ha sido declarado!!!\n");
        }else{
            Object test = ctx.actualParams();
            if (test != null){
                this.visit(ctx.actualParams());
                Object test2 = (( ((miParser.FunctionDeclASTContext) id.declCtx).formalParams()));
                if (test2 != null){
                    if (ctx.actualParams().cantParams != ( ((miParser.FunctionDeclASTContext) id.declCtx).formalParams()).cantParams){
                        errors +=("Error en la llamada del método, cantidad de parámetros diferente a la declaración\n");
                        return id.type;
                    }
                }else{
                    if (ctx.actualParams().cantParams > 0){
                        errors +=("Error en la llamada del método, cantidad de parámetros diferente a la declaración\n");
                        return id.type;
                    }
                }
            }else{
                try {
                    if ((((miParser.FunctionDeclASTContext) id.declCtx).formalParams()).cantParams > 0){
                        errors +=("Error en la llamada del método, cantidad de parámetros diferente a la declaración\n");
                        return id.type;
                    }
                }catch (Exception ignored){
                    return id.type;
                }

            }
            for (int i = 0; i < id.listParams.size(); i++) {

                if(!types.get(i).equals(id.listParams.get(i).type))
                    errors +=("Error, el parametro es tipo <"+id.listParams.get(i).type+"> y está ingresando un tipo <"+types.get(i)+">.\n");


            }
            return id.type;
        }
        return ctx.ID();
    }

    @Override
    public Object visitActualParamsAST(miParser.ActualParamsASTContext ctx) {
        types.clear();

        for (int i = 0; i < ctx.expression().size(); i++) {
            types.add((String) this.visit(ctx.expression(i)));
            this.visit(ctx.expression(i));
        }

        ctx.cantParams = ctx.expression().size();

        return ctx;
    }

    @Override
    public Object visitArrLookupAST(miParser.ArrLookupASTContext ctx) {
        Ident id = tabla.buscar(ctx.ID().getText());
        if (id != null){
            if(id.type.equals("int[]")){
                return "int";
            }else if( id.type.equals("char[]")){
                return "char";
            }else if( id.type.equals("boolean[]")){
                return "boolean";
            }else if( id.type.equals("string[]")){
                return "string";
            }
        }
        this.visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitArrLengthAST(miParser.ArrLengthASTContext ctx) {

        Ident id = tabla.buscar(ctx.ID().getText());
        if (id != null){
            if((id.type.contains("[]"))){
                return "int";
            }else if(id.type.equals("string")){
                return "int";
            }
        }
        if(id == null){
            errors += "Error, \""+ ctx.ID().getText() +"\" no existe. \n";
            return null;
        }
        errors += "Error, a "+ctx.ID().getText() +" no se le puede hacer un length. \n";
        return null;
    }

}
