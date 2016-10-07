/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formidesktop;

/**
 *
 * @author azaraf
 */
public class Counter {
    
    private int contador;
    
    public Counter(){
        this.contador = 0;
    }
    
    public void incrementar(){
        this.contador++;
    }
    
    public void decrementar(){
        this.contador--;
    }
    
    public int obtenerCuenta(){
        return contador;
    }
    
    public void setCuenta(int contador){
        this.contador = contador;
    }
}
