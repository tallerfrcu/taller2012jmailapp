/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 * Clase que representa un usuario de la aplicación.
 * @author Accornero, Fontana, García, Pascal
 */
public class Usuario {

    /**
     * Variable de instancia que representa el nombre de usuario del Usuario. Se
     * utiliza para identificarse unívocamente en la aplicación.
     */
    private String nombreDeUsuario;
    /**
     * Variable de instancia que representa la contraseña del Usuario. Se
     * utiliza para autenticarse en la aplicación.
     */
    private String contrasenaUsuario;
    /**
     * lista de correos del usuario.
     */
    private List<CuentaDeCorreo> cuentasCorreo;

    /**
     * Constructor de la clase que instacia un Usuario inicializando sus
     * el nombre de usuario y la constraseña
     * @param nombreDeUsuario nombre de usuario del usuario
     * @param contrasenaUsuario contraseña del usuario
     */
    public Usuario(String nombreDeUsuario, String contrasenaUsuario, 
            List<CuentaDeCorreo> cuentasCorreo) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
    }

    /**
     * Constructor de la clase que instancia un Usuario sin inicializar
     * sus variables de instancia.
     */
    public Usuario() {
    }

    /**
     *
     * @param nombreDeUsuario
     */
    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    /**
     *
     * @param contrasenaUsuario
     */
    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public void setCuentasCorreo(List<CuentaDeCorreo> cuentasCorreo) {
        this.cuentasCorreo = cuentasCorreo;
    }

    /**
     *
     * @return
     */
    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    /**
     *
     * @return
     */
    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    /**
     *
     * @return
     */
    public List<CuentaDeCorreo> getCuentasCorreo() {
        return cuentasCorreo;
    }

}
