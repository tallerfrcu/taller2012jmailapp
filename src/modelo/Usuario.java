/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un usuario de la aplicación.
 *
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
     * Constructor de la clase que instacia un Usuario asignando el
     * nombre de usuario y la constraseña
     *
     * @param nombreDeUsuario nombre de usuario del usuario
     * @param contrasenaUsuario contraseña del usuario
     */
    public Usuario(String nombreDeUsuario, String contrasenaUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
    }

    /**
     * Constructor de la clase que instancia un Usuario instanciado la lista de
     * correos, pero sin agregar ninguna instancia de {@link 
     * modelo.CuentaDeCorreo CuentaDeCorreo} a la lista
     */
    public Usuario() {
        this.cuentasCorreo = new ArrayList<>();
    }

    /**
     * Método que asigna el nombre de usuario
     *
     * @param nombreDeUsuario Nombre de usuario
     */
    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    /**
     * Método que asigna la contraseña del usuario
     *
     * @param contrasenaUsuario Contraseña de usuario
     */
    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    /**
     * Método que incializa las cuentas de correo de un usuario
     *
     * @param cuentasCorreo Cuentas de correo
     */
    public void setCuentasCorreo(List<CuentaDeCorreo> cuentasCorreo) {
        this.cuentasCorreo = cuentasCorreo;
    }

    /**
     * Método que devuelve el nombre de usuario
     *
     * @return Nombre de usuario
     */
    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    /**
     * Método que devuelve la contraseña de usuario
     *
     * @return Contraseña de usuario
     */
    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    /**
     * Método que devuelve las cuentas de correo
     *
     * @return Cuentas de correo
     */
    public List<CuentaDeCorreo> getCuentasCorreo() {
        return cuentasCorreo;
    }

}
