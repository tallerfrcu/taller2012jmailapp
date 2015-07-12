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
     * 
     * @param nombreUsuario
     * @param contrasenaUsuario
     * @return
     * @throws ExcepcionLogIn
     * @throws ExcepcionErrorConexionBD 
     */
    public Usuario logInUsuario(String nombreUsuario, String contrasenaUsuario)
            throws ExcepcionLogIn, ExcepcionErrorConexionBD;
    
}
