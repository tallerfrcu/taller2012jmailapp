/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import persistencia.postgres.PostgresDAOFactory;

/**
 * Clase abstracta que define las instancias del DAO Factory y los métodos
 * que se deben implementar para conectarse a una base de datos cualquiera.
 * @author Accornero, Fontana, García, Pascal
 */
public abstract class  DAOFactory {
    /**
     * instancia única en toda la ejecución del programa de la implementación
     * de la clase <code>DAOFactory</code>
     */
    private static DAOFactory FACTORY = null;
    /**
     * método de clase que devuelve la instancia del factory de la base de 
     * datos para conectarse, desconectarse, consultar y actualizar datos.
     * @return variable de clase que contiene el acceso a la base de datos.
     */
    public static DAOFactory getDAOFactory(){
        if(DAOFactory.FACTORY == null) {
            return new PostgresDAOFactory();
        } else {
            return DAOFactory.FACTORY;
        }
    }
    /**
     * definición del método que inicia la conexión a la base de datos
     * @throws Excepciones.ExcepcionErrorConexionBD excepción que se lanza
     * si ocurre un error de conexión
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado
     */
    public abstract void iniciarConexion() throws ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado;
    /**
     * definición del método que cierra la conexión a la base de datos.
     * @throws Excepciones.ExcepcionErrorConexionBD Excepción que se lanza
     * si ocurre un error al desconectarse a la base de datos
     */
    public abstract void cerrarConexion() throws ExcepcionErrorConexionBD;
    /**
     * 
     * @return
     * @throws ExcepcionErrorConexionBD 
     */
    public abstract IServicioCorreoDAO getServicioCorreo()
            throws ExcepcionErrorConexionBD;
    /**
     * 
     * @return 
     */
    public abstract ICorreoDAO getCorreoDAO();
    /**
     * definición del método que devuelve una instancia de UsuarioDAO
     * @return 
     */
    public abstract IUsuarioDAO getUsuarioDAO();
}
