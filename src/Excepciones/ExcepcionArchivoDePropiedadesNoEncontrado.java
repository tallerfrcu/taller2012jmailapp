/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Clase que representa una excepción de archivo de propiedes no encontrado.
 * Se utiliza cuando no se halla el archivo .properites correspondiente que
 * contiene los datos de conexión a la base de datos.
 * @author Accornero, Fontana, García, Pascal
 */
public class ExcepcionArchivoDePropiedadesNoEncontrado extends Exception {
    /**
     * Constructor que instanacia la excepción con un mensaje de error por 
     * defecto y la excepción original lanzada por la aplicación
     * @param excepcionOriginal excecpón original que se catchea y se pasa por
     * parámetro
     */
    public ExcepcionArchivoDePropiedadesNoEncontrado(
            Exception excepcionOriginal){
        super("Error al abrir el archivo de propiedades\n Contáctese con "
                + "el servicio técnico",excepcionOriginal);
    }
    /**
     * Constructor que instancia la excepción con un mensaje de error pasado
     * por parámetro. Se utiliza para especificar mejor el error que ocurre.
     * También inicializa el error original
     * @param excepcionOriginal instancia del error original que ocurrió
     * @param mensajeError mensaje de error
     */
    public ExcepcionArchivoDePropiedadesNoEncontrado(
            Exception excepcionOriginal, String mensajeError) {
        super(mensajeError, excepcionOriginal);
    }
}
