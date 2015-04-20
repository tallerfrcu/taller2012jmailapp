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
}
