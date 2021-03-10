
package regexive_201700761;

import analizadores.Sintactico;
import java.io.IOException;
import java.util.ArrayList;

public class Manejador {
    private static Manejador instancia;
    private analizadores.Sintactico parse;
    private ArrayList<Recopilador> expresiones;
    private ArrayList<Recopilador> analizar;
    private ArrayList<Recopilador> conjuntos;
    private ArrayList<Validacion> validacionJson;
    
    public ArrayList<Validacion> getValidacionJson(){
        return validacionJson;
    }
    
    public void setValidacionJson(ArrayList<Validacion> validacionesJson){
        this.validacionJson = validacionesJson;
    }
    
    public ArrayList<Recopilador> getExpresiones(){
        return expresiones;
    }
    
    public void setExpresiones(ArrayList<Recopilador> expresiones){
        this.expresiones = expresiones;
    }
    
    public ArrayList<Recopilador> getAnalisis(){
        return analizar;
    }

    public void setAnalisis(ArrayList<Recopilador> analizar){
        this.analizar = analizar;
    }
    
    public ArrayList<Recopilador> getConjuntos(){
        return conjuntos;
    }
    
    public void setConjuntos(ArrayList<Recopilador> conjunto){
        this.conjuntos = conjunto;
    }
    
    private ArrayList<ErrorLexico> erroresLexicos;
    private String rutaArbol;
    private String rutaAFD;
    private String rutaAFN;
    private String rutaSiguientes;
    private String rutaTransiciones;
    private String rutaErrores;
    private String rutaSalidas;
    private String dotPATH;
    
    public String getDPath(){
        return dotPATH;
    }
    
    public ArrayList<ErrorLexico> getErrores(){
        return erroresLexicos;
    }
    
    public void setErrores(ArrayList<ErrorLexico> error){
        this.erroresLexicos = error;
    }
    
    public Sintactico getParse(){
        return parse;
    }
    
    public void setParse(Sintactico parse){
        this.parse = parse;
    }
    
    private static synchronized void instanciar(){
        if (instancia == null) {
            instancia = new Manejador();
        }
    }
    
    public static Manejador obtenerInstancia(){
        instanciar();
        return instancia;
    }
    
    public String getRutaArbol(){
        return rutaArbol;
    }
    
    public String rutaAFD(){
        return rutaAFD;
    }
    
    public String getRutaAFN(){
        return rutaAFN;
    }
    
    public String getRutaSiguientes(){
        return rutaSiguientes;
    }
    
    public String getRutaTransiciones(){
        return rutaTransiciones;
    }
    
    public String getRutaErrores(){
        return rutaErrores;
    }
    
    public String getRutaSalidas(){
        return rutaSalidas;
    }
    
    public Manejador(){
        erroresLexicos = new ArrayList<>();
        rutaArbol = "\\ARBOLES_201700761\\";
        rutaAFD = "\\AFD_201700761\\";
        rutaAFN = "\\AFND_201700761\\";
        rutaSiguientes = "\\SIGUIENTES_201700761\\";
        rutaTransiciones = "\\TRANSICIONES_201700761\\";
        rutaErrores = "\\ERRORES_201700761\\";
        rutaSalidas = "\\SALIDAS_201700761\\";
        expresiones = new ArrayList<>();
        conjuntos = new ArrayList<>();
        analizar = new ArrayList<>();
        dotPATH = "D:\\Graphviz\\bin\\dot.exe";
        validacionJson = new ArrayList<>();
    }
    
    public void procesoArbolito() throws IOException{
        for (Recopilador expresion:expresiones) {
            String expr = (String)expresion.valor;
            String[] datos = expr.split(",");
            
        }
    }
}
