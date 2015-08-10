/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.postgres;

import Excepciones.ExcepcionErrorConexionBD;
import Excepciones.ExcepcionLogIn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CarpetaCuentaCorreo;
import modelo.CuentaDeCorreo;
import modelo.ServicioCorreo;
import modelo.Usuario;
import persistencia.IUsuarioDAO;

/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class PostgresUsuarioDAO implements IUsuarioDAO {

    /**
     * Instancia de la conexión a la base de datos
     */
    private Connection conexion;
    /**
     * string de la consulta sql que selecciona el usuario y contraseña
     */
    private String sqlSelectUsuario;
    /**
     * String de inserción de usuario nuevo en la base de datos
     */
    private String sqlInsertUsuario;
    /**
     * String de la sentencia sql para saber si existe un nombre de usuario en
     * uso en la base de datos
     */
    private String sqlSelectExisteUsuario;

    /**
     * Constructor de la clase que instancia un objeto PostgresUsuarioDAO
     * inicializando la conexión a la base de datos y generando los string de
     * consulta que se utilizarán.
     *
     * @param conexion instancia de conexión a la base de datos
     */
    public PostgresUsuarioDAO(Connection conexion) {
        this.conexion = conexion;
        this.sqlSelectUsuario = "select usuario.*, cuenta_de_correo.nombre_cuenta, "
                + "contrasena_cuenta, servicio_correo.id_servicio_correo, "
                + "url_servicio_correo from usuario "
                + "left join usuario_cuenta_de_correo on "
                + "usuario.nombre_de_usuario = usuario_cuenta_de_correo.nombre_de_usuario "
                + "left join cuenta_de_correo on "
                + "cuenta_de_correo.nombre_cuenta = usuario_cuenta_de_correo.nombre_cuenta "
                + "left join servicio_correo on "
                + "servicio_correo.id_servicio_correo = "
                + "usuario_cuenta_de_correo.id_servicio_correo "
                + "where usuario.nombre_de_usuario = ? "
                + "and contrasena_usuario = ?";
        this.sqlInsertUsuario = "insert into usuario "
                + "(nombre_de_usuario, contrasena_usuario) values(?,?)";
        this.sqlSelectExisteUsuario = "select 1 from usuario "
                + "where nombre_de_usuario like ?";
    }

    /**
     * Método que verifica que el usuario y contraseña de la aplicación
     * coincidan. Si coinciden, devuelve una instancia de usuario con sus
     * variables de instanacias inicializadas. En caso contrario, lanza una
     * excepción de logueo
     *
     * @param nombreUsuario nombre de usuario de la aplicación
     * @param contrasenaUsuario contraseña del usuario de la aplicación
     * @return instancia de usuario con sus datos instanciados
     * @throws ExcepcionLogIn se lanza si no coincide el usuario y la contraseña
     * o si no se encuentra el usuario en la base de datos
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error al
     * conectarse a la base de datos local
     */
    @Override
    public Usuario logInUsuario(String nombreUsuario, String contrasenaUsuario)
            throws ExcepcionLogIn, ExcepcionErrorConexionBD {
        PreparedStatement sentenciaSQL = null;
        Usuario usuario = null;
        try {
            ResultSet rs;
            Statement st;
            st = this.conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sentenciaSQL = this.conexion.prepareStatement(this.sqlSelectUsuario);
            sentenciaSQL.setString(1, nombreUsuario);
            sentenciaSQL.setString(2, contrasenaUsuario);
            //rs = sentenciaSQL.executeQuery();
            rs = st.executeQuery(sentenciaSQL.toString());
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setNombreDeUsuario(rs.getString("nombre_de_usuario"));
                usuario.setContrasenaUsuario(rs.getString("contrasena_usuario"));
                rs.previous();
                while (rs.next()) {
                    if ((rs.getString("nombre_cuenta")) == null) {
                        continue;
                    } else {
                        CuentaDeCorreo correoUsuario = new CuentaDeCorreo();
                        ServicioCorreo servicioCorreo = new ServicioCorreo();
                        correoUsuario.setNombreCuenta(
                                rs.getString("nombre_cuenta"));
                        correoUsuario.setContrasenaCuenta(
                                rs.getString("contrasena_cuenta"));
                        servicioCorreo.setIdServicioCorreo(
                                rs.getInt("id_servicio_correo"));
                        servicioCorreo.setUrlServicioCorreo(
                                rs.getString("url_servicio_correo"));
                        correoUsuario.setServicio(servicioCorreo);
                        usuario.getCuentasCorreo().add(correoUsuario);
                    }
                }
            } else {
                throw new ExcepcionLogIn();
            }
        } catch (SQLException ex) {
            throw new ExcepcionErrorConexionBD(
                    "Error al conectarse a la base de datos local", ex);
        }
        return usuario;
    }

    /**
     * Método que genera un nuevo usuario de la aplicación.
     *
     * @param nombeUsuario nombre de usuario usado para loguearse a la
     * aplicación
     * @param contrasenaUsuario contraseña del usuario, usada para loguearse a
     * la aplicación
     * @return true si se guarda el usuario con éxito
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * o si ya existe en uso el nombre de usuario que se quiere registrar
     */
    @Override
    public boolean generarUsuario(String nombeUsuario, String contrasenaUsuario)
            throws ExcepcionErrorConexionBD {
        try {
            PreparedStatement sqlInsert
                    = this.conexion.prepareStatement(this.sqlInsertUsuario);
            if (!this.existeUsuario(nombeUsuario)) {
                sqlInsert.setString(1, nombeUsuario);
                sqlInsert.setString(2, contrasenaUsuario);
                sqlInsert.executeUpdate();
            } else {
                throw new ExcepcionErrorConexionBD(
                        "El nombre de usuario " + nombeUsuario
                        + " ya existe en uso");
            }
        } catch (SQLException ex) {
            throw new ExcepcionErrorConexionBD(ex);
        }
        return true;
    }

    /**
     * Método que valida que un nombre de usuario exista en la base de datos
     *
     * @param nombreUsuario nombre de usuario que se quiere saber si existe en
     * la base de datos
     * @return true si existe el nombre de usuario, false en caso contrario
     * @throws ExcepcionErrorConexionBD se lanza si ocurre un error de conexión
     * a la base de datos local
     */
    @Override
    public boolean existeUsuario(String nombreUsuario) throws ExcepcionErrorConexionBD {
        try {
            PreparedStatement sqlSelect
                    = this.conexion.prepareStatement(this.sqlSelectExisteUsuario);
            sqlSelect.setString(1, nombreUsuario);
            ResultSet rs = sqlSelect.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new ExcepcionErrorConexionBD(ex);
        }
    }

}
