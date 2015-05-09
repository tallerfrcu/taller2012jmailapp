/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionDeServiciosCorreo;
import java.util.ArrayList;
import java.util.List;
import modelo.CuentaDeCorreo;
import modelo.Envio;
import modelo.Recepcion;
import servicios.FactoryServiciosCorreo;
import servicios.IServiciosCorreo;

/**
 * Clase que representa un controlador que se encarga de manejar los servicios
 * de correo para envío y recepción. 
 * @author Accornero, Fontana, García, Pascal
 */
public class ControladorDeServicios {
    /**
     * 
     * @param mail
     * @return
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado 
     */
    public boolean enviarCorreo(Envio mail) 
            throws ExcepcionArchivoDePropiedadesNoEncontrado{
        IServiciosCorreo servicio;
        servicio = FactoryServiciosCorreo.getInstanciaFactoryServiciosCorreo().
                getServicioCorreo(mail.getOrigenMail().getServicio());
        return servicio.enviarCorreo(mail);
    }
    /**
     * Método que devuelve una lista de mails recibidos. Retorna el total de 
     * mails existentes en la cuenta.
     * @param cuenta cuenta de la que se quiere obtener los mails
     * @return lista de instancias de Recepción
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si no se 
     * encuentra el archivo de propiedades correspondiente al servicio de la 
     * cuenta de correo
     * @throws ExcepcionDeServiciosCorreo se lanza si ocurre un error al 
     * intentar conectar con la cuenta de correo
     */
    public List<Recepcion> recibirCorreos(CuentaDeCorreo cuenta) 
            throws ExcepcionArchivoDePropiedadesNoEncontrado, 
            ExcepcionDeServiciosCorreo{
        IServiciosCorreo servicio;
        servicio = FactoryServiciosCorreo.getInstanciaFactoryServiciosCorreo().
                getServicioCorreo(cuenta.getServicio());
        return servicio.recibirCorreos(cuenta);
    }
}
