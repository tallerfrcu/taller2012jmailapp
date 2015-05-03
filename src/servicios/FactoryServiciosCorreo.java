/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import modelo.ServicioCorreo;

/**
 * Clase que representa una factoría de servicios de correo. Dependiendo de 
 * el identificador que se envíe, devuelve una instancia de servicios de correo.
 * Se utiliza el patrón Singleton para que exista un único gestor de las 
 * estrategias de servicios de correos que existen en la aplicación.
 * @author Accornero, Fontana, García, Pascal
 */
public class FactoryServiciosCorreo {
    /**
     * instancia de clase de FactoryServiciosCorreo
     */
    private static FactoryServiciosCorreo FACTORY_SERVICIOS_CORREO;
    
    /**
     * Constructor privado de la clase que instancia un FactoryServiciosCorreo
     */
    private FactoryServiciosCorreo(){        
    }
    
    /**
     * método de clase que devuelve la instancia única de la clase misma. 
     * Evalúa que la instancia de FactoryServiciosCorreo esté instanciada, de
     * no ser así, la instancia y la devuelve.
     * @return instancia de FactoryServiciosCorreo
     */
    public static FactoryServiciosCorreo getInstanciaFactoryServiciosCorreo() {
        if (FACTORY_SERVICIOS_CORREO == null){
            FACTORY_SERVICIOS_CORREO = new FactoryServiciosCorreo();
            return FACTORY_SERVICIOS_CORREO;
        } else {
            return FACTORY_SERVICIOS_CORREO;
        }
    }
    /**
     * Método que devuelve la isntancia correspondiente del servicio de correo
     * @param servicioCorreo servicio de correo del modelo que se desea obtener
     * para utilizar el servicio de envío y/o recepción
     * @return instancia correspondiente de IServicioCorreo
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado
     */
    public IServiciosCorreo getServicioCorreo(ServicioCorreo servicioCorreo) 
            throws ExcepcionArchivoDePropiedadesNoEncontrado {
        switch(servicioCorreo.getUrlServicioCorreo()) {
            case "@gmail.com": {
                return new ServicioGMail();
            }
            case "@yahoo.com" : {
                
            }
            default:{
                System.err.println("");
            }
        }
        return null;
    }
}
