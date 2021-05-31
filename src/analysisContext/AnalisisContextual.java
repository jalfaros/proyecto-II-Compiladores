package analysisContext;

import generated.miParser;
import generated.miParserBaseVisitor;
import interprete.Almacen;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class AnalisisContextual extends miParserBaseVisitor {

    private Stack<Object> pilaExpresiones;
    private TablaSimbolos tabla;
    private TablaSimbolClass tablaClass;

    List<String> types = new ArrayList<String>();
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
       this.pilaExpresiones = new Stack<Object>();

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
                errors += ("Error, el array <" + id.tok +"> No ha sido inicializado.\n");
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

        tabla.openScope();
                this.visit(ctx.block());
        tabla.closeScope();

        return null;
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
        List<Ident.Params> list = (List<Ident.Params>) this.visit(ctx.formalParams());
        if(ctx.type() != null){
            id = this.visit(ctx.type());
            funcType = (String) id;
            if (id.equals("int") || id.equals("boolean") || id.equals("char") || id.equals("string")){
                Ident t = tabla.buscar(ctx.ID().getText());
                if (t == null){
                    tabla.agregarParams(ctx.ID().getText(), (String) id, ctx, list );
                    t = tabla.buscar(ctx.ID().getText());
                    t.initialited = true;
                    this.visit(ctx.block());
                }else{
                    errors += ("Error, ya existe una función con este nombre.\n");
                }

            }else{
                errors +=("Error, no se permite este tipo de dato en las funciones.\n");
            }
        }

        for (Ident.Params params : list) {
            tabla.borrar(params.name);
        }

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

        tabla.insertar(ctx.ID().getText(), ctx.type().getText(), null);
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

    @Override
    public Object visitClassDelcAST(miParser.ClassDelcASTContext ctx) {

        Ident existClass = tabla.buscar(ctx.ID().getText());
        if(existClass == null){
            tabla.insertar(ctx.ID().getText(), null, ctx);
        }else{
            errors += "Error, ya existe una declaración con el nombre <"+ctx.ID().getText()+">. \n";
        }

        return null;
    }

    @Override
    public Object visitClassVariableDeclAST(miParser.ClassVariableDeclASTContext ctx) {

        String tipo = (String) visit(ctx.stype());
        Ident exist = tabla.buscar(ctx.ID().getText());

        if(exist == null) {
            if (ctx.ASSIGN() == null) {
                if (tipo.contains("[]")) {
                    //Guardando el array
                    //this.tabla.insertar(pilaExpresiones.pop() + "." + ctx.ID().getText(), tipo, null);
                    errors += "Error, sólo se permite declaraciones de variables en las clases.\n";
                } else {
                    switch (tipo) {
                        case "boolean", "string", "char", "int" -> this.tabla.insertar(pilaExpresiones.pop() + "." + ctx.ID().getText(), tipo, null);
                    }
               }
            } else {
                if (tipo.equals("int") || tipo.equals("char") || tipo.equals("string") || tipo.equals("boolean")) {
                    if (this.visit(ctx.expression()).equals(tipo)) {
                        this.tabla.insertar(pilaExpresiones.pop() + "." + ctx.ID().getText(), tipo, null);
                    }else {
                        errors += "Error, a <"+pilaExpresiones.pop() + "." + ctx.ID().getText()+"> debe asignar el mismo tipo de dato.\n";
                    }
                } else if (tipo.equals("int[]") || tipo.equals("char[]") || tipo.equals("string[]") || tipo.equals("boolean[]")) {
                    errors += "Error, sólo se permite declaraciones de variables en las clases.\n";
                    /*if(ctx.expression().getText().contains((tipo+"[")) && ctx.expression().getText().contains("new")) {
                        this.tabla.insertar(pilaExpresiones.pop() + "." + ctx.ID().getText(), tipo, null);
                        exist = tabla.buscar(pilaExpresiones.pop() + "." + ctx.ID().getText());
                        exist.initialited = true;
                    }else{
                        errors += "Error, no es la manera correcta de inicilizar un arreglo.\n";
                    }*/
                }
            }
        }else {
            if (exist.tok.contains(".")) {
                errors += "Error, ya existe una declaración con el nombre <" + ctx.ID().getText() + ">. \n";
            }else{
                if (ctx.ASSIGN() == null) {
                    if (tipo.contains("[]")) {
                        //Guardando el array
                        //this.tabla.insertar(pilaExpresiones.pop() + "." + ctx.ID().getText(), tipo, null);
                        errors += "Error, sólo se permite declaraciones de variables en las clases.\n";
                    } else {
                        switch (tipo) {
                            case "boolean", "string", "char", "int" -> this.tabla.insertar(pilaExpresiones.pop() + "." + ctx.ID().getText(), tipo, null);
                        }
                    }
                } else {
                    if (tipo.equals("int") || tipo.equals("char") || tipo.equals("string") || tipo.equals("boolean")) {
                        if (this.visit(ctx.expression()).equals(tipo)) {
                            this.tabla.insertar(pilaExpresiones.pop() + "." + ctx.ID().getText(), tipo, null);
                        }else {
                            errors += "Error, a <"+pilaExpresiones.pop() + "." + ctx.ID().getText()+"> debe asignar el mismo tipo de dato.\n";
                        }
                    } else if (tipo.equals("int[]") || tipo.equals("char[]") || tipo.equals("string[]") || tipo.equals("boolean[]")) {
                        errors += "Error, sólo se permite declaraciones de variables en las clases.\n";
                    }
                }
            }
        }

        return null;
    }

    @Override
    public Object visitVariableDeclAST(miParser.VariableDeclASTContext ctx) {

        Ident idExist = tabla.buscar(ctx.ID().getText());
        Ident typeClass = tabla.buscar(ctx.type().getText());

        //VALIDANDO SI YA EXISTE EL ID
        if(idExist == null){
            //VALIDANDO SÓLO LA DECLARACIÓN
            if (ctx.ASSIGN() == null){
                if(ctx.type().getText().equals("boolean[]") || ctx.type().getText().equals("char[]") || ctx.type().getText().equals("int[]") || ctx.type().getText().equals("string[]")){
                    tabla.insertar(ctx.ID().getText(), ctx.type().getText(), ctx);

                }else if (ctx.type().getText().equals("boolean") || ctx.type().getText().equals("char") || ctx.type().getText().equals("int") || ctx.type().getText().equals("string")){
                    tabla.insertar(ctx.ID().getText(), ctx.type().getText(), ctx);

                }else if(typeClass != null){
                    //GUARDANDO LA CLASE
                    tabla.insertar(ctx.ID().getText(), ctx.type().getText(), null);

                }else errors +=("Error, el tipo de dato <"+ctx.type().getText()+"> no corresponde a ningún tipo de dato.\n");

            //VALIDANDO DECLARACION Y ASIGNACION
            }else {
                //VALIDANDO LOS ARRAYS
                if(ctx.type().getText().equals("boolean[]") || ctx.type().getText().equals("char[]") || ctx.type().getText().equals("int[]") || ctx.type().getText().equals("string[]")){
                    //VALINDO QUE SEA DEL MISMO TIPO
                    this.visit(ctx.expression());
                    if (ctx.expression().getText().contains(("new"+(ctx.type().getText().replace("[]",""))+"["))) {
                        tabla.insertar(ctx.ID().getText(), ctx.type().getText(), null);
                        idExist = tabla.buscar(ctx.ID().getText());
                        tabla.insertar(ctx.ID().getText()+"[]", (ctx.type().getText().replace("[]", "")), null);
                        idExist.initialited = true;

                    }else {
                        errors += "Error, no es la manera correcta de inicializar un arreglo.\n";
                    }
                //VALIDANDO LAS VARIABLES
                }else if (ctx.type().getText().equals("boolean") || ctx.type().getText().equals("char") || ctx.type().getText().equals("int") || ctx.type().getText().equals("string")){
                    try {
                        if(!this.visit(ctx.expression()).equals(ctx.type().getText())){
                            errors += "Error, está tratando de asignar a <"+ctx.ID().getText()+"> un tipo de dato distinto.\n";
                        }else {
                            tabla.insertar(ctx.ID().getText(), ctx.type().getText(), ctx);
                        }
                    }catch (Exception e){
                        errors += "Error, está tratando de asignar a <"+ctx.ID().getText()+"> un tipo de dato inválido.\n";
                    }

                //VALIDANDO LAS CLASES
                }else if(typeClass != null){
                    //VALINDO QUE SEA DEL MISMO TIPO
                    if (ctx.expression().getText().contains(("new"+(ctx.type().getText()+("()"))))) {

                        //Guardando la inicialización de la clase
                        tabla.insertar(ctx.ID().getText(), ctx.type().getText(), null);
                        idExist = tabla.buscar(ctx.ID().getText());
                        idExist.initialited = true;

                        //Busco la clase
                        Ident ins = tabla.buscar(ctx.type().getText());
                        for (int i = 0; i <((Integer) (((miParser.ClassDelcASTContext)ins.declCtx).classVariableDeclaration().size())) ; i++) {
                            pilaExpresiones.push(ctx.ID().getText());

                            //Hago las visitas en variableDeclaration
                            this.visit(((miParser.ClassDelcASTContext)ins.declCtx).classVariableDeclaration(i));

                        }
                    }else {
                        errors += "Error, no es la manera correcta de inicializar una clase.\n";
                    }

                }else errors +=("Error, el tipo de dato <"+ctx.type().getText()+"> no corresponde a ningún tipo de dato.\n");
            }
        }else {
            errors+= "Error, el nombre <"+idExist.tok+"> ya está siendo utilizada.\n";
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

            Ident classExist = tabla.buscar(part1+"."+part2);
            Ident idExist = tabla.buscar(part1);

            if(classExist != null) {//Busco en la tabla local si existe
                if (idExist.initialited){ //Viendo si la vara fue inicializada
                    if(!classExist.type.equals(this.visit(ctx.expression()))){
                        errors+= "Error, <"+part1+"."+part2+"> es tipo <" +classExist.type +"> y está tratando de asignar un tipo diferente.\n";
                    }
                }else errors +=("Error, <"+part1+"> no a sido inicializada.\n");
            }else errors +=("Error, está asignando a <"+ctx.ID(0).getText()+"> un dato invalido.\n");
            return null;

        }else {
            Ident id = tabla.buscar(ctx.ID(0).getText());

            if (id != null ) {
                Object exprType = this.visit(ctx.expression() );
                String expre = ctx.expression().getText();

                if(id.type == null){
                    errors += "Error, no es la manera correcta para inicializar a una clase.\n";
                } if (expre.contains("new") && expre.contains("()")){

                    if(expre.contains(("new"+id.type+"()"))){

                        //Guardando la inicialización de la clase
                        id.initialited = true;

                        //Busco la clase
                        Ident ins = tabla.buscar(id.type);

                        for (int i = 0; i <((Integer) (((miParser.ClassDelcASTContext)ins.declCtx).classVariableDeclaration().size())) ; i++) {
                            pilaExpresiones.push(id.tok);

                            //Hago las visitas en variableDeclaration
                            this.visit(((miParser.ClassDelcASTContext)ins.declCtx).classVariableDeclaration(i));
                        }

                    }else {
                        errors += "Error, la variable es tipo <"+id.type+"> y está tratando de inicializar una clase.\n";
                    }
                } else if (expre.contains("new") && expre.contains("[")){
                    assert id.type != null;
                    if(expre.contains(("new"+(id.type.replace("[]", ""))+"["))){
                        tabla.insertar(ctx.ID(0).getText()+"[]", (id.type.replace("[]", "")), null);
                        id.initialited = true;
                    }else {
                        errors += "Error, la variable es tipo <"+id.type+"> y está tratando de inicializar un tipo distinto.\n";
                    }
                }else if (exprType != null){
                    if(exprType.equals("int") || exprType.equals("string") || exprType.equals("char") || exprType.equals("boolean")){
                        assert id.type != null;
                        if(!id.type.equals(exprType)){
                            errors += "Error, la variable <"+id.tok+"> es tipo <"+id.type+"> y está tratando de asignarle un tipo <"+exprType+">.\n";
                        }
                    }else if(exprType.equals("int[]") || exprType.equals("string[]") || exprType.equals("char[]") || exprType.equals("boolean[]")){
                        assert id.type != null;
                        if(!id.type.equals(exprType)){
                            errors += "Error, la variable <"+id.tok+"> es tipo <"+id.type+"> y está tratando de asignarle un tipo <"+exprType+">.\n";
                        }
                    }
                }else {
                    errors += "Error, el dato que quiere asignar a <"+id.tok+"> es un dato invalido.\n";
                }


            } else {
                errors +=("Error, \"" + ctx.ID(0).getText() + "\" aún no ha sido declaradooo.\n");
            }
        }

        return null;
    }

    @Override
    public Object visitExpressionAST(miParser.ExpressionASTContext ctx) {
        String exprType = (String) this.visit(ctx.simpleExpression(0));
        String exprType2 = null;
        String operador = null;
        Ident id1 = null;
        Ident id2 = null;

        for (int i = 1; i < ctx.simpleExpression().size(); i++) {
            operador = ctx.ROPERATOR().get(0).toString();

            try {
                exprType2 = (String) this.visit(ctx.simpleExpression(i));

                if(ctx.simpleExpression(i).getText().contains("[")){
                    String[] parts = ctx.simpleExpression(i).getText().split("\\[");
                    String part1 = parts[0]; // 123
                    id2 = tabla.buscar((part1+"[]"));
                }else {
                    id2 = tabla.buscar(ctx.simpleExpression(i).getText());
                }

                if(ctx.simpleExpression(i-1).getText().contains("[")){
                    String[] parts = ctx.simpleExpression(i-1).getText().split("\\[");
                    String part1 = parts[0]; // 123
                    id1 = tabla.buscar((part1+"[]"));
                }else {
                    id1 = tabla.buscar(ctx.simpleExpression(i-1).getText());
                }


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

        if(ctx.simpleExpression(0).getText().contains("[")){
            String[] parts = ctx.simpleExpression(0).getText().split("\\[");
            String part1 = parts[0];
            id1 = tabla.buscar((part1+"[]"));
        }else {
            id1 = tabla.buscar(ctx.simpleExpression(0).getText());
        }

        if (id1 != null){
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

            if(ctx.term(i).getText().contains("[")){
                String[] parts = ctx.term(i).getText().split("\\[");
                String part1 = parts[0]; // 123
                id2 = tabla.buscar((part1+"[]"));
            }else {
                id2 = tabla.buscar(ctx.term(i).getText());
            }

            if(ctx.term(i-1).getText().contains("[")){
                String[] parts = ctx.term(i-1).getText().split("\\[");
                String part1 = parts[0]; // 123
                id1 = tabla.buscar((part1+"[]"));
            }else {
                id1 = tabla.buscar(ctx.term(i-1).getText());
            }


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
                errors +=("Error, operando con datos no validos!\n");
            }

        }
        if(aOperador == null) return simpleExpre1;
        return null;

    }

    @Override
    public Object visitTermAST(miParser.TermASTContext ctx) {

        String factor1 = (String) this.visit(ctx.factor(0));
        String factor2 = null;
        String mOperador = null;
        Ident id1 = null;
        Ident id2 = null;

        for (int i = 1; i < ctx.factor().size(); i++) {

            mOperador = ctx.MOP().get(i-1).getText();
            //id1 = tabla.buscar(ctx.factor(i-1).getText());
            //id2 = tabla.buscar(ctx.factor(i).getText());
            if(ctx.factor(i).getText().contains("[")){
                String[] parts = ctx.factor(i).getText().split("\\[");
                String part1 = parts[0]; // 123
                id2 = tabla.buscar((part1+"[]"));
            }else {
                id2 = tabla.buscar(ctx.factor(i).getText());
            }

            if(ctx.factor(i-1).getText().contains("[")){
                String[] parts = ctx.factor(i-1).getText().split("\\[");
                String part1 = parts[0]; // 123
                id1 = tabla.buscar((part1+"[]"));
            }else {
                id1 = tabla.buscar(ctx.factor(i-1).getText());
            }

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
        Ident id = tabla.buscar(ctx.ID().getText());
        if (id == null ){
            errors += "Error, la clase que quiere inicializar no existe.\n";
        }else{
            if(id.type != null){
                errors += "Error, lo que desea inicializar no corresponde a una clase.\n";
            }
        }
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
                try {
                    if(!types.get(i).equals(id.listParams.get(i).type))
                        errors +=("Error, el parametro es tipo <"+id.listParams.get(i).type+"> y está ingresando un tipo <"+types.get(i)+">.\n");


                }catch (Exception e){
                        errors += "Error, el dato que quiere enviar por parámetro es inválido.\n";
                }

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

        String[] parts = ctx.ID().getText().split("\\[");
        String part1 = parts[0];

        Ident id = tabla.buscar((part1+"[]"));
        if (id != null){
            if (this.visit(ctx.expression()) == null || !this.visit(ctx.expression()).equals("int")){

                errors += "Error, el indice del arreglo <"+ctx.ID().getText()+"> es inválido.\n";

            } else if(id.type.equals("int[]")){
                return "int";
            }else if( id.type.equals("char[]")){
                return "char";
            }else if( id.type.equals("boolean[]")){
                return "boolean";
            }else if( id.type.equals("string[]")){
                return "string";
            }
        }else{
            errors += "Error, <"+ctx.ID().getText()+">, no existe o no a sido inicializado.\n";
            return null;
        }
        return  null;

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
