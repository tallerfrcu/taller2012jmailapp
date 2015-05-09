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
import java.util.Iterator;
import java.util.List;
import modelo.Recepcion;
import persistencia.ICorreos;

/**
 * Clase que implementa la interfaz ICorreo y define los métodos para tratar los
 * correos contra la base de datos
 * @author Accornero, Fontana, García, Pascal
 */
public class CorreosDAO implements ICorreos {

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
     * Constructor de la clase que instancia CooreosDAO con una instancia de la
     * conexión a la base de datos. También se inicializan las cadenas de
     * inserción y consulta
     *
     * @param conexion instancia de conexión a la base de datos
     */
    public CorreosDAO(Connection conexion) {
        this.conexion = conexion;
        this.sqlSelectMailPorCuentaUsuario
                = "select 1 from mail, recepcion where "
                + "mail.id_mail = recepcion.id_mail "
                + "and recepcion.nombre_cuenta = "
                + "usuario_cuenta_de_correo.nombre_cuenta "
                + "and recepcion.id_servicio_correo = "
                + "usuario_cuenta_de_correo.id_servicio_correo"
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
    }
    /**
     * Implementación del método que guarda los correos recibidos en la base
     * de datos. Verifica que los mails no sean existentes, de ser así no los
     * inserta. 
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
                    //Se hace commit de ambas inserciones
                    this.conexion.commit();
                }
            }

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
}
