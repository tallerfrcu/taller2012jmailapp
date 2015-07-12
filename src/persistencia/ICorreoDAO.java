/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Excepciones.ExcepcionErrorConexionBD;
import java.sql.Timestamp;
import java.util.List;
import modelo.CarpetaCuentaCorreo;
import modelo.CuentaDeCorreo;
import modelo.Envio;
import modelo.Mail;
import modelo.Recepcion;

/**
 * Interfaz que define los métodos para llevar a cabo las operaciones que 
 * se hacen con los correos en la base de datos
 * @author Accornero, Fontana, García, Pascal
 */
public interface ICorreoDAO {
    /**
     * Definición del método para guardar correos en la base de datos
     * @param listaMails lista de mails obtenidos del servicio de correo y 
     * que se guardarán en la base de datos.
     * @return true en caso de que exista al menos un mail nuevo, false en caso
     * que no exista ningún mail nuevo
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * a la base de datos
     */
    public boolean guardarCorreosRecibidos(List<Recepcion> listaMails) 
            throws ExcepcionErrorConexionBD;
    /**
     * 
     * @param cuenta
     * @return
     * @throws ExcepcionErrorConexionBD 
     */
    public List<CarpetaCuentaCorreo> getCarpetas(CuentaDeCorreo cuenta)
            throws ExcepcionErrorConexionBD;
    /**
     * Definición del método que obtiene una lista de mails pertenecientes a 
     * una carpeta pasada por parámetro
     * @param carpeta instancia de {@link modelo.CarpetaCuentaCorreo 
     * CarpetaCuentaCorreo} de la que se quiere recuperar los mails
     * @return lista de {@link modelo.Mail Mails}
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión 
     * a la base de datos
     */
    public List<Mail> getMails(CarpetaCuentaCorreo carpeta) 
            throws ExcepcionErrorConexionBD;
    /**
     * Definición del método qeu guarda en la base de datos un correo a enviar.
     * Se guarda automátimente como no enviado
     * @param mailEnvio instancia de {@link modelo.Envio Envio}
     * @return
     * @throws ExcepcionErrorConexionBD 
     */
    public boolean guardarCorreoEnvio(Envio mailEnvio) 
            throws ExcepcionErrorConexionBD;
    /**
     * Método que devuelve el id de un mail a partir del destino u origen y 
     * la fecha
     * @param cuenta destino u origen
     * @param fecha fecha dle mail
     * @return id del correo
     * @throws ExcepcionErrorConexionBD se lanza si o curre un error de 
     * conexión a la base de datos local
     */
    public int getIdCorreo(String cuenta, Timestamp fecha) 
            throws ExcepcionErrorConexionBD;
    /**
     * 
     * @param idMail
     * @return
     * @throws ExcepcionErrorConexionBD 
     */
    public Mail getCorreo(int idMail) throws ExcepcionErrorConexionBD;
}

