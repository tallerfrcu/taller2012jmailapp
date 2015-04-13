/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase que representa un mail
 * @author Accornero, Fontana, García, Pascal
 */
public abstract class Mail {
    /**
     * Variable de instancia que representa el asunto del mail
     */
    protected String asuntoMail;
    /**
     * Variable de instancia que representa el texto del mail
     */
    protected String textoMail;
    /**
     * Variable de instancia que representa la identificación del mail
     */
    protected int idMail;
    /**
     * Método que devuelve el asunto del mail
     * @return Asunto del mail
     */
    protected String getAsuntoMail() {
        return asuntoMail;
    }
    /**
     * Método que asigna el asunto del mail
     * @param asuntoMail Asunto del mail
     */
    protected void setAsuntoMail(String asuntoMail) {
        this.asuntoMail = asuntoMail;
    }
    /**
     * Método que devuelve el texto del mail
     * @return Texto del mail
     */
    protected String getTextoMail() {
        return textoMail;
    }
    /**
     * Método que asigna el texto del mail
     * @param textoMail Texto del mail
     */
    protected void setTextoMail(String textoMail) {
        this.textoMail = textoMail;
    }
    /**
     * Método que devuelve la identificación del mail
     * @return Identificación del mail
     */
    public int getIdMail() {
        return idMail;
    }
    /**
     * Método que asigna la identificación del mail
     * @param idMail Identificación del mail
     */
    public void setIdMail(int idMail) {
        this.idMail = idMail;
    }
    
    
    
    
}
