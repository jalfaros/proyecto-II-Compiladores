package interprete;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.LinkedList;

public class Almacen {
    LinkedList<Instancia> tablaAlmacen;

    public class Instancia{
        String nombre;
        Object valor;
        ParserRuleContext ctx;
        Object[] arr;

        public Instancia(String nombre, Object valor) {
            this.nombre = nombre;
            this.valor = valor;
            this.ctx = null;
            arr = null;
        }

        public Instancia(String nombre, int size, Object valor) {
            this.nombre = nombre;
            this.valor = null;
            this.ctx = null;
            arr = new Object[size];
        }

        public Instancia(String nombre, Object valor, ParserRuleContext ctx) {
            this.nombre = nombre;
            this.valor = valor;
            this.ctx = ctx;
            arr = null;
        }
    }

    public Almacen() {
        this.tablaAlmacen = new LinkedList<Instancia>();
    }

    public void agregarInstancia(String nombre, Object valor){
        this.tablaAlmacen.add(new Instancia(nombre, valor));
    }

    public void agregarInstancia(String nombre, int size, Object valor){
        this.tablaAlmacen.add(new Instancia(nombre, size, valor));
    }

    public void agregarInstancia(String nombre, Object valor, ParserRuleContext ctx){
        this.tablaAlmacen.add(new Instancia(nombre, valor, ctx));
    }

    //Esto es como un buscar
    public Object getInstancia(String nombr){
        for(Object id: tablaAlmacen)
            if (((Instancia)id).nombre.equals(nombr))
                return (((Instancia)id).valor);
        return null;
    }

    //Obtener valor de un array
    public Object getValueArr(String nombre, int index){
        for(Object id: tablaAlmacen)
            if (((Instancia)id).nombre.equals(nombre))
                return (((Instancia)id).arr[index]);
        return null;
    }

    //Esto es para buscar la instancia y setearle un nuevo valor
    public  void  setInstancia(String nombr, Object val){
        for(Object id: tablaAlmacen)
            if (((Instancia)id).nombre.equals(nombr))
                ((Instancia)id).valor  = val;

    }

    //Este es para setear un valor a un array
    public  void  setValuArr(String nombr,int index, Object val){
        for(Object id: tablaAlmacen)
            if (((Instancia)id).nombre.equals(nombr))
                try {
                    ((Instancia) id).arr[index] = val;
                }catch (Exception e){
                    System.out.println("Error, el index "+index+" está fuera del rango del array.");
                }

    }

}
