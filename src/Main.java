import analysisContext.AnalisisContextual;
import generated.*;
import interprete.Almacen;
import interprete.Interprete;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Main {

    public static void main(String[] args) {
        miScanner inst = null;
        miParser parser = null;
        ParseTree tree=null;
        CharStream input=null;
        CommonTokenStream tokens = null;
        try {
            input = CharStreams.fromFileName("test.txt");
            inst = new miScanner(input);
            tokens = new CommonTokenStream(inst);
            parser = new miParser(tokens);
            tree = parser.program();
            //PrettyPrintAST pp = new PrettyPrintAST();
            AnalisisContextual ac = new AnalisisContextual();
            Interprete inter = new Interprete();

            ac.visit(tree);
            //inter.visit(tree);
            if (ac.errors  == (null)){


                //Solo si no hay errores que me haga el interprete
                inter.visit(tree);
                System.out.println("\nCompilación sin errores!");

            }else {
                System.out.println(ac.errors.replace("null",""));

            }

            //ac.visit(tree);
            //MiParserMANUAL parse2 = new MiParserMANUAL(inst);
            //parse2.parseProgram();
            System.out.println("Compilación Terminada!!\n");
            //java.util.concurrent.Future<JFrame> treeGUI = org.antlr.v4.gui.Trees.inspect(tree, parser);
            //treeGUI.get().setVisible(true);
        }/*
        catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
            e.printStackTrace();
        }
    }

}