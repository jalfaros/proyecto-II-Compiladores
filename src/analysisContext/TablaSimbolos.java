package analysisContext;

import org.antlr.v4.runtime.*;

import java.util.LinkedList;

public class TablaSimbolos {
    LinkedList<Object> tabla;

    private  int nivelActual;


    public TablaSimbolos(){
        tabla = new LinkedList<Object>();
        this.nivelActual = -1;
    }

    public void insertar(Token id, String tipo, ParserRuleContext decl){
        //no se puede insertar un elemento repetido en el mismo nivel
        Ident i = new Ident(id,tipo,decl, nivelActual);
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

    public void imprimir() {
        System.out.println("----- INICIO TABLA ------");
        for (int i = 0; i < tabla.size(); i++) {
            Token s = (Token) ((Ident) tabla.get(i)).tok;
            System.out.println("Nombre: " + s.getText() + " - " + ((Ident) tabla.get(i)).nivel + " - " + ((Ident) tabla.get(i)).type);
/*            if (s.getType() == 0) System.out.println("\tTipo: Indefinido");
            else if (s.getType() == 1) System.out.println("\tTipo: Integer\n");
            else if (s.getType() == 2) System.out.println("\tTipo: String\n");*/
        }
        System.out.println("----- FIN TABLA ------");
    }
}
