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
    List<String> classes = new ArrayList<String>();
    String arrTyp = null;

   public AnalisisContextual() {

       tabla = new TablaSimbolos();
       tablaClass = new TablaSimbolClass();
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
        for (int i = 0; i < ctx.statement().size(); i++) {
            this.visit(ctx.statement(i));
        }
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

        if(this.visit(ctx.printStatement()) == null){
            System.out.println("Error, está intentando imprimir algo que no existe!");
        }

        return null;
    }

    @Override
    public Object visitIfStaAST(miParser.IfStaASTContext ctx) {
        this.visit(ctx.ifStatement());
        return null;
    }

    @Override
    public Object visitWhileStaAST(miParser.WhileStaASTContext ctx) {
        this.visit(ctx.whileStatement());
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
       tabla.openScope();
        for (miParser.StatementContext c: ctx.statement())
            this.visit(c);

        System.out.println(" La tabla de la clase ");
        tabla.imprimir();
        tabla.closeScope();
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
        Object expr = this.visit(ctx.expression());

        if(expr == null ){
            System.out.println("Error, el operador en el \"while\" es invalido.");
        }

        this.visit(ctx.expression());
        this.visit(ctx.block());
        return null;
    }

    @Override
    public Object visitIfStmntAST(miParser.IfStmntASTContext ctx) {
        Object expr = this.visit(ctx.expression());

        if(expr == null ){
            System.out.println("Error, el operador en el \"if\" es invalido.");
        }
        this.visit(ctx.block(0));

        if(ctx.block(1) !=  null){

            this.visit(ctx.block(1));
        }




        return null;
    }

    @Override
    public Object visitReturnStmntAST(miParser.ReturnStmntASTContext ctx) {

        if(this.visit(ctx.expression()) == null){
            System.out.println("Está retornando un dato invalido.");
        }
        this.visit(ctx.expression());
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
                            }else System.out.println("Esta tratando de asignar a <"+ctxVar.expression().getText()+"> un tipo de dato distinto.");
                        }else if (id != null){
                            if (id.type.equals(ctxVar.stype().getText())){
                                tablaClass.insertar(ctxVar.ID().getSymbol(), ctxVar.stype().getText(), ctxVar, ctx.ID().getText());
                                id = tablaClass.buscar(ctxVar.ID().getText());
                                id.initialited = true;
                            }else System.out.println("Esta tratando de asignar a <"+ctxVar.expression().getText()+"> un tipo de dato distinto.");

                        } else System.out.println("Error, el dato que le quiere asignar a <"+ctxVar.ID()+"> en la clase <"+ctx.ID().getText() +">, no es un tipo de dato valido.");

                    }else{
                        System.out.println("Error, ya existe en la clase <"+ctx.ID().getText() +"> una variable con el nombre <"+ ctxVar.ID().getText() + ">.");
                    }
                }else {
                    id = tablaClass.buscarClaseYVar(ctx.ID().getText(),ctxVar.expression().getText());
                    if( this.visit(ctxVar.expression()) != null){
                        if (this.visit(ctxVar.expression()).equals(ctxVar.stype().getText())){
                            tablaClass.insertar(ctxVar.ID().getSymbol(), ctxVar.stype().getText(), ctxVar, ctx.ID().getText());
                            id = tablaClass.buscar(ctxVar.ID().getText());
                            id.initialited = true;
                        }else System.out.println("Esta tratando de asignar a <"+ctxVar.expression().getText()+"> un tipo de dato distinto.");
                    }else if (id != null){
                        if (id.type.equals(ctxVar.stype().getText())){
                            tablaClass.insertar(ctxVar.ID().getSymbol(), ctxVar.stype().getText(), ctxVar, ctx.ID().getText());
                            id = tablaClass.buscar(ctxVar.ID().getText());
                            id.initialited = true;
                        }else System.out.println("Esta tratando de asignar a <"+ctxVar.expression().getText()+"> un tipo de dato distinto.");

                    } else System.out.println("Error, el dato que le quiere asignar a <"+ctxVar.ID()+"> en la clase <"+ctx.ID().getText() +">, no es un tipo de dato valido.");

                }

            }else {
                id = tablaClass.buscar(ctxVar.ID().getText());
                if(id != null){
                    if (!id.className.equals(ctx.ID().getText())){
                        tablaClass.insertar(ctxVar.ID().getSymbol(), ctxVar.stype().getText(), ctxVar, ctx.ID().getText());
                    }else{
                        System.out.println("Error, ya existe en la clase <"+ctx.ID().getText() +"> una variable con el nombre <"+ ctxVar.ID().getText() + ">.");
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


        return "hola";
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
        if (ctx.ASSIGN() == null && idExist == null){
            if(ctx.type().getText().equals("boolean[]") || ctx.type().getText().equals("char[]") || ctx.type().getText().equals("int[]") || ctx.type().getText().equals("string[]")){
                tabla.insertar(ctx.ID().getSymbol(),ctx.type().getText(),ctx);

            }else if (ctx.type().getText().equals("boolean") || ctx.type().getText().equals("char") || ctx.type().getText().equals("int") || ctx.type().getText().equals("string")){
                tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);

            }else if(classExist){
                tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);

            }else System.out.println("Error, el tipo de dato <"+ctx.type().getText()+"> no corresponde a ningún tipo de dato.");

        //VALIDANDO DECLARACION Y ASIGNACION
        }else if(ctx.ASSIGN() != null && idExist == null){
            //VALIDANDO ARRAYS
            if(ctx.type().getText().equals("boolean[]") || ctx.type().getText().equals("char[]") || ctx.type().getText().equals("int[]") || ctx.type().getText().equals("string[]")){
                if (this.visit(ctx.expression()) != null){
                    try {
                        if (ctx.expression().getText().substring(0, 3).equals("new")) {
                            if (ctx.type().getText().equals((ctx.expression().getText().substring(3, ctx.type().getText().length() +1 )) + "[]" )) {
                                if(this.visit(ctx.expression()).equals("int")) {
                                    tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                                    idExist = tabla.buscar(ctx.ID().getText());
                                    idExist.initialited = true;
                                } else System.out.println();
                            }else {
                                System.out.println("Error, el array es tipo <"+ctx.type().getText()+"> y está tratando de inicializarlo con otro tipo.");
                            }

                        }else if(ctx.type().getText().equals(this.visit(ctx.expression()))){
                            idExist = tabla.buscar(ctx.expression().getText());
                            if(idExist.initialited){
                                tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                                idExist = tabla.buscar(ctx.ID().getText());
                                idExist.initialited = true;
                            }else System.out.println("Error, el arreglo <"+ctx.expression().getText()+"> aún no ha sido inicializado.");

                        }else System.out.println("Error, el array es tipo <"+ctx.type().getText()+"> y está tratando de asignar otro tipo.");

                    } catch (Exception e) {
                        if(ctx.type().getText().equals(this.visit(ctx.expression()))){
                            idExist = tabla.buscar(ctx.expression().getText());
                            if(idExist.initialited){
                                tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                                idExist = tabla.buscar(ctx.ID().getText());
                                idExist.initialited = true;
                            }else System.out.println("Error, el arreglo <"+ctx.expression().getText()+"> aún no ha sido inicializado.");

                        }else System.out.println("Error, el array es tipo <"+ctx.type().getText()+"> y está tratando de inicializarlo con otro tipo.");
                    }
                }else if (ctx.expression().getText().contains(".")) {
                    if(arrTyp != null){
                        tabla.insertar(ctx.ID().getSymbol(),ctx.type().getText(),ctx);
                        idExist = tabla.buscar(ctx.ID().getText());
                        idExist.initialited = true;
                    }
                }

                else System.out.println("Error, el dato que trata de asignar a <"+ctx.ID().getText()+"> aún no ha sido declarado.");

            //VALIDANDO VARIABLES
            }else if ((ctx.type().getText().equals("boolean") || ctx.type().getText().equals("char") || ctx.type().getText().equals("int") || ctx.type().getText().equals("string"))){
                //System.out.println(this.visit(ctx.expression()) + " "+ ctx.expression().getText());
                if (ctx.type().getText().equals(this.visit(ctx.expression()))){
                    Ident id = tabla.buscar(ctx.expression().getText());
                        if(id != null) {
                            if (!id.initialited) {
                                System.out.println("Error, la variable <" + ctx.ID().getText()+ "> no puede ser asignada porque <"+id.tok.getText()+"> no ha sido inicializada.");
                            }
                        }else {
                            tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                            idExist = tabla.buscar(ctx.ID().getText());
                            idExist.initialited = true;
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
                                            tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                                            idExist = tabla.buscar(ctx.ID().getText());
                                            idExist.initialited = true;
                                        } else
                                            System.out.println("Error, <" + part1 + "> no a sido inicializada.");
                                    } else
                                        System.out.println("Error, la variable es tipo <" + ctx.type().getText() + "> y está tratando de inicializarlo con otro tipo.");
                                } else System.out.println("Error, el dato que quiere asignar no existe.");
                            }else System.out.println("Error, no se encuentra <"+part2+"> en < "+part1+" >.");
                        }else System.out.println("Error, <"+part1+"> no a sido inicializada.");
                    }else System.out.println("Error, está asignando a <"+ctx.ID().getText()+"> un dato invalido.");

                }else System.out.println("Error, el dato que se quiere asignar a <"+ctx.ID().getText()+"> no es un dato valido.");
                //----------------------------------------------------------------------------------------

            }else if(classExist){
                if (ctx.expression().getText().substring(0, 3).equals("new")) {
                    if (ctx.type().getText().equals((ctx.expression().getText().substring(3, ctx.expression().getText().length() -2 ))  )) {
                        tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);
                        idExist = tabla.buscar(ctx.ID().getText());
                        idExist.initialited = true;
                    }else {
                        System.out.println("Error, la variable es tipo <"+ctx.type().getText()+"> y está tratando de usar un dato invalido sin inicializar.");
                    }

                }else System.out.println("Error, la variable es tipo <"+ctx.type().getText()+"> y está tratando de usar un dato invalido sin inicializar.");


            }else System.out.println("Error, el tipo de dato <"+ctx.type().getText()+"> no corresponde a ningún tipo de dato.");

        }else if (ctx.type().getText().equals("boolean") || ctx.type().getText().equals("char") || ctx.type().getText().equals("int") || ctx.type().getText().equals("string")) {
            tabla.insertar(ctx.ID().getSymbol(), ctx.type().getText(), ctx);

        }else {
            System.out.println("Error, la variable <"+ctx.ID().getText()+"> ya está siendo utilizada por otro tipo.");
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
                                            System.out.println("Error, la variable <" + id.tok.getText() + "> no puede ser asignada porque no se le ha asignado ningún dato.");
                                        }
                                    }else {
                                        idExist = tablaClass.buscarClaseYVar(idExist.className,part2);
                                        idExist.initialited = true;
                                    }
                                }else {
                                    if (!idExist.type.equals(this.visit(ctx.expression()))){
                                        System.out.println("Error, no se le puede asignar a un tipo <"+idExist.type +"> un tipo <"+this.visit(ctx.expression())+">.");
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
                                                            System.out.println("Error, <" + idExist.tok.getText() + "> no a sido inicializada.");
                                                    } else
                                                        System.out.println("Error, la variable es tipo <" + expre1.type + "> y está tratando de inicializarlo con otro tipo.");
                                                } else System.out.println("Error, el dato que quiere asignar no existe.");
                                            }else System.out.println("Error, no se encuentra <"+point2+"> en < "+point1+" >.");
                                        }else System.out.println("Error, <"+point1+"> no a sido inicializada.");
                                    }else System.out.println("Error, está asignando a <"+expre1.tok.getText()+"> un dato invalido.");

                                }else System.out.println("Error, a <"+part1+"."+part2+"> no se puede asignar un dato que no es válido.");

                            }
                        } else System.out.println("Error, el dato que quiere asignar no existe.");
                    }else System.out.println("Error, no se encuentra <"+part2+"> en < "+part1+" >.");
                }else System.out.println("Error, <"+part1+"> no a sido inicializada.");
            }else System.out.println("Error, está asignando a <"+ctx.ID(0).getText()+"> un dato invalido.");
            return null;
        }

        Ident id = tabla.buscar(ctx.ID(0).getText());

        if (id != null && ctx.PUNTO() == null) {
            Object exprType = this.visit(ctx.expression() );
            Ident idExp2 = tabla.buscar(ctx.expression().getText());


            if (idExp2 != null) {
                if (idExp2.type.substring(idExp2.type.length() - 2, idExp2.type.length()).equals("[]"))
                    if (!idExp2.initialited)
                        System.out.println("Error, el array \"" + ctx.expression().getText() + "\" no ha sido inicializado.");
                    else if (!id.initialited)
                        System.out.println("Error, el array \"" + ctx.ID(0).getText() + "\" no ha sido inicializado.");

                    else if (!id.type.equals(idExp2.type))
                        System.out.println("Error, el array \"" + ctx.expression().getText() + "\" no es del mismo tipo.");

            } else if (id.type.substring(id.type.length() - 2, id.type.length()).equals("[]")) {

                try {
                    String test = (ctx.expression().getText().substring(0, 3));
                    if (test.equals("new")) {
                        String test2 = (ctx.expression().getText().substring(3, 1 + id.type.length()));
                        if (!id.type.equals((test2 + "[]"))) {
                            System.out.println("Error, el array es tipo \"" + id.type + "\" y está tratando de inicializar con un tipo de dato diferente");
                        } else if (exprType.equals("int")) {
                            System.out.println("Agregando la inicializacion, en el asssingAST a: " + ctx.ID(0).getText());
                            id.initialited = true;
                        }
                    } else if (!id.initialited)
                        System.out.println("El arreglo \" " + ctx.ID(0).getText() + " \" aún no ha sido inicializado.");
                    else if(!exprType.equals((id.type.substring(0,id.type.length()-2)))){
                        System.out.println("Error, al arreglo no se le permite asignar de esta manera.");
                    }
                } catch (Exception e) {
                    System.out.println("Error, no es la manera correcta para asignar a un array.");
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
                                            System.out.println("Error, <" + idExist.tok.getText() + "> no a sido inicializada.");
                                    } else
                                        System.out.println("Error, la variable es tipo <" + id.type + "> y está tratando de inicializarlo con otro tipo.");
                                } else System.out.println("Error, el dato que quiere asignar no existe.");
                            }else System.out.println("Error, no se encuentra <"+point2+"> en < "+point1+" >.");
                        }else System.out.println("Error, <"+point1+"> no a sido inicializada.");
                    }else System.out.println("Error, está asignando a <"+id.tok.getText()+"> un dato invalido.");

                }else if (idExp2 == null) {
                    System.out.println(" <" + ctx.expression().getText() + ">, no corresponde a un tipo de dato o no ha sido declaradooo!");
                } else if (!idExp2.type.equals(id.type)) {
                    System.out.println("La asignación: <" + ctx.expression().getText() + ">, no corresponde al mismo tipo de dato!");
                }
            } else if (id.type.equals((ctx.expression().getText().substring(3, ctx.expression().getText().length()-2)))){
                try {
                    String test = (ctx.expression().getText().substring(0, 3));
                    if (test.equals("new")) {
                        String test2 = (ctx.expression().getText().substring(3, ctx.expression().getText().length() - 2 ));
                        System.out.println(test2);
                        if (!id.type.equals((test2))) {
                            System.out.println("Error, la variable es tipo \"" + id.type + "\" y está tratando de inicializar con un tipo de dato diferente");
                        } else{
                            System.out.println(id.tok.getText());
                            id.initialited = true;
                        }
                    } else if (!id.initialited)
                        System.out.println("El arreglo \" " + ctx.ID(0).getText() + " \" aún no ha sido inicializado.");
                } catch (Exception e) {
                    System.out.println("Error, no es la manera correcta para asignar a la variable.");
                }
            } else if (!id.type.equals(exprType)) {
                System.out.println("Los tipos son imcompatibles para la asignación entre: <" + id.type + "> y <" + exprType + ">.");
            }

        } else if (id == null) {
            System.out.println("\"" + ctx.ID(0).getText() + "\" no ha sido declarado!!!");
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
                            System.out.println("Error, <" + exprType + "> y <" + exprType2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + operador + "\".");
                        } else if (!exprType2.equals(exprType)) {
                            System.out.println("Error, <" + exprType + "> y <" + exprType2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + operador + "\".");
                        } else return "boolean";

                    } else if (exprType2 == null && exprType != null) {
                        if (!exprType.equals(id2.type)) {
                            System.out.println("Error, <" + exprType + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                        } else if (exprType.equals("boolean") || exprType.equals("char") || exprType.equals("string")) {
                            System.out.println("Error, <" + exprType + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + operador + "\".");
                        } else return "boolean";

                    } else if (exprType2 != null) {
                        if (!exprType2.equals(id1.type)) {
                            System.out.println("Error, <" + id1.type + "> y <" + exprType2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                        } else if (exprType2.equals("boolean") || exprType2.equals("char") || exprType2.equals("string")) {
                            System.out.println("Error, <" + exprType2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + operador + "\".");
                        } else return "boolean";

                    } else {
                        if (id1.type.equals("char") || id1.type.equals("boolean") || id1.type.equals("string")) {
                            System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                        } else if (!id1.type.equals(id2.type)) {
                            System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                        } else return "boolean";
                    }

                } else if (operador.equals("!=") || operador.equals("==")) {
                    if (exprType2 != null && exprType != null) {
                        if (!exprType2.equals(exprType)) {
                            System.out.println("Error,<" + exprType2 + "> y <" + exprType + "> son tipos de datos incompatibles, sólo se permite datos de igual tipo.");
                        } else return "boolean";

                    } else if (exprType2 == null && exprType != null) {
                            if (!exprType.equals(id2.type)) {
                            System.out.println("Error, <" + exprType + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                        } else return "boolean";

                    } else if (exprType2 != null) {
                             if (!exprType2.equals(id1.type)) {
                            System.out.println("Error, <" + id1.type + "> y <" + exprType2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                        } else return "boolean";

                    } else {
                         if (!id1.type.equals(id2.type)) {
                            System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + operador + "\".");
                        } else return "boolean";
                    }

                }

            } catch (Exception e) {
                System.out.println("Está operando con datos que no existen.");
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
                            if (simpleExpre1.equals("string") || simpleExpre1.equals("char") || (simpleExpre2.equals("string") || simpleExpre2.equals("char"))) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (int o boolean) en el operador  \"" + aOperador + "\".");
                            } else if (!simpleExpre2.equals(simpleExpre1)) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (int o boolean) en el operador  \"" + aOperador + "\".");
                            } else return "boolean";

                        } else if (simpleExpre2 == null && simpleExpre1 != null) {
                            if (!simpleExpre1.equals(id2.type)) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + aOperador + "\".");
                            } else if (simpleExpre1.equals("string") || simpleExpre1.equals("char")) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o boolean) en el operador  \"" + aOperador + "\".");
                            } else return "boolean";

                        } else if (simpleExpre2 != null) {
                             if (!simpleExpre2.equals(id1.type)) {
                                System.out.println("Error, <" + id1.type + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + aOperador + "\".");
                            } else if (simpleExpre2.equals("string") || simpleExpre2.equals("char")) {
                                System.out.println("Error, <" + simpleExpre2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o boolean) en el operador  \"" + aOperador + "\".");
                            } else return "boolean";

                        } else {
                            if (id1.type.equals("char") || id1.type.equals("string")) {
                                System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + aOperador + "\".");
                            } else if (!id1.type.equals(id2.type)) {
                                System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + aOperador + "\".");
                            } else return "boolean";
                        }
                    }
                    case "+" -> {
                        if (simpleExpre2 != null && simpleExpre1 != null) {
                            if (simpleExpre1.equals("boolean") || simpleExpre1.equals("char") || (simpleExpre2.equals("boolean") || simpleExpre2.equals("char"))) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (int o string) en el operador  \"" + aOperador + "\".");
                            } else if (!simpleExpre2.equals(simpleExpre1)) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (int o string) en el operador  \"" + aOperador + "\".");
                            } else if (simpleExpre1.equals("int")) return "int";
                            else return "string";

                        } else if (simpleExpre2 == null && simpleExpre1 != null) {
                             if (!simpleExpre1.equals(id2.type)) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".");
                            } else if (simpleExpre1.equals("boolean") || simpleExpre1.equals("char")) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o string) en el operador  \"" + aOperador + "\".");
                            } else if (simpleExpre1.equals("int")) return "int";
                            else return "string";

                        } else if (simpleExpre2 != null) {
                             if (!simpleExpre2.equals(id1.type)) {
                                System.out.println("Error, <" + id1.type + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".");
                            } else if (simpleExpre2.equals("boolean") || simpleExpre2.equals("char")) {
                                System.out.println("Error, <" + simpleExpre2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o string) en el operador  \"" + aOperador + "\".");
                            } else if (simpleExpre2.equals("int")) return "int";
                            else return "string";

                        } else {
                            if (id1.type.equals("char") || id1.type.equals("boolean")) {
                                System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".");
                            } else if (!id1.type.equals(id2.type)) {
                                System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o string) en el operador  \"" + aOperador + "\".");
                            } else if (id1.type.equals("int")) return "int";
                            else return "string";
                        }
                    }
                    case "-" -> {
                        if (simpleExpre2 != null && simpleExpre1 != null) {
                            if (simpleExpre1.equals("boolean") || simpleExpre1.equals("char") || simpleExpre1.equals("string") || simpleExpre2.equals("string") || (simpleExpre2.equals("boolean") || simpleExpre2.equals("char"))) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + aOperador + "\".");
                            } else if (!simpleExpre2.equals(simpleExpre1)) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + aOperador + "\".");
                            } else return "int";

                        } else if (simpleExpre2 == null && simpleExpre1 != null) {
                            if (!simpleExpre1.equals(id2.type)) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".");
                            } else if (simpleExpre1.equals("boolean") || simpleExpre1.equals("char") || simpleExpre1.equals("string")) {
                                System.out.println("Error, <" + simpleExpre1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + aOperador + "\".");
                            } else return "int";

                        } else if (simpleExpre2 != null) {
                            if (!simpleExpre2.equals(id1.type)) {
                                System.out.println("Error, <" + id1.type + "> y <" + simpleExpre2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".");
                            } else if (simpleExpre2.equals("boolean") || simpleExpre2.equals("char") || simpleExpre2.equals("string")) {
                                System.out.println("Error, <" + simpleExpre2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + aOperador + "\".");
                            } else return "int";

                        } else {
                            if (id1.type.equals("char") || id1.type.equals("boolean") || id1.type.equals("string")) {
                                System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".");
                            } else if (!id1.type.equals(id2.type)) {
                                System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + aOperador + "\".");
                            } else return "int";
                        }
                    }
                }
            }catch (Exception e){
                    System.out.println("Está operando con datos no validos!");
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
                                System.out.println("Error, <" + factor1 + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + mOperador + "\".");
                            } else if (!factor2.equals(factor1)) {
                                System.out.println("Error, <" + factor1 + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos (int) en el operador  \"" + mOperador + "\".");
                            } else return "int";

                        } else if (factor2 == null && factor1 != null) {
                            if (!factor1.equals(id2.type)) {
                                System.out.println("Error, <" + factor1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                            } else if (factor1.equals("boolean") || factor1.equals("char") || factor1.equals("string")) {
                                System.out.println("Error, <" + factor1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + mOperador + "\".");
                            } else return "int";

                        } else if (factor2 != null) {
                            if (!factor2.equals(id1.type)) {
                                System.out.println("Error, <" + id1.type + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                            } else if (factor2.equals("boolean") || factor2.equals("char") || factor2.equals("string")) {
                                System.out.println("Error, <" + factor2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int) en el operador  \"" + mOperador + "\".");
                            } else return "int";

                        } else {
                            if (id1.type.equals("char") || id1.type.equals("boolean") || id1.type.equals("string")) {
                                System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                            } else if (!id1.type.equals(id2.type)) {
                                System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int) en el operador  \"" + mOperador + "\".");
                            } else return "int";
                        }

                        break;

                    case "&&":
                        if (factor2 != null && factor1 != null) {
                            if (factor1.equals("string") || factor1.equals("char") || (factor2.equals("string") || factor2.equals("char"))) {
                                System.out.println("Error, <" + factor1 + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos (int o boolean) en el operador  \"" + mOperador + "\".");
                            } else if (!factor2.equals(factor1)) {
                                System.out.println("Error, <" + factor1 + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos (int o boolean) en el operador  \"" + mOperador + "\".");
                            } else return "boolean";

                        } else if (factor2 == null && factor1 != null) {
                            if (!factor1.equals(id2.type)) {
                                System.out.println("Error, <" + factor1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + mOperador + "\".");
                            } else if (factor1.equals("string") || factor1.equals("char")) {
                                System.out.println("Error, <" + factor1 + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o boolean) en el operador  \"" + mOperador + "\".");
                            } else return "boolean";

                        } else if (factor2 != null) {
                            if (!factor2.equals(id1.type)) {
                                System.out.println("Error, <" + id1.type + "> y <" + factor2 + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + mOperador + "\".");
                            } else if (factor2.equals("string") || factor2.equals("char")) {
                                System.out.println("Error, <" + factor2 + "> y <" + id1.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean tipo (int o boolean) en el operador  \"" + mOperador + "\".");
                            } else return "boolean";

                        } else {
                            if (id1.type.equals("char") || id1.type.equals("string")) {
                                System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + mOperador + "\".");
                            } else if (!id1.type.equals(id2.type)) {
                                System.out.println("Error, <" + id1.type + "> y <" + id2.type + "> son  incompatibles, sólo se permiten tipos iguales y que sean (int o boolean) en el operador  \"" + mOperador + "\".");
                            } else return "boolean";
                        }
                        break;
                }

            }catch (Exception e){

                    System.out.println("Está operando con datos no validos.");
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
        //System.out.println("FACTOR ID PUNTO ID")
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
        System.out.println(this.visit(ctx.arrayLookup()));
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
                System.out.println("La inicialización de los arreglos deben de ser siempre de tipo int y se esta usando  tipo \""+attr+"\"");
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
                                    System.out.println("Error, <" + idExist.tok.getText() + "> no a sido inicializada.");
                                    arrTyp = null;
                                }else {
                                    arrTyp = ctx.expression().getText();
                                }

                            } else
                                System.out.println("Error, esta tratando de inicializar el array con un tipo <"+ idExist.type +">.");
                        } else System.out.println("Error, el dato que quiere asignar al tamaño del array no existe.");
                    } else System.out.println("Error, no se encuentra <" + point2 + "> en < " + point1 + " >.");
                } else System.out.println("Error, <" + point1 + "> no a sido inicializado.");
            } else System.out.println("Error, está asignando al array de tipo <" + ctx.stype().getText() + "> un dato invalido.");

        } else{
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

        return this.visit(ctx.expression());
    }

    @Override
    public Object visitSubExprAST(miParser.SubExprASTContext ctx) {
        return this.visit(ctx.expression());
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
            if((id.type.substring(id.type.length()-2, id.type.length())).equals("[]")){
                return "int";
            }else if(id.type.equals("string")){
                return "int";
            }
        }
        if(id == null){
            return " \""+ ctx.ID().getText() +"\" no existe. ";
        }

        return  " "+ctx.ID().getText() +" no se le puede hacer un length.";
    }

}
