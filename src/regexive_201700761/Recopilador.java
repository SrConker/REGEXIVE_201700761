/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexive_201700761;

/**
 *
 * @author Elder
 */
public class Recopilador {
    String nombre;
    Object valor;
    
    public Recopilador(String nombre, Object valor){
        this.nombre = nombre;
        this.valor = valor;
    }
    
    @Override
    public String toString(){
        return "Recopilador{"+"nombre="+nombre+", valor="+valor+'}';
    }
}
