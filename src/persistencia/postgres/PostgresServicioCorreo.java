/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.postgres;

import Excepciones.ExcepcionErrorConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ServicioCorreo;
import persistencia.IServicioCorreo;

/**
 * Clase que representa los servicios de conexión a la base de datos para los
 * obtener, actualizar, insertar y borrar lo referente a los servicios de
 * correo
 * @author Accornero, Fontana, García, Pascal
 */
public class PostgresServicioCorreo implements IServicioCorreo{
    /**
     * instancia de conexión a la base de datos
     */
    private Connection conexion;
    /**
     * Constructor de la clase que instancia un objeto, pasando por parámetro
     * la conexión a la base de datos
     * @param conexion instancia de conexión a la base de datos
     */
    public PostgresServicioCorreo(Connection conexion) {
        this.conexion = conexion;
    }
    /**
     * método que obtiene todos los servicios de correo que hay en la 
     * base de datos.
     * @return lista de servicios de correo
     * @throws Excepciones.ExcepcionErrorConexionBD excepción que se lanza si
     * ocurre un error al consultar en la base de datos
     */
    @Override
    public List<ServicioCorreo> getServiciosCorreo() throws 
            ExcepcionErrorConexionBD {
        Statement sentencia = null;
        ArrayList<ServicioCorreo> listaServiciosCorreo = new ArrayList<>();
        String sentenciaSQL;
        ResultSet resultado;
        sentenciaSQL = "select id_servicio_correo, url_servicio_correo from "
                + "servicio_correo";
        try {
            sentencia = this.conexion.createStatement();
            resultado = sentencia.executeQuery(sentenciaSQL);
            while (resultado.next()) {
                ServicioCorreo sCTemporal = new ServicioCorreo();
                sCTemporal.setIdServicioCorreo(
                        resultado.getInt("id_servicio_correo"));
                sCTemporal.setUrlServicioCorreo(
                        resultado.getString("url_servicio_correo"));
                listaServiciosCorreo.add(sCTemporal);
                resultado.next();
            }
        } catch(SQLException ex){
            throw new ExcepcionErrorConexionBD("Error al recuperar "
                    + "los servicios de correo", ex);
        }
        return listaServiciosCorreo;
    }
    
}
