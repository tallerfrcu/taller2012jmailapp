/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos.utilidades;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionLogIn;
import java.util.Iterator;
import modelo.CuentaDeCorreo;
import modelo.Usuario;

/**
 * Clase que representa una instancia de sesión. Es una clase singleton ya que
 * existe una única sesión durante la ejecución de la aplicación.
 * @author Accornero, Fontana, García, Pascal
 */
public class DatosDeSesion {
    /**
     * instancia del usuario que se encuentra actualmente logueado 
     * a la aplicación
     */
    private Usuario usuarioLogueado;
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
    public static DatosDeSesion getDatosDeSesion() 
            throws ExcepcionArchivoDePropiedadesNoEncontrado{
        if (DatosDeSesion.SESION == null) {
            DatosDeSesion.SESION =  new DatosDeSesion();
            return DatosDeSesion.SESION;
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
    /**
     * método que loguea al usuario guardándolo en la variable de instancia
     * usuarioLogueado.
     * @param usuarioLogIn usuario que se loguea a la aplicación
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado
     */
    public void logInUsuario(Usuario usuarioLogIn) 
            throws ExcepcionArchivoDePropiedadesNoEncontrado{
        DatosDeSesion.getDatosDeSesion().setUsuarioLogIn(usuarioLogIn);
    }
    /**
     * Método que devuelve la instancia de {@link modelo.Usuario Usuario} 
     * correspondiente al usuario logueado
     * @return usuario logueado a la aplicación
     * @throws Excepciones.ExcepcionLogIn se lanza si no se encuentra logueado
     * el usuario a la aplicación
     */
    public Usuario getUsuarioLogueado() throws ExcepcionLogIn{
        if(this.usuarioLogueado == null){
            throw new ExcepcionLogIn("No se ha iniciado sesión");
        } else {
            return DatosDeSesion.SESION.usuarioLogueado;
        }
    }
    private void setUsuarioLogIn(Usuario usuarioLogIn) {
        this.usuarioLogueado = usuarioLogIn;
    }
    /**
     * Método que devuelve una instancia de {@link modelo.CarpetaCuentaCorreo }
     * CuentaDeCorreo si la encuentra en el usuario logueado
     * @param cuenta string de la cuenta que se desea buscar
     * @return instancia de CuentaDeCorreo
     */
    public CuentaDeCorreo getCuenta(String cuenta){
        Iterator iterador = this.usuarioLogueado.getCuentasCorreo().iterator();
        while(iterador.hasNext()){
            CuentaDeCorreo cuentaCorreo = (CuentaDeCorreo)iterador.next();
            if((cuentaCorreo.getNombreCuenta() + 
                    cuentaCorreo.getServicio().getUrlServicioCorreo()).
                    compareTo(cuenta) == 0) {
                return cuentaCorreo;
            }
        }
        return null;
    }
}
