/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Excepciones.ExcepcionErrorConexionBD;
import java.util.List;
import modelo.Recepcion;

/**
 * Interfaz que define los métodos para llevar a cabo las operaciones que 
 * se hacen con los correos en la base de datos
 * @author Accornero, Fontana, García, Pascal
 */
public interface ICorreos {
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
}
