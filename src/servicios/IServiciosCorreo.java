/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;
import modelo.CuentaDeCorreo;
import modelo.Envio;
import modelo.Recepcion;

/**
 * Interfaz que define los servicios que se deben ofrecer desde los distintos
 * servicios de correo que se implementen en la aplicación
 * @author Accornero, Fontana, García, Pascal
 */
public interface IServiciosCorreo {
    /**
     * Método que define el envío de un e-mail
     * @param mail instancia de Envio que contiene los atributos del mail para
     * ser enviado
     * @return devuelve true si el envío fue exitoso, false en caso contrario
     */
    public boolean enviarCorreo(Envio mail);
    /**
     * Méotodo que define la recepción de e-mails 
     * @param cuenta cuenta de correo de la que se quiere recibir los mails
     * @return lista de mails
     * @throws Excepciones.ExcepcionDeServiciosCorreo excepción que se lanza
     * si ocurre algún error de conectividad
     */
    public List<Recepcion> recibirCorreos(CuentaDeCorreo cuenta) 
            throws Excepciones.ExcepcionDeServiciosCorreo;
}
