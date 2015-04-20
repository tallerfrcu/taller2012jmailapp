/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos.utilidades;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * Clase que representa un pool de conexiones, utilizado para gestionar el
 * objeto DataSource que contiene los datos de conexión a la base de datos.
 * @author Accornero, Fontana, García, Pascal
 */
public class PoolDeConexiones {
    /**
     * Pool de conexiones
     */
    private DataSource dataSource;
    /**
     * Archivo de propiedades que contiene los datos de conexión a la 
     * base de datos.
     */
    private Properties propiedades;
    /**
     * driver de conexión a la base de datos
     */
    private String driver;
    /**
     * Constructor de la clase que instancia un pool de conexiones, instanciando
     * un DataSource con los datos de la conexión a la base de datos que se
     * encuentra en el archivo propiedadesPostgres.properties
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado Excepcion
     * que se lanza si ocurre un error al abrir el archivo de propiedades
     */
    public PoolDeConexiones() throws ExcepcionArchivoDePropiedadesNoEncontrado {
        this.inicializarPropiedades();
        try {
            this.dataSource = 
                    BasicDataSourceFactory.createDataSource(this.propiedades);
            this.driver = this.propiedades.getProperty("driver");
        } catch (Exception ex) {
            throw new ExcepcionArchivoDePropiedadesNoEncontrado(ex);
        }
        
    }
    /**
     * Método privado que abre el archivo de propiedades y se lo asigna a la 
     * instancia privada de propiedades.
     * @throws ExcepcionArchivoDePropiedadesNoEncontrado Excepción que se lanza
     * si ocurre un error al buscar el archivo o al intentar abrirlo
     */
    private void inicializarPropiedades() throws 
            ExcepcionArchivoDePropiedadesNoEncontrado{
        try {
            FileInputStream f =new FileInputStream("C:/propiedadesPostgres.properties");
            propiedades.load(f);
        } catch (FileNotFoundException  ex) {
            throw new ExcepcionArchivoDePropiedadesNoEncontrado(ex);
        } catch (IOException ex) {
            throw new ExcepcionArchivoDePropiedadesNoEncontrado(ex);
        }
    }
    /**
     * Método que devuelve la instancia de DataSource
     * @return instancia de DataSource
     */
    public DataSource getDataSource() {
        return this.dataSource;
    }
    /**
     * Método que retorna el driver de conexión a la base de datos
     * @return driver de conexión a la base de datos
     */
    public String getDriver() {
        return this.driver;
    }
    
}
