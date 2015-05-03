/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import java.util.List;
import modelo.CuentaDeCorreo;
import modelo.Envio;
import modelo.ServicioCorreo;

/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class ControladorDeFachada {
    
    public List<ServicioCorreo> getListaServicioCorreo () 
            throws ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado {
        ControladorDeBaseDeDatos controlador = new ControladorDeBaseDeDatos();
        return controlador.getListaServicioCorreo();
    }
    /**
     * 
     * @param cuentaEnvio
     * @param destino
     * @param asunto
     * @param cuerpo
     * @return
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado 
     */
    public boolean enviarCorreo(
            CuentaDeCorreo cuentaEnvio, String destino, 
            String asunto, String cuerpo) throws ExcepcionArchivoDePropiedadesNoEncontrado {
        ControladorDeServicios controladorDeServicios = 
                new ControladorDeServicios();
        Envio mail = new Envio();
        mail.setOrigenMail(cuentaEnvio);
        mail.setDestinoMail(destino);
        mail.setAsuntoMail(asunto);
        mail.setTextoMail(cuerpo);
        return controladorDeServicios.enviarCorreo(mail);
    }
}
