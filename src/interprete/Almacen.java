package interprete;

import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.ST;

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
            this.arr = null;
        }

        public Instancia(String nombre, int size, Object valor) {
            this.nombre = nombre;
            this.valor = null;
            this.ctx = null;
            this.arr = new Object[size];
        }

        public Instancia(String nombre, Object valor, ParserRuleContext ctx) {
            this.nombre = nombre;
            this.valor = valor;
            this.ctx = ctx;
            this.arr = null;
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
    public Instancia getInstancia(String nombr){
        for(Instancia id: tablaAlmacen)
            if (id.nombre.equals(nombr))
                return id;
        return null;
    }

    //Obtener valor de un array
    public Object getValueArr(String nombre, int index){
        for(Object id: tablaAlmacen)
            if (((Instancia)id).nombre.equals(nombre))
                try {
                    return (((Instancia)id).arr[index]);
                }catch (Exception e){
                    System.out.println("Error, el index "+index+" está fuera del rango del array.");
                }

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

    //Metodo para instanciar un arreglo
    public  void  seInsArr(String nombr,int size){
        for(Object id: tablaAlmacen)
            if (((Instancia)id).nombre.equals(nombr))
                    ((Instancia) id).arr = new Object[size];

    }

}
