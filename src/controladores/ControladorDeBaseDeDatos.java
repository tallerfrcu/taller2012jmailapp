/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionDeServiciosCorreo;
import Excepciones.ExcepcionErrorConexionBD;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CuentaDeCorreo;
import modelo.ServicioCorreo;
import persistencia.DAOFactory;
import persistencia.ICorreos;
import persistencia.IServicioCorreo;

/**
 * Clase que representa un controlador que posee las responsabilidades asociadas
 * a la base de datos
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class ControladorDeBaseDeDatos {

    /**
     * Instancia de un controlador de servicios
     */
    private ControladorDeServicios controladorDeServicios;

    /**
     * Constructor de la clase que instancina un controlador de Base de Datos
     * instanciando un controlador de servicios de correo
     */
    public ControladorDeBaseDeDatos() {
        this.controladorDeServicios = new ControladorDeServicios();
    }

    /**
     * Método que devuelve una lista con los servicio de correo que existen
     * en la base de datos.
     * @return Lista con los servicios de correo
     * @throws ExcepcionErrorConexionBD excepción que se lanza cuando ocurre un
     * error de conexión
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado excepción que se lanza
     * cuando no se encuentra el archivo de propiedades
     */
    public List<ServicioCorreo> getListaServicioCorreo()
            throws ExcepcionErrorConexionBD,
            ExcepcionArchivoDePropiedadesNoEncontrado {
        try {
            DAOFactory factory = DAOFactory.getDAOFactory();
            factory.iniciarConexion();
            IServicioCorreo servicioCorreo = factory.getServicioCorreo();
            List<ServicioCorreo> listaServicioCorreo
                    = servicioCorreo.getServiciosCorreo();
            factory.cerrarConexion();
            return listaServicioCorreo;
        } finally {
            DAOFactory.getDAOFactory().cerrarConexion();
        }
    }
    /**
     * Método que busca los correos recibidos y si los encuentra, los guarda
     * en la base de datos. Verifica que los correos que se bajan no existan
     * en la base de datos. Si no existen los guarda, en caso contrario no 
     * hace nada
     * @param cuenta cuenta de correo de la que se deben bajar los mails
     * @return true en caso que exista al menos un correo nuevo, false en caso 
     * que no exista ningún correo nuevo
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si no se 
     * puede abrir el archivo de propiedades del servicio de la cuenta
     * @throws ExcepcionDeServiciosCorreo se lanza si ocurre un error al 
     * conectarse con el servidor de la cuenta de correo
     * @throws ExcepcionErrorConexionBD se lanza si ocurre algún error al 
     * guardar los mails en la base de datos
     */
    public boolean actualizarCorreoRecibidos(CuentaDeCorreo cuenta)
            throws ExcepcionArchivoDePropiedadesNoEncontrado,
            ExcepcionDeServiciosCorreo,
            ExcepcionErrorConexionBD {
        ArrayList listaMails
                = (ArrayList) this.controladorDeServicios.
                recibirCorreos(cuenta);
        try {
            DAOFactory factory = DAOFactory.getDAOFactory();
            factory.iniciarConexion();
            ICorreos correosDAO = factory.getCorreoDAO();
            return correosDAO.guardarCorreosRecibidos(listaMails);
        } finally {
            DAOFactory.getDAOFactory().cerrarConexion();
        }

    }
}
