/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionDeServiciosCorreo;
import Excepciones.ExcepcionErrorConexionBD;
import Excepciones.ExcepcionLogIn;
import Recursos.utilidades.DatosDeSesion;
import controladores.hilos.HiloEnviarMails;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import modelo.CarpetaCuentaCorreo;
import modelo.CuentaDeCorreo;
import modelo.Envio;
import modelo.Mail;
import modelo.ServicioCorreo;
import modelo.Usuario;
import persistencia.DAOFactory;
import persistencia.ICorreoDAO;
import persistencia.IServicioCorreoDAO;
import persistencia.IUsuarioDAO;
import persistencia.postgres.PostgresCorreoDAO;
import servicios.FactoryServiciosCorreo;
import servicios.IServiciosCorreo;

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
     * Método que devuelve la instancia de {@link modelo.Usuario Usuario} 
     * logueado a la aplicación
     * @return instancia de {@link modelo.Usuario Usuario} que corresponde al
     * usuario logueado a la aplicación
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza en caso de que
     * no se encuentre el archivo de propiedades que contiene los datos de 
     * conexión a la base de datos
     * @throws Excepciones.ExcepcionLogIn se lanza si no se encuentra el usuario
     * logueado a la aplicación
     */
    public Usuario getUsuarioLogueado() 
            throws ExcepcionArchivoDePropiedadesNoEncontrado, ExcepcionLogIn{
        return DatosDeSesion.getDatosDeSesion().getUsuarioLogueado();
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
     * Méotodo que guarda en la base de datos un correo a enviar. 
     * Automáticamente se guarda como no enviado.
     * @param mailEnvio mail a enviar
     * @return true si se guardó con éxito
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error al 
     * conectarse a la base de datos
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado
     */
    public boolean guardarCorreoEnvio(Envio mailEnvio) throws 
            ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado {
        DAOFactory factory = DAOFactory.getDAOFactory();
        factory.iniciarConexion();
        ICorreoDAO correoDAO = factory.getCorreoDAO();
        correoDAO.guardarCorreoEnvio(mailEnvio);
        factory.cerrarConexion();
        return true;
    }

    /**
     * Método que busca los correos nuevos en la cuenta que se pasa por
     * parámetro. Si existen, se guardan en la base de datos. En caso que exista
     * al menos un correo nuevo, se duevuelve true. False en caso contrario
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
    /**
     * Método que inicia sesión en la aplicación. Consulta en la base de datos
     * si se encuentra el usuario pasado por parámetro y verifica que coincida
     * con la contraseña, también pasada por parámetro. Si coinciden, guarda
     * el usuario en la clase singelton 
     * {@link Recursos.utilidades.DatosDeSesion DatosDeSesion}, en caso que no 
     * coincidan o no se encuentra un usuario lanza una excepción 
     * {@link Excepciones.ExcepcionLogIn ExcepcionLogIn}
     * @param nombreUsuario nombre de usuario del usuario, usado para loguearse
     * a la aplicación
     * @param contrasenaUsuario contraseña del usuario, usada para loguearse
     * a la aplicación
     * @return true si se logueo correctamente
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error al 
     * conectarse a la base de datos local
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si ocurre un
     * error al buscar el archivo de propiedades de la base de datos
     * @throws ExcepcionLogIn se lanza si no coinciden el usuario y contraseña,
     * o si el usuario no se encuentra en la base de datos
     */
    public boolean loguinUsuario(
            String nombreUsuario, String contrasenaUsuario) 
            throws ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado, ExcepcionLogIn {
        try {
            DAOFactory factory = DAOFactory.getDAOFactory();
            factory.iniciarConexion();
            IUsuarioDAO usuarioDAO = factory.getUsuarioDAO();
            DatosDeSesion.getDatosDeSesion().logInUsuario(
                    usuarioDAO.logInUsuario(nombreUsuario, contrasenaUsuario));
            factory.cerrarConexion();
        } finally {
            //DAOFactory.getDAOFactory().cerrarConexion();
        }
        return true;
    }
    /**
     * Método que devuelve la lista de carpetas asociadas a una {@link 
     * modelo.CuentaDeCorreo cuenta de correo}
     * @param cuenta instancia de {@link modelo.CuentaDeCorreo CuentaDeCorreo}
     * @return lista de instancias de {@link modelo.CarpetaCuentaCorreo 
     * CarpetaCuentaCorreo}
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * a la base de datos local
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si ocurre un 
     * error al levantar el archivo de propiedades de la base de datos
     */
    public List<CarpetaCuentaCorreo> getCarpetasCuenta(CuentaDeCorreo cuenta) 
            throws ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado{
        DAOFactory factory = DAOFactory.getDAOFactory();
        factory.iniciarConexion();
        ICorreoDAO correosDAO = factory.getCorreoDAO();
        ArrayList lista = (ArrayList)correosDAO.getCarpetas(cuenta);
        factory.cerrarConexion();
        return lista;
    }
    /**
     * Método que devuelva una lista de instancias de {@link modelo.Mail Mail}.
     * Puede contener tanto instancias de {@link modelo.Recepcion Recepcion} 
     * como de {@link modelo.Envio Envio}
     * @param carpeta carpeta de la que se quieren obtener los mails
     * @return lista de Mails
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * a la base de datos local
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si ocurre un 
     * error al levantar el archivo de propiedades de la base de datos
     */
    public List<Mail> getCorreosDeCarpeta(CarpetaCuentaCorreo carpeta) 
            throws ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado{
        DAOFactory factory = DAOFactory.getDAOFactory();
        factory.iniciarConexion();
        ICorreoDAO correosDAO = factory.getCorreoDAO();
        ArrayList lista = (ArrayList)correosDAO.getMails(carpeta);
        factory.cerrarConexion();
        return lista;
    }
    /**
     * 
     * @param mail
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado 
     */
    public void enviarMail(Mail mail) 
            throws ExcepcionArchivoDePropiedadesNoEncontrado{
        Thread hilo = new Thread(new HiloEnviarMails((Envio)mail));
        hilo.start();
    }
    /**
     * Método que devuelve el id del mail a partir del origen o destino y 
     * de la fecha y hora del mail
     * @param cuenta origen o destino   
     * @param fecha fecha y hora del mail
     * @return id del mail o -1 en caso de que no se encuentre
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de 
     * conexión a la base de datos
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado
     */
    public int getIdMail(String cuenta, Timestamp fecha) 
            throws ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado {
        DAOFactory factory = DAOFactory.getDAOFactory();
        factory.iniciarConexion();
        ICorreoDAO correoDAO = factory.getCorreoDAO();
        int idCorreo = correoDAO.getIdCorreo(cuenta, fecha);
        factory.cerrarConexion();
        return idCorreo;
    }
    /**
     * 
     * @param idMail
     * @return
     * @throws ExcepcionErrorConexionBD 
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado 
     */
    public Mail getMailPorId(int idMail) throws ExcepcionErrorConexionBD, 
            ExcepcionArchivoDePropiedadesNoEncontrado {
        DAOFactory factory = DAOFactory.getDAOFactory();
        factory.iniciarConexion();
        ICorreoDAO correoDAO = factory.getCorreoDAO();
        Mail mail = correoDAO.getCorreo(idMail);
        factory.cerrarConexion();
        return mail;
    }
}
