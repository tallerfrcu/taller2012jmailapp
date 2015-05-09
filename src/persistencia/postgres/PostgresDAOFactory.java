/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.postgres;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import Recursos.utilidades.DatosDeSesion;
import Recursos.utilidades.PoolDeConexiones;
import java.sql.Connection;
import java.sql.SQLException;
import persistencia.DAOFactory;
import persistencia.ICorreos;
import persistencia.IServicioCorreo;


/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class PostgresDAOFactory extends DAOFactory {
    /**
     * instancia de conexión a la base de datos
     */
    private Connection conexion;
    /**
     * método que inicia la conexión a la base de datos
     * @throws Excepciones.ExcepcionErrorConexionBD excepción que se lanza si 
     * ocurre un error de conexión
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado Excepción 
     * que se lanza si ocurre un error al abrir el archivo de propiedades
     */
    @Override
    public void iniciarConexion() throws ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado {
        try {
            //se asigna el driver
            PoolDeConexiones pool;
            //pool = new PoolDeConexiones();
            
            pool = DatosDeSesion.getDatosDeSesion().getPoolDeConexiones();

            Class.forName(pool.getDriver());
            //se asigna la conexión
            this.conexion = pool.getDataSource().getConnection();
        } catch (ClassNotFoundException ex) {
            throw new ExcepcionErrorConexionBD("Error al iniciar el driver de"
                    + " conexión a la base de datos", ex);
            
        } catch (SQLException ex) {
            throw new ExcepcionErrorConexionBD("Error al iniciar la conexión "
                    + "a la base de datos ",ex );
        }
    }
    /**
     * Método que cierra la conexión a la base de datos
     * @throws Excepciones.ExcepcionErrorConexionBD Excepción que se lanza
     * si ocurre un error al desconectarse a la base de datos
     */
    @Override
    public void cerrarConexion() throws ExcepcionErrorConexionBD {
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            throw new ExcepcionErrorConexionBD("Error al cerrar la conexión "
                    + "a la base de datos ",ex );
        }
    }
    /**
     * 
     * @return
     * @throws ExcepcionErrorConexionBD 
     */
    @Override
    public IServicioCorreo getServicioCorreo() throws ExcepcionErrorConexionBD {
        return new PostgresServicioCorreo(this.conexion);
    }
    /**
     * 
     * @return 
     */
    @Override
    public ICorreos getCorreoDAO() {
        return new CorreosDAO(this.conexion);
    }
    
}
