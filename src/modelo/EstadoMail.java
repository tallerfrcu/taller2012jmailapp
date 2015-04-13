/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase que representa el estado del mail
 * @author Accornero, Fontana, García, Pascal
 */
public class EstadoMail {
    /**
     * Variable de instancia que representa la identificacion del estado del 
     * mail
     */
    private int idEstadoMail;
    /**
     * Variable de instancia que representa la descripción del estado del mail
     */
    private String descripcionEstadoMail;
    /**
     * Constructor de la clase que instancia el estado del mail, asignando
     * el identificador del estado del mail,y la descripción del estado del mail
     * @param idEstadoMail
     * @param descripcionEstadoMail 
     */
    public EstadoMail(int idEstadoMail, String descripcionEstadoMail) {
        this.idEstadoMail = idEstadoMail;
        this.descripcionEstadoMail = descripcionEstadoMail;
    }
    /**
     * Constructor de la clase que instancia el estado del mail, sin 
     * asignar sus variables de instancia
     */
    public EstadoMail() {
    }
    /**
     * Método que devuelve la identificación del estado del mail
     * @return 
     */
    public int getIdEstadoMail() {
        return idEstadoMail;
    }
    /**
     * Método que asigna la identificación del estado del mail
     * @param idEstadoMail identificación del estado del mail
     */
    public void setIdEstadoMail(int idEstadoMail) {
        this.idEstadoMail = idEstadoMail;
    }
    /**
     * Método que devuelve la descripción del estado del mail
     * @return Descripción del estado del mail
     */
    public String getDescripcionEstadoMail() {
        return descripcionEstadoMail;
    }
    /**
     * Método que asigna la descripción del estado del mail
     * @param descripcionEstadoMail Descripción del estado del mail
     */
    public void setDescripcionEstadoMail(String descripcionEstadoMail) {
        this.descripcionEstadoMail = descripcionEstadoMail;
    }
    
    
    
}
