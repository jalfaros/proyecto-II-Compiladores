package interprete;

import java.util.LinkedList;

public class Almacen {
    LinkedList<Instancia> tablaAlmacen;

    public class Instancia{
        String nombre;
        Object valor;

        public Instancia(String nombre, Object valor) {
            this.nombre = nombre;
            this.valor = valor;
        }
    }

    public Almacen() {
        this.tablaAlmacen = new LinkedList<Instancia>();
    }

    public void agregarInstancia(String nombre, Object valor){
        this.tablaAlmacen.add(new Instancia(nombre, valor));
    }

    //Esto es como un buscar
    public Object getInstancia(String nombr){
        for(Object id: tablaAlmacen)
            if (((Instancia)id).nombre.equals(nombr))
                return (((Instancia)id).valor);
        return null;
    }

    //Esto es para buscar la instancia y setearle un nuevo valor
    public  void  setInstancia(String nombr, Object val){
        for(Object id: tablaAlmacen)
            if (((Instancia)id).nombre.equals(nombr))
                ((Instancia)id).valor  = val;

    }

}
