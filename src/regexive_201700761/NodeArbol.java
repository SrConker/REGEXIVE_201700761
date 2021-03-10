
package regexive_201700761;

import java.util.ArrayList;

public class NodeArbol {
    public int numero;
    public int ID_Hoja;
    public String id;
    public boolean anulable;
    public ArrayList<Integer> primeros;
    public ArrayList<Integer> ultimos;
    public NodeArbol izquierda;
    public NodeArbol derecha;
    
    public NodeArbol(int numero, String id){
        this.numero = numero;
        this.id = id;
        primeros = new ArrayList<>();
        ultimos = new ArrayList<>();
    }
}
