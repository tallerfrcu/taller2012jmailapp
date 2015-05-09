/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Timestamp;

/**
 * Clase que representa un mail
 * @author Accornero, Fontana, García, Pascal
 */
public abstract class Mail {
    /**
     * fecha del mail. Para el caso de Recepción es la fecha de recepción
     * y para el caso de Envío es la fecha de envío
     */
    protected Timestamp fechaMail;
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
    public String getAsuntoMail() {
        return asuntoMail;
    }
    /**
     * Método que asigna el asunto del mail
     * @param asuntoMail Asunto del mail
     */
    public void setAsuntoMail(String asuntoMail) {
        this.asuntoMail = asuntoMail;
    }
    /**
     * Método que devuelve el texto del mail
     * @return Texto del mail
     */
    public String getTextoMail() {
        return textoMail;
    }
    /**
     * Método que asigna el texto del mail
     * @param textoMail Texto del mail
     */
    public void setTextoMail(String textoMail) {
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
    /**
     * Método que asigna la fecha del mail. Para el caso de Recepción es la 
     * fecha de envío y para el caso de Envío es la fecha de envío
     * @param fechaMail fecha del mail.
     */
    public void setFechaMail(Timestamp fechaMail) {
        this.fechaMail = fechaMail;
    }
    /**
     * Método que devuelve la fecha del mail
     * @return 
     */
    public Timestamp getFechaMail() {
        return fechaMail;
    }
    
    
    
    
}
