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
import modelo.Recepcion;
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
 * Clase que representa un Controlador de Fachada, que se encarga de ser 
 * interfaz entre la vista ({@link GUI GUI}) y las capas más bajas de la 
 * aplicación
 * @author Accornero, Fontana, García, Pascal
 */
public class ControladorDeFachada {

    /**
     * Constructor de la clase que instancia un controlador de fachada
     */
    public ControladorDeFachada() {
    }

    /**
     * Método que devuelve la instancia de {@link modelo.Usuario Usuario}
     * logueado a la aplicación
     *
     * @return instancia de {@link modelo.Usuario Usuario} que corresponde al
     * usuario logueado a la aplicación
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza en caso de que
     * no se encuentre el archivo de propiedades que contiene los datos de
     * conexión a la base de datos
     * @throws Excepciones.ExcepcionLogIn se lanza si no se encuentra el usuario
     * logueado a la aplicación
     */
    public Usuario getUsuarioLogueado()
            throws ExcepcionArchivoDePropiedadesNoEncontrado, ExcepcionLogIn {
        return DatosDeSesion.getDatosDeSesion().getUsuarioLogueado();
    }

    /**
     * Método que devuelve una lista con los servicio de correo que existen en
     * la base de datos.
     *
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
            IServicioCorreoDAO servicioCorreo = factory.getServicioCorreo();
            List<ServicioCorreo> listaServicioCorreo
                    = servicioCorreo.getServiciosCorreo();
            factory.cerrarConexion();
            return listaServicioCorreo;
        } finally {
        }
    }

    /**
     * Méotodo que guarda en la base de datos un correo a enviar.
     * Automáticamente se guarda como no enviado.
     *
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
     * Método que busca los correos recibidos y si los encuentra, los guarda en
     * la base de datos. Verifica que los correos que se bajan no existan en la
     * base de datos. Si no existen los guarda, en caso contrario no hace nada
     *
     * @param cuenta cuenta de correo de la que se deben bajar los mails
     * @return true en caso que exista al menos un correo nuevo, false en caso
     * que no exista ningún correo nuevo
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si no se puede
     * abrir el archivo de propiedades del servicio de la cuenta
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
                = (ArrayList) this.recibirCorreos(cuenta);
        //try {
        DAOFactory factory = DAOFactory.getDAOFactory();
        factory.iniciarConexion();
        ICorreoDAO correosDAO = factory.getCorreoDAO();
        boolean b = correosDAO.guardarCorreosRecibidos(listaMails);
        factory.cerrarConexion();
        return b;
        //DAOFactory.getDAOFactory().cerrarConexion();
        //}

    }

    /**
     * Método que inicia sesión en la aplicación. Consulta en la base de datos
     * si se encuentra el usuario pasado por parámetro y verifica que coincida
     * con la contraseña, también pasada por parámetro. Si coinciden, guarda el
     * usuario en la clase singelton
     * {@link Recursos.utilidades.DatosDeSesion DatosDeSesion}, en caso que no
     * coincidan o no se encuentra un usuario lanza una excepción
     * {@link Excepciones.ExcepcionLogIn ExcepcionLogIn}
     *
     * @param nombreUsuario nombre de usuario del usuario, usado para loguearse
     * a la aplicación
     * @param contrasenaUsuario contraseña del usuario, usada para loguearse a
     * la aplicación
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
     *
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
            ExcepcionArchivoDePropiedadesNoEncontrado {
        DAOFactory factory = DAOFactory.getDAOFactory();
        factory.iniciarConexion();
        ICorreoDAO correosDAO = factory.getCorreoDAO();
        ArrayList lista = (ArrayList) correosDAO.getCarpetas(cuenta);
        factory.cerrarConexion();
        return lista;
    }

    /**
     * Método que devuelva una lista de instancias de {@link modelo.Mail Mail}.
     * Puede contener tanto instancias de {@link modelo.Recepcion Recepcion}
     * como de {@link modelo.Envio Envio}
     *
     * @param carpeta carpeta de la que se quieren obtener los mails
     * @return lista de Mails
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * a la base de datos local
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si ocurre un
     * error al levantar el archivo de propiedades de la base de datos
     */
    public List<Mail> getCorreosDeCarpeta(CarpetaCuentaCorreo carpeta)
            throws ExcepcionErrorConexionBD,
            ExcepcionArchivoDePropiedadesNoEncontrado {
        DAOFactory factory = DAOFactory.getDAOFactory();
        factory.iniciarConexion();
        ICorreoDAO correosDAO = factory.getCorreoDAO();
        ArrayList lista = (ArrayList) correosDAO.getMails(carpeta);
        factory.cerrarConexion();
        return lista;
    }

    /**
     * Método
     *
     * @param mail
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado
     */
    public void enviarMail(Mail mail)
            throws ExcepcionArchivoDePropiedadesNoEncontrado {
        Thread hilo = new Thread(new HiloEnviarMails((Envio) mail));
        hilo.start();
    }

    /**
     * Método que devuelve el id del mail a partir del origen o destino y de la
     * fecha y hora del mail
     *
     * @param cuenta origen o destino
     * @param fecha fecha y hora del mail
     * @return id del mail o -1 en caso de que no se encuentre
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * a la base de datos
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
     * Método que devuelve un mail a través de su id
     *
     * @param idMail identificador interno del mail
     * @return instancia de alguna de las subclases de {@link modelo.Mail Mail}
     * que puede ser {@link modelo.Recepcion Recepción} o {@link modelo.Envio
     * Envío}
     * @throws ExcepcionErrorConexionBD Se lanza si ocurre un error de conexión
     * a la base de datos local
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado se lanza si
     * no se encuentra el archivo de propiedades de conexión a la base de datos
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

    /**
     * Método que genera un usuario nuevo de la aplicación
     *
     * @param nombreUsuario nombre de usuario que se quiere dar de alta
     * @param contrasena contraseña del usuario
     * @return true si se genera el usuario exitosamente
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * a la base de datos local o si ya existe el usuario que se quiere generar
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si no se
     * encuentra el archivo de propiedades de conexión a la base de datos
     */
    public boolean generarUsuario(String nombreUsuario, String contrasena)
            throws ExcepcionErrorConexionBD,
            ExcepcionArchivoDePropiedadesNoEncontrado {
        DAOFactory factory = DAOFactory.getDAOFactory();
        factory.iniciarConexion();
        IUsuarioDAO usuarioDAO = factory.getUsuarioDAO();
        boolean res = usuarioDAO.generarUsuario(nombreUsuario, contrasena);
        factory.cerrarConexion();
        return res;
    }

    /**
     * Método que devuelve una lista de mails recibidos. Retorna el total de
     * mails existentes en la cuenta.
     *
     * @param cuenta cuenta de la que se quiere obtener los mails
     * @return lista de instancias de Recepción
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado se lanza si no se
     * encuentra el archivo de propiedades correspondiente al servicio de la
     * cuenta de correo
     * @throws ExcepcionDeServiciosCorreo se lanza si ocurre un error al
     * intentar conectar con la cuenta de correo
     */
    public List<Recepcion> recibirCorreos(CuentaDeCorreo cuenta)
            throws ExcepcionArchivoDePropiedadesNoEncontrado,
            ExcepcionDeServiciosCorreo {
        IServiciosCorreo servicio;
        servicio = FactoryServiciosCorreo.getInstanciaFactoryServiciosCorreo().
                getServicioCorreo(cuenta.getServicio());
        return servicio.recibirCorreos(cuenta);
    }
}
