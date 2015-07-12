/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class ExcepcionTratamientoCorreo extends Exception {
    /**
     * 
     * @param excepcionOriginal 
     */
    public ExcepcionTratamientoCorreo(Exception excepcionOriginal){
        super(excepcionOriginal);
    }
    /**
     * 
     * @param mensaje
     * @param excepcionOriginal 
     */
    public ExcepcionTratamientoCorreo(
            String mensaje, Exception excepcionOriginal){
        super(mensaje, excepcionOriginal);
    }
}
