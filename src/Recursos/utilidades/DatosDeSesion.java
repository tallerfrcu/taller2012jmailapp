/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos.utilidades;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;

/**
 * Clase que representa una instancia de sesión. Es una clase singleton ya que
 * existe una única sesión durante la ejecución de la aplicación.
 * @author Accornero, Fontana, García, Pascal
 */
public class DatosDeSesion {
    /**
     * instancia única de sesión de la aplicación.
     */
    private static DatosDeSesion SESION = null;
    /**
     * 
     */
    private PoolDeConexiones poolDeConexiones;
    /**
     * Constructor privado que isntancia un pool de conexiones.
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado
     */
    private DatosDeSesion() throws ExcepcionArchivoDePropiedadesNoEncontrado {
        this.poolDeConexiones = new PoolDeConexiones();
    }
    /**
     * método de clase que devuelve la instancia única de sesión
     * @return instancia de sesión de la aplicación.
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado
     */
    public static DatosDeSesion getDatosDeSesion() throws ExcepcionArchivoDePropiedadesNoEncontrado{
        if (DatosDeSesion.SESION == null) {
            return new DatosDeSesion();
        } else {
            return DatosDeSesion.SESION;
        }
    }
    /**
     * 
     * @return 
     */
    public PoolDeConexiones getPoolDeConexiones() {
        return poolDeConexiones;
    }
    
}
