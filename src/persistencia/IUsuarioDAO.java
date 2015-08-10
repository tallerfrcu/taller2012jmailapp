/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Excepciones.ExcepcionErrorConexionBD;
import Excepciones.ExcepcionLogIn;
import java.util.List;
import modelo.CarpetaCuentaCorreo;
import modelo.CuentaDeCorreo;
import modelo.Usuario;

/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public interface IUsuarioDAO {
    /**
     * Definición del método que loguea a un usuario a la aplicación
     * @param nombreUsuario
     * @param contrasenaUsuario
     * @return instancia de {@link modelo.Usuario Usuario} si se logueó 
     * exitosamente
     * @throws ExcepcionLogIn se lanza si no se encuentra el nombre de usuario
     * en la base de datos interna o si no coincide el usuario y la contraseña
     * @throws ExcepcionErrorConexionBD 
     */
    public Usuario logInUsuario(String nombreUsuario, String contrasenaUsuario)
            throws ExcepcionLogIn, ExcepcionErrorConexionBD;
    
    /**
     * Definición dle método que dá de alta un usuario en la base de datos
     * @param nombeUsuario nombe de usuario para loguearse a la aplicación
     * @param contrasenaUsuario contraseña dle usuario para loguearse a la 
     * aplicación
     * @return true si el usuario se creó exitosamente
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * a la base de datos
     */
    public boolean generarUsuario(String nombeUsuario, String contrasenaUsuario)
            throws ExcepcionErrorConexionBD;
    /**
     * Definición del método que valida que un nombre de usuario no se encuentre
     * usado en la base de datos
     * @param nombreUsuario nombre de usuario que se quiere saber si existe en 
     * uso
     * @return true si existe, false en caso contrario
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * a la base de datos local
     */
    public boolean existeUsuario(String nombreUsuario) 
            throws ExcepcionErrorConexionBD;
}
