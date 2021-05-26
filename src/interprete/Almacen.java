package interprete;

import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.ST;

import java.util.LinkedList;

public class Almacen {
    LinkedList<Instancia> tablaAlmacen;

    public static class Instancia{
        String nombre;
        Object valor;
        ParserRuleContext ctx;

        public Instancia(String nombre, Object valor) {
            this.nombre = nombre;
            this.valor = valor;
            this.ctx = null;
        }

        public Instancia(String nombre, Object valor, ParserRuleContext ctx) {
            this.nombre = nombre;
            this.valor = valor;
            this.ctx = ctx;
        }
    }

    public Almacen() {
        this.tablaAlmacen = new LinkedList<Instancia>();
    }

    public void agregarInstancia(String nombre, Object valor){
        this.tablaAlmacen.add(new Instancia(nombre, valor));
    }

    public void agregarInstancia(String nombre, Object valor, ParserRuleContext ctx){
        this.tablaAlmacen.add(new Instancia(nombre, valor, ctx));
    }

    //Esto es como un buscar
    public Instancia getInstancia(String nombr){
        for(Instancia id: tablaAlmacen)
            if (id.nombre.equals(nombr))
                return id;
        return null;
    }

    //Esto es para buscar la instancia y setearle un nuevo valor
    public  void  setInstancia(String nombr, Object val){
        for(Object id: tablaAlmacen)
            if (((Instancia)id).nombre.equals(nombr))
                ((Instancia)id).valor  = val;

    }


}
