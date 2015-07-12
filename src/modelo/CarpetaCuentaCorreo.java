/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una carpeta de un usuario en una cuenta en particular, 
 * que contiene una lista de mails. 
 * @author Accornero, Fontana, García, Pascal
 */
public class CarpetaCuentaCorreo {
    /**
     * id único de la carpeta
     */
    private int idCarpeta;
    /**
     * nombre de la carpeta
     */
    private String nombreCarpeta;
    /**
     * cuenta a la que está asociada la carpeta
     */
    private CuentaDeCorreo cuentaDeCorreo;
    /**
     * lista de mails que contiene la carpeta
     */
    private List<Mail> listaMails;
    /**
     * Constructor de la clase que instancia una carpeta instanciando una lista
     * vacía de mails.
     */
    public CarpetaCuentaCorreo() {
        this.listaMails = new ArrayList<>();
    }
    /**
     * Constructor de la clase que instancia una carpeta inicializando
     * sus variables de instancia
     * @param idCarpeta id de la carpeta
     * @param nombreCarpeta nombre de la carpeta
     * @param cuentaDeCorreo cuenta de correo a la que está asociada la carpeta
     * @param listaMails lista de mails de la carpeta
     */
    public CarpetaCuentaCorreo(int idCarpeta, String nombreCarpeta, 
            CuentaDeCorreo cuentaDeCorreo, List<Mail> listaMails) {
        this.idCarpeta = idCarpeta;
        this.nombreCarpeta = nombreCarpeta;
        this.cuentaDeCorreo = cuentaDeCorreo;
        this.listaMails = listaMails;
    }
    /**
     * Metodo que devuelve el id de la carpeta
     * @return 
     */
    public int getIdCarpeta() {
        return idCarpeta;
    }
    /**
     * Metodo que devuelve el nombre de la carpeta
     * @return 
     */
    public String getNombreCarpeta() {
        return nombreCarpeta;
    }
    /**
     * Metodo que devuelve la cuenta de correo asociada a la carpeta
     * @return 
     */
    public CuentaDeCorreo getCuentaDeCorreo() {
        return cuentaDeCorreo;
    }
    /**
     * Metodo que devuelve la lista de mails de la carpeta
     * @return 
     */
    public List<Mail> getListaMails() {
        return listaMails;
    }
    /**
     * Método que asigna el id de la carpeta
     * @param idCarpeta id de la carpeta
     */
    public void setIdCarpeta(int idCarpeta) {
        this.idCarpeta = idCarpeta;
    }
    /**
     * Método que asigna el nombre de la carpeta
     * @param nombreCarpeta nombre de la carpeta
     */
    public void setNombreCarpeta(String nombreCarpeta) {
        this.nombreCarpeta = nombreCarpeta;
    }
    /**
     * Método que asigna la cuenta de correo a la carpeta
     * @param cuentaDeCorreo cuenta de correo a la que está asociada la carpeta
     */
    public void setCuentaDeCorreo(CuentaDeCorreo cuentaDeCorreo) {
        this.cuentaDeCorreo = cuentaDeCorreo;
    }
    /**
     * Método que asigna la lista de mails de la carpeta
     * @param listaMails lista de mails que contiene la carpeta
     */
    public void setListaMails(List<Mail> listaMails) {
        this.listaMails = listaMails;
    }

    @Override
    public String toString() {
        return this.nombreCarpeta;
    }
    
}
