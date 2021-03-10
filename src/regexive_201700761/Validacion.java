
package regexive_201700761;

public class Validacion {
    private String valor;
    private String expresion;
    private String resultado;
    
    public Validacion(String val, String exp){
        this.valor = val;
        this.expresion = exp;
        this.resultado="";
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
}
