package analysisContext;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class TablaSimbolClass {
    LinkedList<Object> tabla;

    public int nivelActualClase;

    public TablaSimbolClass() {
        tabla = new LinkedList<Object>();
        this.nivelActualClase = -1;
    }

    public void insertar(String id, String tipo, ParserRuleContext decl,String className){
        //no se puede insertar un elemento repetido en el mismo nivel
        Ident i = new Ident(id,tipo,decl, nivelActualClase, className, null);
        tabla.addFirst(i);
    }

    public Ident buscar(String nombre)
    {
        Ident temp=null;
        for(Object id : tabla)
            if (((Ident)id).tok.equals(nombre))
                return ((Ident)id);
        return temp;
    }

    public Ident buscarClase(String nombre)
    {
        Ident temp=null;
        for(Object nomb : tabla)
            if (((Ident)nomb).className.equals(nombre))
                return ((Ident)nomb);
        return temp;
    }

    public Ident buscarClaseYVar(String clase, String variable)
    {
        Ident temp=null;
        for(Object nomb : tabla)
            if (((Ident)nomb).className.equals(clase) && ((Ident)nomb).tok.equals(variable))
                return ((Ident)nomb);
        return temp;
    }


    public void openScope(){
        nivelActualClase++;
    }

    public void closeScope(){
        tabla.removeIf(n -> (((Ident)n).nivel == nivelActualClase));
        nivelActualClase--;
    }

    public void imprimir() {
        System.out.println("\n----- INICIO TABLA CLASE------ ");
        for (Object o : tabla) {
            String s = ((Ident) o).tok;
            System.out.println("Nombre: " + s + " - " + ((Ident) o).nivel + " - " + ((Ident) o).type + " - clase: "+((Ident) o).className);
/*            if (s.getType() == 0) System.out.println("\tTipo: Indefinido");
            else if (s.getType() == 1) System.out.println("\tTipo: Integer\n");
            else if (s.getType() == 2) System.out.println("\tTipo: String\n");*/
        }
        System.out.println("----- FIN TABLA ------ \n");
    }
}