
package regexive_201700761;

import java.util.ArrayList;

public class ListaSiguiente {
    private String nombreNodo;
    private int numeroNodo;
    public ArrayList<Integer> siguientes;
    
    public ListaSiguiente(String nombreNodo, int numeroNodo){
        this.nombreNodo = nombreNodo;
        this.numeroNodo = numeroNodo;
        siguientes = new ArrayList<>();
    }

    public String getNombreNodo() {
        return nombreNodo;
    }

    public int getNumeroNodo() {
        return numeroNodo;
    }

    public void setSiguiente(int siguiente) {
        siguientes.add(siguiente);
    }
}
