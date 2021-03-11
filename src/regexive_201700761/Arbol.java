
package regexive_201700761;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Arbol {
    int max = 0;
    public String nombreExpresion;
    int cantidadNodos;
    int cantidadEstados;
    ArrayList<String> nodoHijo;
    String[][] tabla = new String [100][100];
    ArrayList<ListaSiguiente> siguientes;
    ArrayList <ListaTransiciones> transiciones;
    public ArrayList<String> tokens;
    NodeArbol raiz;
    public String cadenaImprimir;
    
    public Arbol(){
        raiz = null;
        tokens = new ArrayList<>();
        siguientes = new ArrayList<>();
        transiciones = new ArrayList<>();
        nodoHijo = new ArrayList<>();
        cantidadNodos = 1;
    }
    
    public boolean insertarNodo(Boolean condicion, NodeArbol raiz, NodeArbol nuevo){
        if (raiz.izquierda != null) {
            condicion = insertarNodo(condicion, raiz.izquierda, nuevo);
        }
        if (raiz.derecha != null) {
            condicion = insertarNodo(condicion, raiz.derecha, nuevo);
        }
        if (!condicion) {
            if (raiz.id.equals("*") || raiz.id.equals("?") || raiz.id.equals("+") || raiz.id.equals("|") || raiz.id.equals(".")) {
                if (raiz.izquierda == null && raiz.derecha == null) {
                    raiz.izquierda = nuevo;
                    condicion = true;
                } else if ((raiz.id.equals("+") || raiz.id.equals("?") || raiz.id.equals("*")) && raiz.izquierda == null) {
                   raiz.izquierda = nuevo;
                   condicion = true;
                }else if (raiz.id.equals("|") || raiz.id.equals(".")) {
                    if (raiz.izquierda != null && raiz.derecha == null) {
                        raiz.derecha = nuevo;
                        condicion = true;
                    }
                }
            }
        }
        return condicion;
    }
    
    public void anulables(NodeArbol raiz){
        if (raiz.izquierda != null) {
            anulables(raiz.izquierda);
        }
        if (raiz.derecha != null) {
            anulables(raiz.derecha);
        }
        if (raiz.izquierda == null && raiz.derecha == null) {
            raiz.anulable = false;
        }
        if (raiz.izquierda != null && raiz.derecha != null) {
            if (raiz.id.equals("*") || raiz.id.equals("?")) {
                raiz.anulable = true;
            }
            if (raiz.id.equals("+")) {
                raiz.anulable = raiz.izquierda.anulable;
            }
        }
        if (raiz.izquierda != null && raiz.derecha != null) {
            if (raiz.id.equals("|")) {
                if (raiz.izquierda.anulable || raiz.derecha.anulable) {
                raiz.anulable = true;
                }else{
                    raiz.anulable = false;
                }
            }
            if (raiz.id.equals(".")) {
                if (raiz.izquierda.anulable && raiz.derecha.anulable) {
                    raiz.anulable = true;
                }else{
                    raiz.anulable = false;
                }
            }
        }
    }
    
    public void primeros(NodeArbol raiz){
        if (raiz.izquierda != null) {
            primeros(raiz.izquierda);
        }
        if (raiz.derecha != null) {
            primeros(raiz.derecha);
        }
        if (raiz.izquierda == null && raiz.derecha == null) {
            raiz.primeros.add(cantidadNodos);
            siguientes.add(new ListaSiguiente(raiz.id, cantidadNodos));
            raiz.ID_Hoja = cantidadNodos;
            cantidadNodos++;
            if (raiz.id.equals("#")) {
                max = raiz.ID_Hoja;
            }else{
                nodoHijo.add(raiz.id);
            }
        }
        if (raiz.id.equals("+") || raiz.id.equals("+") || raiz.id.equals("?")) {
            for (int i = 0; i < raiz.izquierda.primeros.size(); i++) {
                raiz.primeros.add(raiz.izquierda.primeros.get(i));
            }
        }
        if (raiz.id.equals("|")) {
            for (int i = 0; i < raiz.izquierda.primeros.size(); i++) {
                raiz.primeros.add(raiz.izquierda.primeros.get(i));
            }
            for (int i = 0; i < raiz.derecha.primeros.size(); i++) {
                raiz.primeros.add(raiz.derecha.primeros.get(i));
            }
        }
        if (raiz.id.equals(".")) {
            if (raiz.izquierda.anulable) {
                for (int i = 0; i < raiz.izquierda.primeros.size(); i++) {
                    raiz.primeros.add(raiz.izquierda.primeros.get(i));
                }
                if (raiz.derecha != null) {
                    for (int i = 0; i < raiz.derecha.primeros.size(); i++) {
                        raiz.primeros.add(raiz.derecha.primeros.get(i));
                    }
                }
            }else{
                for (int i = 0; i < raiz.izquierda.primeros.size(); i++) {
                    raiz.primeros.add(raiz.izquierda.primeros.get(i));
                }
            }
        }
    }
    
    public void ultimos(NodeArbol raiz){
        if (raiz.izquierda != null) {
            ultimos(raiz.izquierda);
        }
        if (raiz.derecha != null) {
            ultimos(raiz.derecha);
        }
        if (raiz.izquierda == null && raiz.derecha == null) {
            raiz.ultimos.add(cantidadNodos);
            cantidadNodos++;
        }
        if (raiz.id.equals("*") || raiz.id.equals("+") || raiz.id.equals("?")) {
            for (int i = 0; i < raiz.izquierda.ultimos.size(); i++) {
                raiz.ultimos.add(raiz.izquierda.ultimos.get(i));
            }
        }
        if (raiz.id.equals("|")) {
            for (int i = 0; i < raiz.izquierda.ultimos.size(); i++) {
                raiz.ultimos.add(raiz.izquierda.ultimos.get(i));
            }
            for (int i = 0; i < raiz.derecha.ultimos.size(); i++) {
                raiz.ultimos.add(raiz.derecha.ultimos.get(i));
            }
        }
        if (raiz.id.equals(".")) {
            if (raiz.derecha.anulable) {
                for (int i = 0; i < raiz.izquierda.ultimos.size(); i++) {
                    raiz.ultimos.add(raiz.izquierda.ultimos.get(i));
                }
                for (int i = 0; i < raiz.derecha.ultimos.size(); i++) {
                    raiz.ultimos.add(raiz.derecha.ultimos.get(i));
                }
            }else{
                for (int i = 0; i < raiz.derecha.ultimos.size(); i++) {
                    raiz.ultimos.add(raiz.derecha.ultimos.get(i));
                }
            }
        }
    }
    
    public void listaSiguientes(NodeArbol raiz){
        if (raiz.izquierda != null) {
            listaSiguientes(raiz.izquierda);
        }
        if (raiz.derecha != null) {
            listaSiguientes(raiz.derecha);
        }
        if (raiz.id.equals(".")) {
            for (int i = 0; i < raiz.izquierda.ultimos.size(); i++) {
                if (raiz.derecha != null) {
                    for (int j = 0; j < raiz.derecha.primeros.size(); j++) {
                        int valor = raiz.izquierda.ultimos.get(i);
                        valor--;
                        siguientes.get(valor).setSiguiente(raiz.derecha.primeros.get(j));
                    }
                }
            }
        }
        if (raiz.id.equals("+") || raiz.id.equals("*")) {
            for (int i = 0; i < raiz.izquierda.ultimos.size(); i++) {
                for (int j = 0; j < raiz.izquierda.primeros.size(); j++) {
                    int valor = raiz.izquierda.ultimos.get(i);
                    siguientes.get(valor-1).setSiguiente(raiz.izquierda.primeros.get(j));
                }
            }
        }
    }
    
    public void insertarToken(String token){
        tokens.add(token);
    }
    
    public void analizarArbol(String nombre) throws IOException {
        this.raiz = new NodeArbol(0, ".");
        this.raiz.derecha = new NodeArbol(1, "#");
        this.raiz.izquierda = new NodeArbol(2, tokens.get(0));
        for (int i = 1; i < tokens.size(); i++) {
            NodeArbol nuevo = new NodeArbol(i+2, tokens.get(i));
            insertarNodo(false, this.raiz, nuevo);
        }
        anulables(this.raiz);
        primeros(this.raiz);
        cantidadNodos = 1;
        ultimos(this.raiz);
        listaSiguientes(this.raiz);
        Collections.sort(nodoHijo);
        tablaEstados();
        cantidadNodos = 1;
        generarReportes(nombre);
    }
    
    public void generarReportes(String nombre) throws IOException{
        graficarArbol(nombre);
        graficarSiguientes(nombre);
        graficarEstados(nombre);
        generarAFD(nombre);
    }
    
    public void generarAFD(String nombre) throws IOException{
        String ruta = "AFD" + nombre + ".dot";
        File archivo = new File(ruta);
        BufferedWriter lectura;
        lectura = new BufferedWriter(new FileWriter(archivo));
        this.cadenaImprimir = "digraph AFD { " + '\n';
        this.cadenaImprimir += "graph [label=\"AFD: " + nombre + "\", labelloc=t, fontsize=20]; ";
        afdDOT();
        this.cadenaImprimir += '\n' + "}";
        
        lectura.write(this.cadenaImprimir);
        lectura.close();
        try{
            String fileInputPath = ruta;
            String fileOutPath = "AFD" + nombre + ".png";
            String tParam = "-Tpng";
            String toParam = "-o";
            String[] cmd = new String[5];
            cmd[0] = Manejador.obtenerInstancia().getDPath();
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = toParam;
            cmd[4] = fileOutPath;
            
            Runtime run = Runtime.getRuntime();
            run.exec(cmd);
        }catch (IOException ex){
            System.out.println(ex);
        }
    }
    
    public void afdDOT(){
        cadenaImprimir += "rankir=LR;";
        cadenaImprimir += "edge [color=black];";
        cadenaImprimir += "node [color = white,style=filled];";
        for (int i = 0; i < transiciones.size(); i++) {
            cadenaImprimir += "\"" + transiciones.get(i).getNombreEstado() + "\"" + "[ label=" + transiciones .get(i).getNombreEstado()+ "]" + '\n';
        }
        cadenaImprimir += "secret_node [style=invis];\n";
        cadenaImprimir += "secret_node -> S0 [label=\"inicio\"];";
        for (int i = 0; i < transiciones.size(); i++) {
            for (int j = 0; j < transiciones.get(i).ID_estado.size(); j++) {
                if (transiciones.get(i).ID_estado.get(j) == max) {
                    cadenaImprimir += transiciones.get(i).getNombreEstado() + "[peripheries=2];\n";
                }
            }
        }
        for (int i = 0; i < transiciones.size(); i++) {
            for (int j = 0; j < nodoHijo.size(); j++) {
                if (tabla[i + 1][j + 1] == null) {
                    //Sin estado de transicion
                }else{
                    //Vamos a quitar errores de graphviz
                    String escape = "";
                    String tem = tabla[0][j + 1].replaceAll("\"", "");
                    String tem2 = "";
                    for (int k = 0; k < tem.length(); k++) {
                        if (tem.charAt(k) == (char)123 || tem.charAt(k) == (char)125) {
                            
                        }else{
                            tem2 += tem.charAt(k);
                        }
                    }
                    escape = "";
                    for (int k = 0; k < tem2.length(); k++) {
                        if (Character.isDigit(tem2.charAt(k)) || Character.isLetter(tem2.charAt(k)) || tem2.charAt(k) == (char)32) {
                            
                        }else{
                            escape = "\\";
                            break;
                        }
                    }
                    cadenaImprimir += "\"" + transiciones.get(i).getNombreEstado() + "\"" + "->\"" + tabla[i+1][j+1] + "\"" + "[label=\"" + escape + tem2 + "\"];" + '\n';
                }
            }
        }
    }
    
    public void graficarEstados (String nombre) {
        String cadenaImp = "<html>" + "<body>" + "<h1 align='center'> Tabla Transiciones: " +nombre+"</h1></br>"+"<table cellpading='10' border = '1' align='center'>"+'\n';
        cadenaImp += " <tr><td><b>Estado</b></td><td><b>Terminales</b></td></tr>"+'\n';
        for (int i = 0; i < cantidadEstados+1; i++) {
            cadenaImp += "<tr>";
            for (int j = 0; j < nodoHijo.size(); j++) {
                if (tabla[j][i] == null) {
                    cadenaImp += "<td><b></b></td>";
                }else{
                    cadenaImp += "<td><b>"+tabla[j][i]+"</b></td>";
                }
            }
            cadenaImp += "</table></body></html>";
            String ruta = "Transiciones"+nombre+".html";
            File archivo = new File(ruta);
            try{
                if (!archivo.exists()) {
                    archivo.createNewFile();
                }
                FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(cadenaImp);
                bw.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void graficarArbol(String nombre) {
        String ruta = "Arbol"+nombre+".dot";
        File archivo = new File(ruta);
        BufferedWriter lectura = null;
        FileWriter fw;
        PrintWriter pw;
        try{
            fw = new FileWriter(ruta);
            pw = new PrintWriter(fw);
            lectura = new BufferedWriter(new FileWriter(archivo));
            this.cadenaImprimir = "digraph ARBOL { " + '\n';
            this.cadenaImprimir+="graph [label=\"Arbol: "+nombre+"\", labelloc=t, fontsize=20]; ";
            this.cadenaImprimir += "rankdir=TB" + '\n';
            this.cadenaImprimir += "node[shape=record,style=filled,color=\"0.619 0.714 0.714\"] " + '\n';
            generarArbolID(raiz);
            this.cadenaImprimir += '\n' + "}";
            lectura.write(this.cadenaImprimir);
            try{
                String fileInputPath = ruta;
                String fileOutPath = "Arbol"+nombre+".png";
                String tParam = "-Tpng";
                String toParam = "-o";
                String[] cmd = new String[5];
                cmd[0] = Manejador.obtenerInstancia().getDPath();
                cmd[1] = tParam;
                cmd[2] = fileInputPath;
                cmd[3] = toParam;
                cmd[4] = fileOutPath;
                Runtime run = Runtime.getRuntime();
                run.exec(cmd);
            }catch (Exception e){
                System.out.println(e);
            }
        }catch (Exception ie){
            ie.printStackTrace();
        }finally{
            try{
                if (null != lectura) {
                    lectura.close();
                }
            }catch (Exception ioe){
                ioe.printStackTrace();
            }
        }
    }
    
    public void generarArbolID(NodeArbol raiz){
        String titulo;
        String escape = "";
        char caracter = raiz.id.charAt(0);
        if(caracter == (char) 34){
            titulo = "Cadena";
        }else if(caracter == (char) 123){
            titulo = "Conjunto";
        }else{
            titulo = "Operador";
        }
        String tem = raiz.id.replaceAll("\"", "");
        String tem2 = "";
        for(int i = 0; i < tem.length(); i++){
            if(tem.charAt(i)==(char)123    ||tem.charAt(i)==(char)125){
            }else{tem2+=tem.charAt(i);}
        }
        escape="";
        for(int i=0;i<tem2.length();i++){
            if(Character.isDigit(tem2.charAt(i)) ||Character.isLetter(tem2.charAt(i)) ||tem2.charAt(i)==(char)32){
            }else{
                escape="\\";
                break;
            }
        }

        String L_Primeros="";
        if (raiz.primeros.size()>0) {
            L_Primeros+=raiz.primeros.get(0);
            for(int i=1;i<raiz.primeros.size();i++){
                L_Primeros+=" ,"+raiz.primeros.get(i);
            }
        }
        
        String L_Ultimos="";
        if (raiz.ultimos.size()>0) {
            L_Ultimos+=raiz.ultimos.get(0);
            for(int i=1;i<raiz.ultimos.size();i++){
                L_Ultimos+=" ,"+raiz.ultimos.get(i);   
            }
        }
        
        
        this.cadenaImprimir += "\"" + raiz.numero  + "\"" + "[label =\"<C0>|P: "+L_Primeros+"|{<C1> Anulable:"+raiz.anulable+"|" +titulo+": "+escape+ tem2 + "}|U: "+L_Ultimos+"|<C2>\"]; \n";
        if(raiz.izquierda !=null ){
            this.generarArbolID(raiz.izquierda);
            this.cadenaImprimir += "\""+ raiz.numero  +"\":C0->"+"\""+raiz.izquierda.numero+"\"; \n";
        }        
        if(raiz.derecha !=null ){
            this.generarArbolID(raiz.derecha);
            this.cadenaImprimir += "\""+ raiz.numero  +"\":C2->"+"\""+raiz.derecha.numero+"\"; \n";
        }
    }
    
    public void graficarSiguientes(String nombre) {
        String cadenaImp="<html>"+ "<body>"+ "<h1 align='center'> Tabla Siguientes: "+nombre+"</h1></br>"+ "<table cellpadding='10' border = '1' align='center'>"+'\n';

        cadenaImp += " <tr><td><b>Nombre de Hoja</b></td><td><b>ID de Hoja</b></td><td><b>Siguientes</b></td></tr>"+'\n';
        for(int i=0;i<siguientes.size();i++){
            String sig = "";
            for(int x=0;x<siguientes.get(i).siguientes.size();x++){
                if(x==0){
                    sig+=siguientes.get(i).siguientes.get(x);
                }else{
                    sig+=","+siguientes.get(i).siguientes.get(x);
                }
            }
            cadenaImp+="<tr><td>"+siguientes.get(i).getNombreNodo()+"</td>"+"<td>"+siguientes.get(i).getNumeroNodo()+"</td>"+"<td>"+sig+"</tr>"+'\n';
        }

        cadenaImp+="</table></body></html>";
        
        String ruta = "Siguientes"+nombre+".html";
        File archivo = new File(ruta);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            FileWriter Fw = new FileWriter(archivo);
            BufferedWriter Bw = new BufferedWriter(Fw);
            Bw.write(cadenaImp);
            Bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void tablaEstados(){
        int fila=1;
        Set<String>hashSet2= new HashSet<String>(nodoHijo);
        nodoHijo.clear();
        nodoHijo.addAll(hashSet2);
        tabla[0][0]=" ";
        for(int i=0;i<nodoHijo.size();i++){
            tabla[0][i+1]=nodoHijo.get(i);
        }
        
        String s="";
        s+="S"+cantidadEstados+"{";
        ListaTransiciones nuevo=new ListaTransiciones("S"+cantidadEstados);
        for(int x=0;x<raiz.primeros.size();x++){
            nuevo.setID_estado(raiz.primeros.get(x));
            if(x==0){
                s+=raiz.primeros.get(x);
            }else{
                s+=","+raiz.primeros.get(x);
            }   
        }
        Collections.sort(nuevo.ID_estado);
        s+="}";
        tabla[1][0]=s;
        transiciones.add(nuevo);
        cantidadEstados++;
        
        int y=1;
        while(y!=0){
            y--;
            for(int x=0;x<transiciones.size();x++){
                for(int x2=0;x2<transiciones.get(x).ID_estado.size();x2++){
                    ListaTransiciones nuevo2=new ListaTransiciones("S"+cantidadEstados);                    
                    int posicionPrimeros=transiciones.get(x).ID_estado.get(x2);
                    String Conc="";
                    Conc+="S"+cantidadEstados+"{";
                    for(int x3=0;x3<siguientes.get(posicionPrimeros-1).siguientes.size();x3++){
                        nuevo2.setID_estado(siguientes.get(posicionPrimeros-1).siguientes.get(x3));
                        if(x3==0){
                            Conc+=siguientes.get(posicionPrimeros-1).siguientes.get(x3);
                        }else{
                            Conc+=","+siguientes.get(posicionPrimeros-1).siguientes.get(x3);
                        }
                    }
                    Set<Integer> hashSet = new HashSet<Integer>(nuevo2.ID_estado);
                    nuevo2.ID_estado.clear();
                    nuevo2.ID_estado.addAll(hashSet);
                    Collections.sort(nuevo2.ID_estado);
                    boolean repetido=false;
                    int posicionrepetido=0;
                    for(int i=0;i<transiciones.size();i++){
                       repetido = transiciones.get(i).ID_estado.equals(nuevo2.ID_estado);
                       if(repetido){
                           posicionrepetido=i;
                           break;
                       }
                    }
                    if(repetido){
                        for(int i=0;i<nodoHijo.size();i++){
                            if(nodoHijo.get(i).equals(siguientes.get(posicionPrimeros-1).getNombreNodo())){
                                tabla[x+1][i+1]="S"+posicionrepetido;
                            }
                        }
                    }else{
                        if(nuevo2.ID_estado.size()==0){
                        }else{
                            tabla[x+2][0]=Conc+"}";
                            for(int i=0;i<nodoHijo.size();i++){
                                if(nodoHijo.get(i).equals(siguientes.get(posicionPrimeros-1).getNombreNodo())){
                                    tabla[x+1][i+1]="S"+cantidadEstados;
                                }
                            }
                            y++;
                            transiciones.add(nuevo2);
                            cantidadEstados++;   
                        }     
                    }
                }
            }   
        }
    }
}
