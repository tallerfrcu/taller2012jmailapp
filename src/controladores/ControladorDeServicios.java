/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import modelo.Envio;
import servicios.FactoryServiciosCorreo;
import servicios.IServiciosCorreo;

/**
 * Clase que representa un controlador que se encarga de manejar los servicios
 * de correo para envío y recepción. 
 * @author Accornero, Fontana, García, Pascal
 */
public class ControladorDeServicios {
    public boolean enviarCorreo(Envio mail) 
            throws ExcepcionArchivoDePropiedadesNoEncontrado{
        IServiciosCorreo servicio;
        servicio = FactoryServiciosCorreo.getInstanciaFactoryServiciosCorreo().getServicioCorreo(mail.getOrigenMail().getServicio());
        return servicio.enviarCorreo(mail);
    }
}
