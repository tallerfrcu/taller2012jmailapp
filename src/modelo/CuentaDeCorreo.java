/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase que representa una cuenta de correo
 * @author Accornero, Fontana, García, Pascal
 */
public class CuentaDeCorreo {
    /**
     * Variable de instancia que representa el nombre de la cuenta
     */
    private String nombreCuenta;
    /**
     * Contraseña de la cuenta de correo
     */
    private String contrasenaCuenta;
    /**
     * Variable de instancia que representa el servicio de correo
     */
    private ServicioCorreo servicio;
    /**
     * Constructor de la clase que instancia la cuenta de correo de un usuario
     * asignando el nombre de la cuenta y el servicio de correo utilizado
     * @param nombreCuenta
     * @param servicio 
     */
    public CuentaDeCorreo(String nombreCuenta, ServicioCorreo servicio) {
        this.nombreCuenta = nombreCuenta;
        this.servicio = servicio;
    }
    /**
     * Constructor de la clase que instancia la cuenta de correo, sin 
     * asignar sus variables de instancia
     */
    public CuentaDeCorreo() {
    }
    /**
     * Método que devuelve el nombre de la cuenta de correo
     * @return Nombre de cuenta
     */
    public String getNombreCuenta() {
        return nombreCuenta;
    }
    /**
     * Método que asigna el nombre de la cuenta de correo
     * @param nombreCuenta Nombre de cuenta
     */
    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }
    /**
     * Método que devuelve el servicio de correo utilizado, junto con su 
     * identificación y dirección URL
     * @return 
     */
    public ServicioCorreo getServicio() {
        return servicio;
    }
    /**
     * Método que asigna el servicio de correo
     * @param servicio Servicio de correo
     */
    public void setServicio(ServicioCorreo servicio) {
        this.servicio = servicio;
    }
    /**
     * Método que asigna la contraseña de la cuenta de correo
     * @param contrasenaCuenta contraseña de la cuenta de correo
     */
    public void setContrasenaCuenta(String contrasenaCuenta) {
        this.contrasenaCuenta = contrasenaCuenta;
    }
    /**
     * Método que devuelve la conraseña de correo de la cuenta
     * @return contraseña de la cuenta de correo
     */
    public String getContrasenaCuenta() {
        return contrasenaCuenta;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return this.nombreCuenta + this.servicio.getUrlServicioCorreo();
    }
    
}
