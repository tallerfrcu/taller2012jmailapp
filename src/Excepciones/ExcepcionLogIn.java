/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Clase que representa una excepción que se lanza cuando hay un error de 
 * logueo a la aplicación
 * @author Accornero, Fontana, García, Pascal
 */
public class ExcepcionLogIn extends Exception {
    /**
     * Constructor que isntancia una excepción de logueo con un mensaje de 
     * error por defecto
     */
    public ExcepcionLogIn() {
        super("No coinciden el usuario y la contraseña");
    }
    /**
     * Constructor que instancia una excepción de logueo con un mensaje de 
     * error y la excepción original
     * @param mensaje mensaje de error
     * @param ex excepción original
     */
    public ExcepcionLogIn(String mensaje, Exception ex) {
        super(mensaje, ex);
    }
    /**
     * Constructor de la clase que isntancia una excepción de logueo, con un
     * mensaje de error pasado por parámetro.
     * @param mensaje mensaje de error con el que se quiere instanciar la 
     * excepción
     */
    public ExcepcionLogIn(String mensaje) {
        super(mensaje);
    }
}
