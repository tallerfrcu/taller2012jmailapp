/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.postgres;

import Excepciones.ExcepcionErrorConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CarpetaCuentaCorreo;
import modelo.CuentaDeCorreo;
import modelo.Envio;
import modelo.Mail;
import modelo.Recepcion;
import modelo.ServicioCorreo;
import persistencia.ICorreoDAO;

/**
 * Clase que implementa la interfaz ICorreo y define los métodos para tratar los
 * correos contra la base de datos
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class PostgresCorreoDAO implements ICorreoDAO {

    /**
     * Instancia de la conexión a la base de datos
     */
    private Connection conexion;
    /**
     * String de la consulta para retornar el siguiente id de la tabla de mail
     */
    private String sqlGetNextValMail;
    /**
     * String de la consulta de mails por cuenta y por usuario
     */
    private String sqlSelectMailPorCuentaUsuario;
    /**
     * String de inserción en la tabla mail
     */
    private String sqlInsertMail;
    /**
     * String de la inserción en la tabla recepcion
     */
    private String sqlInsertMailRecibido;
    /**
     * String de selección de las carpetas pertenecientes a una cuenta de correo
     */
    private String sqlSelectCarpetas;
    /**
     * String de selección de los mails de una carpeta
     */
    private String sqlSelectMails;
    /**
     * String de inserción de un mail en la base de datos
     */
    private String sqlInsertMailEnvio;
    /**
     * String de la consulta para obtener el id de un mail
     */
    private String sqlSelectIdMail;
    /**
     * String utilizado para obtener un mail a partir de un ID
     */
    private String sqlSelectMailPorID;
    /**
     * String de inserción en la tabla de relación entre el mail y la carpeta
     */
    private String sqlInsertCarpetaMail;
    /**
     * Constructor de la clase que instancia CooreosDAO con una instancia de la
     * conexión a la base de datos. También se inicializan las cadenas de
     * inserción y consulta
     *
     * @param conexion instancia de conexión a la base de datos
     */
    public PostgresCorreoDAO(Connection conexion) {
        this.conexion = conexion;
        this.sqlSelectMailPorCuentaUsuario
                = "select 1 from mail, recepcion, usuario_cuenta_de_correo"
                + " where mail.id_mail = recepcion.id_mail "
                + "and recepcion.nombre_cuenta = "
                + "usuario_cuenta_de_correo.nombre_cuenta "
                + "and recepcion.id_servicio_correo = "
                + "usuario_cuenta_de_correo.id_servicio_correo "
                + "and recepcion.nombre_cuenta = ? "
                + "and recepcion.id_servicio_correo = ? "
                + "and mail.fecha_mail = ? "
                + "and recepcion.origen = ?";
        this.sqlInsertMailRecibido
                = "insert into recepcion (id_mail, nombre_cuenta, "
                + "id_servicio_correo, origen, leido) "
                + "values (?,?,?,?,?)";
        this.sqlInsertMail
                = "insert into mail (id_mail, asunto_mail, texto_mail, "
                + "fecha_mail) values(?,?,?,?)";
        this.sqlGetNextValMail
                = "select nextval('mail_id_mail_seq') as id";
        this.sqlSelectCarpetas = "select ccc.nombre_carpeta, ccc.id_carpeta, "
                + "ccc.fecha_creacion_carpeta from carpeta_cuenta_correo ccc "
                + "where ccc.nombre_cuenta = ? and ccc.id_servicio_correo = ?";
        this.sqlSelectMails = "select r.id_mail, asunto_mail, texto_mail, "
                + "fecha_mail, r.nombre_cuenta||url_servicio_correo as "
                + "destino, r.origen from carpeta_cuenta_correo ccc, "
                + "carpeta_cuenta_correo_mail cccm, mail left join "
                + "recepcion r on mail.id_mail = r.id_mail, servicio_correo s "
                + "where ccc.id_carpeta = ? "
                + "and ccc.id_carpeta = cccm.id_carpeta "
                + "and cccm.id_mail = mail.id_mail "
                + "and s.id_servicio_correo = r.id_servicio_correo "
                + "union select e.id_mail, asunto_mail, texto_mail, "
                + "fecha_mail, e.destino, "
                + "e.nombre_cuenta || url_servicio_correo as origen "
                + "from carpeta_cuenta_correo ccc, "
                + "carpeta_cuenta_correo_mail cccm, mail left join "
                + "envio e on mail.id_mail = e.id_mail, "
                + "servicio_correo s where ccc.id_carpeta = ? "
                + "and ccc.id_carpeta = cccm.id_carpeta "
                + "and cccm.id_mail = mail.id_mail "
                + "and e.id_servicio_correo = s.id_servicio_correo";
        this.sqlInsertMailEnvio = "insert into envio (id_mail, nombre_cuenta, "
                + "id_servicio_correo, destino, enviado) values (?,?,?,?,?)";
        this.sqlSelectIdMail = "select m.id_mail "
                + "from mail m left join envio e on e.id_mail = m.id_mail "
                + "left join recepcion r on r.id_mail = m.id_mail "
                + "where (e.destino = ? or r.origen = ?) "
                + "and m.fecha_mail = cast( ? as timestamp) ";
        this.sqlSelectMailPorID = "select * from mail left join "
                + "envio on mail.id_mail = envio.id_mail left join "
                + "recepcion on mail.id_mail = recepcion.id_mail left join "
                + "servicio_correo sc on sc.id_servicio_correo = "
                + "recepcion.id_servicio_correo or "
                + "sc.id_servicio_correo= envio.id_servicio_correo "
                + "where mail.id_mail = ?";
        this.sqlInsertCarpetaMail = "insert into carpeta_cuenta_correo_mail"
                + "(select id_carpeta,? "
                + "from carpeta_cuenta_correo "
                + "where nombre_cuenta like '?' "
                + "and id_servicio_correo = ? "
                + "and nombre_carpeta like 'Bandeja de Salida')";
    }

    /**
     * Implementación del método que guarda los correos recibidos en la base de
     * datos. Verifica que los mails no sean existentes, de ser así no los
     * inserta.
     *
     * @param listaMails lista de mails obtenido por el servidor de correos y
     * que se quieren guardar localmente
     * @return true en caso de que exista al menos un mail nuevo, false en caso
     * que no exista ningún mail nuevo
     * @throws ExcepcionErrorConexionBD se lanza si ocurre algún error de
     * conexión, o al insertar, o al ejecutar alguna consulta
     */
    @Override
    public boolean guardarCorreosRecibidos(List<Recepcion> listaMails)
            throws ExcepcionErrorConexionBD {
        Iterator iterador;
        iterador = listaMails.iterator();
        int idMailTemporal;
        boolean flagMailCargado = false;
        PreparedStatement sqlInsertMailPS = null;
        PreparedStatement sqlInsertRecepcion = null;
        PreparedStatement sqlGetNexValMail = null;
        try {
            sqlInsertMailPS
                    = this.conexion.prepareStatement(this.sqlInsertMail);
            sqlInsertRecepcion
                    = this.conexion.prepareStatement(this.sqlInsertMailRecibido);
            sqlGetNexValMail
                    = this.conexion.prepareStatement(this.sqlGetNextValMail);
            while (iterador.hasNext()) {
                Recepcion mailTemporal = (Recepcion) iterador.next();
                if (!this.existeMailRecibido(mailTemporal)) {
                    this.conexion.setAutoCommit(false);
                    flagMailCargado = true;
                    ResultSet rsNexValMail
                            = sqlGetNexValMail.executeQuery();
                    if (rsNexValMail.next()) {
                        idMailTemporal = rsNexValMail.getInt("id");
                    } else {
                        throw new ExcepcionErrorConexionBD(
                                "Error al obtener el id de la secuencia", null);
                    }
                    //Se inserta en la tabla de mail
                    sqlInsertMailPS.setInt(1, idMailTemporal);
                    sqlInsertMailPS.setString(2, mailTemporal.getAsuntoMail());
                    sqlInsertMailPS.setString(3, mailTemporal.getTextoMail());
                    sqlInsertMailPS.setTimestamp(4, mailTemporal.getFechaMail());
                    sqlInsertMailPS.executeUpdate();
                    //Se inserta en la tabla recepcion
                    sqlInsertRecepcion.setInt(1, idMailTemporal);
                    sqlInsertRecepcion.setString(2,
                            mailTemporal.getDestinoMail().getNombreCuenta());
                    sqlInsertRecepcion.setInt(3,
                            mailTemporal.getDestinoMail().getServicio().
                            getIdServicioCorreo());
                    sqlInsertRecepcion.setString(4,
                            mailTemporal.getOrigenMail());
                    sqlInsertRecepcion.setBoolean(5, false);
                    sqlInsertRecepcion.executeUpdate();
                }
            }
            //Se hace commit de las inserciones
            this.conexion.commit();
        } catch (SQLException ex) {
            try {
                this.conexion.rollback();
            } catch (SQLException ex1) {
                throw new ExcepcionErrorConexionBD(
                        "Error al guardar correo. "
                        + "No se puedo revertir los cambios", ex1);
            }
            throw new ExcepcionErrorConexionBD(
                    "Error al guardar correo en la base de datos", ex);
        } finally {
            try {
                if (sqlInsertMailPS != null) {
                    sqlInsertMailPS.close();
                }
                if (sqlGetNexValMail != null) {
                    sqlGetNexValMail.close();
                }
                if (sqlInsertRecepcion != null) {
                    sqlInsertRecepcion.close();
                }
            } catch (SQLException ex) {
                throw new ExcepcionErrorConexionBD(
                        "Error al guardar correo en la base de datos. "
                        + "\nNo se pudo cerrar la consulta", ex);
            }
        }
        return flagMailCargado;
    }

    /**
     * Método privado que verificca la existencia de un mail recibido. Se
     * utiliza para validar la lista de mails al recebir y verificar que el mail
     * ya exista en la base de datos
     *
     * @param mail instancia de Recepcion que hay que verificar la existencia en
     * la base de datos
     * @return true en caso de que el mail ya exista, false en caso contrario
     * @throws ExcepcionErrorConexionBD se lanza en caso de que ocurra un error
     * al ejecutar la consulta
     */
    private boolean existeMailRecibido(Recepcion mail)
            throws ExcepcionErrorConexionBD {
        PreparedStatement sqlSelectMail = null;
        try {
            sqlSelectMail
                    = this.conexion.prepareStatement(
                            this.sqlSelectMailPorCuentaUsuario);
            ResultSet rs;
            sqlSelectMail.setString(
                    1, mail.getDestinoMail().getNombreCuenta());
            sqlSelectMail.setInt(
                    2, mail.getDestinoMail().getServicio().
                    getIdServicioCorreo());
            sqlSelectMail.setTimestamp(3, mail.getFechaMail());
            sqlSelectMail.setString(4, mail.getOrigenMail());
            rs = sqlSelectMail.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new ExcepcionErrorConexionBD(
                    "Error al verificar la existencia del mail en la cuenta: "
                    + mail.getDestinoMail().getNombreCuenta()
                    + mail.getDestinoMail().getServicio().
                    getUrlServicioCorreo(), ex);
        } finally {
            if (sqlSelectMail != null) {
                try {
                    sqlSelectMail.close();
                } catch (SQLException ex) {
                    throw new ExcepcionErrorConexionBD(
                            "Error al verificar la existencia del mail en la "
                            + "cuenta: "
                            + mail.getDestinoMail().getNombreCuenta()
                            + mail.getDestinoMail().getServicio().
                            getUrlServicioCorreo()
                            + "\n No se pudo cerrar la consulta", ex);
                }
            }
        }
    }

    /**
     * Implementación del método que devuelve la lista de carpetas
     * correspondientes a una cuenta de correo.
     *
     * @param cuenta cuenta de correo de la que se quiere obtener las carpetas
     * @return lista con instancias de {@link modelo.CarpetaCuentaCorreo
     * CarpetaCuentaCorreo}
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error al
     * conectarse a la base de datos.
     */
    @Override
    public List<CarpetaCuentaCorreo> getCarpetas(CuentaDeCorreo cuenta)
            throws ExcepcionErrorConexionBD {
        ArrayList<CarpetaCuentaCorreo> lista = new ArrayList();
        try {
            PreparedStatement sqlSelect
                    = this.conexion.prepareStatement(this.sqlSelectCarpetas);
            ResultSet rs;
            sqlSelect.setString(1, cuenta.getNombreCuenta());
            sqlSelect.setInt(2, cuenta.getServicio().getIdServicioCorreo());
            rs = sqlSelect.executeQuery();
            while (rs.next()) {
                CarpetaCuentaCorreo carpeta = new CarpetaCuentaCorreo();
                carpeta.setCuentaDeCorreo(cuenta);
                carpeta.setIdCarpeta(rs.getInt("id_carpeta"));
                carpeta.setNombreCarpeta(rs.getString("nombre_carpeta"));
                lista.add(carpeta);
            }
        } catch (SQLException ex) {
            throw new ExcepcionErrorConexionBD(
                    "Error al conectarse a la base de datos", ex);
        }
        return lista;
    }

    /**
     * Implementación del método que obtiene la lista de mails de una carpeta
     * pasada por parámetro
     *
     * @param carpeta carpeta de la que se quiere recuperar los mails
     * @return lista de instancias de {@link modelo.CarpetaCuentaCorreo
     * CarpetaCuentaCorreo}
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * a la base de datos local
     */
    @Override
    public List<Mail> getMails(CarpetaCuentaCorreo carpeta)
            throws ExcepcionErrorConexionBD {
        ArrayList<Mail> lista = new ArrayList<>();
        try {
            String strCuenta
                    = carpeta.getCuentaDeCorreo().getNombreCuenta()
                    + carpeta.getCuentaDeCorreo().getServicio().getUrlServicioCorreo();
            PreparedStatement sentenciaSQL
                    = this.conexion.prepareStatement(this.sqlSelectMails);
            ResultSet rs;
            sentenciaSQL.setInt(1, carpeta.getIdCarpeta());
            sentenciaSQL.setInt(2, carpeta.getIdCarpeta());
            rs = sentenciaSQL.executeQuery();
            while (rs.next()) {
                Mail mail = null;
                if (rs.getString("origen").equals(strCuenta)) {
                    Envio mailEnvio = new Envio();
                    mailEnvio.setDestinoMail(rs.getString("destino"));
                    mailEnvio.setOrigenMail(carpeta.getCuentaDeCorreo());
                    mail = mailEnvio;
                } else if (rs.getString("destino").equals(strCuenta)) {
                    Recepcion mailRecepcion = new Recepcion();
                    mailRecepcion.setDestinoMail(carpeta.getCuentaDeCorreo());
                    mailRecepcion.setOrigenMail(rs.getString("origen"));
                    mail = mailRecepcion;
                }
                if (mail != null) {
                    mail.setIdMail(rs.getInt("id_mail"));
                    mail.setFechaMail(rs.getTimestamp("fecha_mail"));
                    mail.setAsuntoMail(rs.getString("asunto_mail"));
                    mail.setTextoMail(rs.getString("texto_mail"));
                }
                lista.add(mail);
            }
        } catch (SQLException ex) {
            throw new ExcepcionErrorConexionBD(
                    "Error al recuperar los mail de la base de datos", ex);
        }
        return lista;
    }

    /**
     *
     * @param mailEnvio
     * @return
     * @throws ExcepcionErrorConexionBD
     */
    @Override
    public boolean guardarCorreoEnvio(Envio mailEnvio)
            throws ExcepcionErrorConexionBD {
        try {
            PreparedStatement stInsertMail
                    = this.conexion.prepareStatement(this.sqlInsertMail);
            PreparedStatement stInsertEnvio
                    = this.conexion.prepareStatement(this.sqlInsertMailEnvio);
            PreparedStatement stGetNexValMail
                    = this.conexion.prepareStatement(this.sqlGetNextValMail);
            PreparedStatement stInsertCarpetaMail 
                    = this.conexion.prepareStatement(this.sqlInsertCarpetaMail);
            int idMail;
            ResultSet rsNexValMail
                    = stGetNexValMail.executeQuery();
            if (rsNexValMail.next()) {
                idMail = rsNexValMail.getInt("id");
            } else {
                throw new ExcepcionErrorConexionBD(
                        "Error al obtener el id de la secuencia", null);
            }
            stInsertMail.setInt(1, idMail);
            stInsertMail.setString(2, mailEnvio.getAsuntoMail());
            stInsertMail.setString(3, mailEnvio.getTextoMail());
            stInsertMail.setTimestamp(4, mailEnvio.getFechaMail());
            stInsertEnvio.setInt(1, idMail);
            stInsertEnvio.setString(2, mailEnvio.getOrigenMail().getNombreCuenta());
            stInsertEnvio.setInt(3, mailEnvio.getOrigenMail().getServicio().getIdServicioCorreo());
            stInsertEnvio.setString(4, mailEnvio.getDestino());
            stInsertEnvio.setBoolean(5, false);
            stInsertCarpetaMail.setInt(1, idMail);
            stInsertCarpetaMail.setString(2, mailEnvio.getOrigenMail().getNombreCuenta());
            stInsertCarpetaMail.setInt(3, mailEnvio.getOrigenMail().getServicio().getIdServicioCorreo());
            this.conexion.setAutoCommit(false);
            stInsertMail.executeUpdate();
            stInsertEnvio.executeUpdate();
            stInsertCarpetaMail.executeUpdate();
            this.conexion.commit();
        } catch (SQLException ex) {
            try {
                this.conexion.rollback();
            } catch (SQLException ex1) {
                throw new ExcepcionErrorConexionBD("Error al revertir los "
                        + "cambios\n No se pudo guardar el correo a enviar", ex1);
            }
            throw new ExcepcionErrorConexionBD("Error al guardar el mail en "
                    + "la base de datos", ex);
        }
        return true;
    }

    /**
     *
     * @param cuenta
     * @param fecha
     * @return
     * @throws ExcepcionErrorConexionBD
     */
    @Override
    public int getIdCorreo(String cuenta, Timestamp fecha)
            throws ExcepcionErrorConexionBD {
        int id = -1;
        try {
            PreparedStatement stSelectId
                    = this.conexion.prepareStatement(this.sqlSelectIdMail);
            stSelectId.setString(1, cuenta);
            stSelectId.setString(2, cuenta);
            stSelectId.setString(3, fecha.toString());
            ResultSet rs = stSelectId.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_mail");
            }
        } catch (SQLException ex) {
            throw new ExcepcionErrorConexionBD("Error al recuperar "
                    + "el id del mail", ex);
        }
        return id;
    }

    @Override
    public Mail getCorreo(int idMail) throws ExcepcionErrorConexionBD {
        Mail mail = null;
        try {
            PreparedStatement stSelectId
                    = this.conexion.prepareStatement(this.sqlSelectMailPorID);
            stSelectId.setInt(1, idMail);
            ResultSet rs = stSelectId.executeQuery();
            if (rs.next()) {
                if ("".equals(rs.getString("destino"))) {
                    Recepcion mailRecibido = new Recepcion();
                    mailRecibido.setDestinoMail(new CuentaDeCorreo(
                            rs.getString(11),
                            new ServicioCorreo(rs.getInt(12),
                                    rs.getString(16))));
                    mailRecibido.setLeido(rs.getBoolean("leido"));
                    mailRecibido.setOrigenMail(rs.getString(13));
                    mail = mailRecibido;
                } else {
                    Envio mailEnviado = new Envio();
                    mailEnviado.setOrigenMail(new CuentaDeCorreo(
                            rs.getString(6),
                            new ServicioCorreo(rs.getInt(15),
                                    rs.getString(16))));
                    mailEnviado.setEnviado(rs.getBoolean("enviado"));
                    mailEnviado.setDestinoMail(rs.getString(8));
                    mail = mailEnviado;
                }
            }
            mail.setAsuntoMail(rs.getString("asunto_mail"));
            mail.setFechaMail(rs.getTimestamp("fecha_mail"));
            mail.setIdMail(rs.getInt(1));
            mail.setTextoMail(rs.getString("texto_mail"));
        } catch (SQLException ex) {
            throw new ExcepcionErrorConexionBD("Error al obtener mail", ex);
        }
        return mail;
    }

}
