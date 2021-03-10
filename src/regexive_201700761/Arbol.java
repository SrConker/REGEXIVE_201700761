
package regexive_201700761;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Arbol {
    int max = 0;
    public String nombreExpresion;
    int cantidadNodos;
    int cantidadEstados;
    ArrayList<String> nodoHijos;
    String[][] tabla = new String [100][100];
    ArrayList<ListaSiguiente> siguientes;
    ArrayList <ListaTransiciones> transiciones;
    public ArrayList<String> tokens;
    NodeArbol raiz;
    public String cadenaImprimir;
}
