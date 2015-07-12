/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Clase que representa una excepción al sincronizar la cuenta de correo, ya 
 * sea al enviar o recibir
 * @author Accornero, Fontana, García, Pascal
 */
public class ExcepcionSincronizacion extends Exception {
    /**
     * Constructor de la clase que instancia una excepción con un mensaje
     * de error y la excepción original que causó el error
     * @param msj mensaje de error
     * @param ex excepción original
     */
    public ExcepcionSincronizacion(String msj, Exception ex){
        super(msj, ex);
    }
}
