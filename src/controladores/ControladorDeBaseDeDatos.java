/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionErrorConexionBD;
import java.util.List;
import modelo.ServicioCorreo;
import persistencia.DAOFactory;
import persistencia.IServicioCorreo;

/**
 * Clase que representa un controlador que posee las responsabilidades
 * asociadas a la base de datos
 * @author Accornero, Fontana, García, Pascal
 */
public class ControladorDeBaseDeDatos {
    /**
     * Método que devuelve una lista con los servicio de correo.
     * @return Lista con los servicios de correo
     * @throws ExcepcionErrorConexionBD excepción que se lanza cuando ocurre
     * un error de conexión
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado excepción que se lanza 
     * cuando no se encuentra el archivo de propiedades
     */
    public List<ServicioCorreo> getListaServicioCorreo () 
            throws ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado{
        DAOFactory factory = DAOFactory.getDAOFactory();
        factory.iniciarConexion();
        IServicioCorreo servicioCorreo = factory.getServicioCorreo();
        List<ServicioCorreo> listaServicioCorreo = 
                servicioCorreo.getServiciosCorreo();
        factory.cerrarConexion();
        return listaServicioCorreo;
    }
}
