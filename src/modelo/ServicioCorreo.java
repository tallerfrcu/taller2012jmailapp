/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase que representa el servicio de correo
 * @author Accornero, Fontana, García, Pascal
 */
public class ServicioCorreo {
    /**
     * Variable de instancia que representa la identificación del servicio de 
     * correo utilizado
     */
    private int idServicioCorreo;
    /**
     * Variable de instancia que representa la dirección URL del servicio de 
     * correo
     */
    private String urlServicioCorreo;
    /**
     * Constructor de la clase que instancia el servicio de correo asignando
     * la identificación del servicio de correo y la dirección URL del servicio
     * de correo
     * @param idServicioCorreo
     * @param urlServicioCorreo 
     */
    public ServicioCorreo(int idServicioCorreo, String urlServicioCorreo) {
        this.idServicioCorreo = idServicioCorreo;
        this.urlServicioCorreo = urlServicioCorreo;
    }
    /**
     * Constructor de la clase que instancia el servicio de correo, sin 
     * asignar sus variables de instancia
     */
    public ServicioCorreo() {
    }

    /**
     * Método que devuelve la identificación del servicio de correo
     * @return Identificación del servicio de correo
     */
    
    public int getIdServicioCorreo() {
        return idServicioCorreo;
    }
    
    /**
     * Método que asigna la identificación del servicio de correo
     * @param idServicioCorreo Identificiación del servicio de correo
     */
    public void setIdServicioCorreo(int idServicioCorreo) {
        this.idServicioCorreo = idServicioCorreo;
    }
    
    /**
     * Método que devuelve la dirección URL del servicio de correo
     * @return URL del servicio de correo
     */
    public String getUrlServicioCorreo() {
        return urlServicioCorreo;
    }
    /**
     * Método que asigna la direccion URL del servicio de correo
     * @param urlServicioCorreo URL del servicio de correo
     */
    public void setUrlServicioCorreo(String urlServicioCorreo) {
        this.urlServicioCorreo = urlServicioCorreo;
    }
    
    
    
}
