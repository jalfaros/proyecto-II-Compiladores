package analysisContext;

import interprete.Almacen;
import org.antlr.v4.runtime.*;

import java.util.LinkedList;
import java.util.List;

public class TablaSimbolos {

    private static TablaSimbolos inst = null;

    LinkedList<Object> tabla;
    public int nivelActual = 0;


    private TablaSimbolos(){
        tabla = new LinkedList<Object>();
        //this.nivelActual = -1;
    }

    public static TablaSimbolos getInstance(){
        if( inst == null ){
            inst = new TablaSimbolos();
        }
        return inst;
    }

    public void insertar(Token id, String tipo, ParserRuleContext decl){
        //no se puede insertar un elemento repetido en el mismo nivel
        Ident i = new Ident(id,tipo,decl, nivelActual, "", null );
        tabla.addFirst(i);
    }

    public void agregarParams(Token id, String tipo, ParserRuleContext decl, List<Ident.Params> params){
        //no se puede insertar un elemento repetido en el mismo nivel
        Ident i = new Ident(id,tipo,decl, nivelActual, "", params );
        tabla.addFirst(i);
    }

    public Ident buscar(String nombre)
    {
        Ident temp=null;
        for(Object id : tabla)
            if (((Ident)id).tok.getText().equals(nombre))
                return ((Ident)id);
        return temp;
    }

    public void openScope(){
        nivelActual++;
    }

    public void closeScope(){
        tabla.removeIf(n -> (((Ident)n).nivel == nivelActual));
        nivelActual--;
    }
    public  void  borrar(String nombr){
        for(Object id: tabla)
            if (((Ident)id).tok.getText().equals(nombr)) {
                tabla.remove(id);
                return;
            }

    }

    public void imprimir() {
        System.out.println("\n----- INICIO TABLA ------");
        for (Object o : tabla) {
            Token s = (Token) ((Ident) o).tok;
            System.out.println("Nombre: " + s.getText() + " - " + ((Ident) o).nivel + " - " + ((Ident) o).type);
/*            if (s.getType() == 0) System.out.println("\tTipo: Indefinido");
            else if (s.getType() == 1) System.out.println("\tTipo: Integer\n");
            else if (s.getType() == 2) System.out.println("\tTipo: String\n");*/
        }
        System.out.println("----- FIN TABLA ------\n");
    }
}
