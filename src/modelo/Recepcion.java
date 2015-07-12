/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase que representa un mail que fue recibido
 * @author Accornero, Fontana, García, Pascal
 */
public class Recepcion extends Mail {
    /**
     * Variable de instancia que representa el destino del mail
     */
    private CuentaDeCorreo destinoMail;
    /**
     * Variable de instancia que representa el origen del mail
     */
    private String origenMail;
    /**
     * Variable de instancia que representa si un mail fue leído o no
     */
    private boolean leido;
     /**
     * Método que devuelve si un mail fue leído o no
     * @return True si fue leído, False si no
     */
    public boolean isLeido() {
        return leido;
    }
    /**
     * Método que asigna si un mail fue leído o no
     * @param leido 
     */
    public void setLeido(boolean leido) {
        this.leido = leido;
    }
    /**
     * Método que devuelve el destino del mail
     * @return Destino del mail
     */
    public CuentaDeCorreo getDestinoMail() {
        return destinoMail;
    }
    /**
     * Método que asgina el destino del mail
     * @param destinoMail  Destino del mail
     */
    public void setDestinoMail(CuentaDeCorreo destinoMail) {
        this.destinoMail = destinoMail;
    }
    /**
     * Método que devuelve el origen del mail
     * @return Origen del mail
     */
    public String getOrigenMail() {
        return origenMail;
    }
    /**
     * Método que asigna el origen del mail
     * @param origenMail Origen del mail
     */
    public void setOrigenMail(String origenMail) {
        this.origenMail = origenMail;
    }
    /**
     * Constructor de la clase que instancia un mail recibido inicializando el
     * destino del mail, origen del mail, y si fue leído o no
     * @param destinoMail
     * @param origenMail
     * @param leido 
     */
    public Recepcion(CuentaDeCorreo destinoMail, 
            String origenMail, boolean leido) {
        super();
        this.destinoMail = destinoMail;
        this.origenMail = origenMail;
        this.leido = leido;
    }
    /**
     * Constructor de la clase que instancia un mail recibido, sin asignar sus
     * variables de instancia
     */
    public Recepcion() {
        super();
    }
    /**
     * Implementación de método que devuelve el origen del mail en formato 
     * String
     * @return origen del mail en formato String
     */
    @Override
    public String getOrigen() {
        return this.origenMail;
    }
    /**
     * Implementación de método que devuelve el destino del mail en formato 
     * String
     * @return destino del mail en formato String
     */
    @Override
    public String getDestino() {
        return this.destinoMail.getNombreCuenta() + 
                this.destinoMail.getServicio().getUrlServicioCorreo();
    }
    
    
}
