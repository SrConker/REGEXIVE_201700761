
package regexive_201700761;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

class Transiciones {
    public String transicion;
    public String estado;
    
    public Transiciones(String transicion, String estado){
        this.transicion = transicion;
        this.estado = estado;
    }
}

public class ListaTransiciones {
    private String nombreEstado;
    public ArrayList<Integer> ID_estado;
    public ArrayList<Transiciones> transiciones;
    
    public ListaTransiciones(String nombreEstado){
        this.nombreEstado = nombreEstado;
        ID_estado = new ArrayList();
        transiciones = new ArrayList();
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public void setID_estado(int posicion) {
        this.ID_estado.add(posicion);
    }
    
    public void setTransicion(String encabezado, String nuevoEstado){
        transiciones.add(new Transiciones(encabezado, nuevoEstado));
        Collections.sort(transiciones, new Comparator(){
            public int compare(Transiciones t1, Transiciones t2){
                return new String(t1.transicion).compareTo(new String(t2.transicion));
            }
            @Override
            public int compare(Object objeto1, Object objeto2){
                throw new UnsupportedOperationException("No soportado aún.");
            }
        });
    }
}
