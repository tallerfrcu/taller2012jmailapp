/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Clase que representa una excepción que puede ocurrir al intentar enviar o
 * recibir correos
 * @author Accornero, Fontana, García, Pascal
 */
public class ExcepcionDeServiciosCorreo extends Exception {
    /**
     * Constructor de la clase que instancia una excepción 
     */
    public ExcepcionDeServiciosCorreo(){
        super();
    }
    /**
     * Constructor de la clase que instancia una excepción de servicio de correo
     * con un mensaje de error y la excepción original que provocó el error
     * @param mensajeError mensaje de error
     * @param exepcionOriginal excepción original
     */
    public ExcepcionDeServiciosCorreo(String mensajeError, 
            Exception exepcionOriginal) {
        super(mensajeError, exepcionOriginal);
    }
}
