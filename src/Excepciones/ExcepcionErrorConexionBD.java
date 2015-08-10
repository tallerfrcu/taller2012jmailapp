/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Clase que representa un error de conexión a la base de datos
 * @author Accornero, Fontana, García, Pascal
 */
public class ExcepcionErrorConexionBD extends Exception {
    /**
     * Constructor de la clase que instancia la excepción con un mensaje de
     * error por defecto y con la excepción original que se atrapó
     * @param excepcionOriginal excepción original que causó el error
     */
    public ExcepcionErrorConexionBD(Exception excepcionOriginal) {
        super("Error de conexión a la base de datos",excepcionOriginal);
    }
    /**
     * Constructor de la clase que instancia la excepción con un mensaje de
     * error que llega como parámetro y con la excepción original que se atrapó
     * @param mensajeError mensaje de error
     * @param excepcionOriginal excepción original que causó el error
     */
    public ExcepcionErrorConexionBD(
            String mensajeError, Exception excepcionOriginal) {
        super(mensajeError, excepcionOriginal);
    }
    /**
     * Constructor de la clase que instancia una excepción con un mensaje de 
     * error
     * @param mensajeError mensaje de error
     */
    public ExcepcionErrorConexionBD(String mensajeError){
        super(mensajeError);
    }
}
