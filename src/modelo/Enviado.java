/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase que representa a un mail enviado
 * @author Accornero, Fontana, García, Pascal
 */
public class Enviado {
    /**
     * Variable de instancia que representa el origen del mail
     */
    private CuentaDeCorreo origenMail;
    /**
     * Variable de instancia que representa el destino del mail
     */
    private String destinoMail;

    /**
     * Método que devuelve el origen del mail
     * @return Origen del mail
     */
    public CuentaDeCorreo getOrigenMail() {
        return origenMail;
    }
    /**
     * Método que asigna el origen del mail
     * @param origenMail Origen del mail
     */
    public void setOrigenMail(CuentaDeCorreo origenMail) {
        this.origenMail = origenMail;
    }
    /**
     * Método que devuelve el destino del mail
     * @return Destino del mail
     */
    public String getDestinoMail() {
        return destinoMail;
    }
    /**
     * Método que asigna el destino del mail
     * @param destinoMail Destino del mail
     */
    public void setDestinoMail(String destinoMail) {
        this.destinoMail = destinoMail;
    }
    /**
     * Constructor de la clase que instancia un mail enviado, inicializando el
     * origen del mail, y el destino del mail
     * @param origenMail
     * @param destinoMail 
     */
    public Enviado(CuentaDeCorreo origenMail, String destinoMail) {
        this.origenMail = origenMail;
        this.destinoMail = destinoMail;
    }
    /**
     * Constructor de la clase que instancia un mail enviado, sin inicializar 
     * sus variables de instancia
     */
    public Enviado() {
    }
    
    
    
    
}
