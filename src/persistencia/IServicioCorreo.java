/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Excepciones.ExcepcionErrorConexionBD;
import java.util.List;
import modelo.ServicioCorreo;

/**
 * Interfaz que define los métodos para consultar, insertar, borrar y actualizar
 * los datos referentes a los servicio de correo
 * @author Accornero, Fontana, García, Pascal
 */
public interface IServicioCorreo {
    public List<ServicioCorreo> getServiciosCorreo() throws 
            ExcepcionErrorConexionBD;
}
