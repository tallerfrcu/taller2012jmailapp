/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase que representa a un mail enviado
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class Envio extends Mail {

    /**
     * Variable de instancia que representa el origen del mail
     */
    private CuentaDeCorreo origenMail;
    /**
     * Variable de instancia que representa el destino del mail
     */
    private String destinoMail;
    /**
     * Variable de instancia que representa si el mail se envió o no
     */
    private boolean enviado;

    /**
     * Método que devuelve el origen del mail
     *
     * @return Origen del mail
     */
    /**
     * Constructor de la clase que instancia un mail enviado, inicializando el
     * origen del mail, y el destino del mail
     *
     * @param origenMail
     * @param destinoMail
     */
    public Envio(CuentaDeCorreo origenMail, String destinoMail) {
        super();
        this.origenMail = origenMail;
        this.destinoMail = destinoMail;
        this.enviado = false;
    }

    /**
     * Constructor de la clase que instancia un mail enviado, sin inicializar
     * sus variables de instancia
     */
    public Envio() {
        super();
    }

    /**
     *
     * @return
     */
    public CuentaDeCorreo getOrigenMail() {
        return origenMail;
    }

    /**
     * Método que asigna el origen del mail
     *
     * @param origenMail Origen del mail
     */
    public void setOrigenMail(CuentaDeCorreo origenMail) {
        this.origenMail = origenMail;
    }

    /**
     * Método que devuelve el destino del mail
     *
     * @return Destino del mail
     */
    public String getDestinoMail() {
        return destinoMail;
    }

    /**
     * Método que asigna el destino del mail
     *
     * @param destinoMail Destino del mail
     */
    public void setDestinoMail(String destinoMail) {
        this.destinoMail = destinoMail;
    }

    /**
     *
     * @param enviado
     */
    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    /**
     *
     * @return
     */
    public boolean getEnviado() {
        return this.enviado;
    }
    
    /**
     * Implementación de método que devuelve el origen en formato String
     * @return origen en formato String
     */
    @Override
    public String getOrigen() {
        return this.origenMail.getNombreCuenta() + 
                this.origenMail.getServicio().getUrlServicioCorreo();
    }
    /**
     * Implentación de método que devuelve el destino del mail en formato String
     * @return destino del mail en formato String
     */
    @Override
    public String getDestino() {
        return this.destinoMail;
    }

}
