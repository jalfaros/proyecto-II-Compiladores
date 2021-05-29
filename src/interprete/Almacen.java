package interprete;


import org.antlr.v4.runtime.ParserRuleContext;

import java.util.LinkedList;

public class Almacen {
    LinkedList<Instancia> tablaAlmacen;
    public int nivelActual = 0;

    public static class Instancia{
        String nombre;
        Object valor;
        int nivel;
        ParserRuleContext ctx;

        public Instancia(String nombre, Object valor, int nivel) {
            this.nombre = nombre;
            this.valor = valor;
            this.ctx = null;
            this.nivel = nivel;
        }

        public Instancia(String nombre, Object valor, int nivel, ParserRuleContext ctx) {
            this.nombre = nombre;
            this.valor = valor;
            this.ctx = ctx;
        }
    }

    public Almacen() {
        this.tablaAlmacen = new LinkedList<Instancia>();
    }

    public void agregarInstancia(String nombre, Object valor){
        this.tablaAlmacen.addFirst(new Instancia(nombre, valor, nivelActual));

    }

    public void agregarInstancia(String nombre, Object valor, ParserRuleContext ctx){
        this.tablaAlmacen.add(new Instancia(nombre, valor,nivelActual, ctx));
    }

    //Esto es como un buscar
    public Instancia getInstancia(String nombr){
        for(Instancia id: tablaAlmacen){
            //System.out.println(id.nombre);
            if (id.nombre.equals(nombr))
                return id;
        }
        return null;
    }

    //Esto es para buscar la instancia y setearle un nuevo valor
    public  void  setInstancia(String nombr, Object val){
        for(Object id: tablaAlmacen)
            if (((Instancia)id).nombre.equals(nombr)) {
                ((Instancia) id).valor = val;
                return;
            }

    }

    public void openScope(){
        nivelActual++;
    }

    public void closeScope(){
        System.out.println();
        tablaAlmacen.removeIf(n -> (((Instancia)n).nivel == nivelActual));
        nivelActual--;
    }

    public void  imprimirNivel(){
        System.out.println(nivelActual + "<- el nivel actual");
    }



}
