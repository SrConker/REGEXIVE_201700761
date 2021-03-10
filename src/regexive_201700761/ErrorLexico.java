
package regexive_201700761;


public class ErrorLexico {
    public String tipo;
    public String descripcion;
    public int linea;
    public int columna;
    
    public ErrorLexico(String tipo, String desc, int linea, int columna){
        this.tipo = tipo;
        this.descripcion = desc;
        this.linea = linea;
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}
