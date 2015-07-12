/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.hilos;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Envio;
import servicios.FactoryServiciosCorreo;
import servicios.IServiciosCorreo;

/**
 * Clase que representa un hilo el cual se encarga de enviar los mails, haicnedo
 * uso de los servicios de correo de la clase {@link servicios.
 * FactoryServiciosCorreo FactoryServiciosCorreo}
 * @author Accornero, Fontana, García, Pascal
 */
public class HiloEnviarMails implements Runnable {
    /**
     * nombre del hilo
     */
    private String nombreHilo;
    /**
     * mail que envía el hilo
     */
    private Envio mailEnvio;
    public HiloEnviarMails(Envio mailEnvio){
        this.mailEnvio = mailEnvio;
    }

    @Override
    public void run() {
        try {
            IServiciosCorreo servicio;
            servicio = FactoryServiciosCorreo.getInstanciaFactoryServiciosCorreo().
                    getServicioCorreo(this.mailEnvio.getOrigenMail().getServicio());
            servicio.enviarCorreo(this.mailEnvio);
        } catch (ExcepcionArchivoDePropiedadesNoEncontrado ex) {
            System.err.println("Error en el hilo");
        }
    }

    
}
