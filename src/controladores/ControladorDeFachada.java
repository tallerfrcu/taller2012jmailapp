/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionDeServiciosCorreo;
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

    /**
     * instancia del controlador de base de datos
     */
    private ControladorDeBaseDeDatos controladorDeBaseDeDatos;
    
    /**
     * Constructor de la clase que instancia un controlador de facha 
     * inicializando el controlador de base de datos.
     */
    public ControladorDeFachada() {
        this.controladorDeBaseDeDatos = new ControladorDeBaseDeDatos();
    }

    /**
     *
     * @return @throws ExcepcionErrorConexionBD
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado
     */
    public List<ServicioCorreo> getListaServicioCorreo()
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
        ControladorDeServicios controladorDeServicios
                = new ControladorDeServicios();
        Envio mail = new Envio();
        mail.setOrigenMail(cuentaEnvio);
        mail.setDestinoMail(destino);
        mail.setAsuntoMail(asunto);
        mail.setTextoMail(cuerpo);
        return controladorDeServicios.enviarCorreo(mail);
    }

    /**
     * Método que busca los correos nuevos en la cuenta que se pasa por
     * parámetro. Si existen, se guardan en la base de datos. En caso que exista
     * al menos un correo nuevo, se duevuelve true. False en caso contrario
     *
     * @param cuentaARecibir cuenta de correo de la que se quiere bajar los
     * correos
     * @return true en caso que exista al menos un correo nuevo. False si no
     * existe ningún correo nuevo
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si existe un
     * error al levantar el archivo de propiedades del servicio de correo
     * @throws ExcepcionDeServiciosCorreo se lanza si ocurre un error al
     * conectar con el servicio de correo.
     * @throws ExcepcionErrorConexionBD se lanza si se produce un error al bajar
     * los correos a la base de datos.
     */
    public boolean existenCorreosRecibidosNuevos(CuentaDeCorreo cuentaARecibir)
            throws ExcepcionArchivoDePropiedadesNoEncontrado,
            ExcepcionDeServiciosCorreo, ExcepcionErrorConexionBD {
        return this.controladorDeBaseDeDatos.actualizarCorreoRecibidos(
                cuentaARecibir);
    }
}
