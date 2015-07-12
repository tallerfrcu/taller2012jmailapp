/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import Excepciones.ExcepcionDeServiciosCorreo;
import Excepciones.ExcepcionTratamientoCorreo;
import Recursos.utilidades.TratamientoDeCorreo;
import com.sun.mail.util.MailSSLSocketFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.CuentaDeCorreo;
import modelo.Envio;
import modelo.Recepcion;

/**
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class ServicioGMail implements IServiciosCorreo {

    /**
     * instancia de propiedades que contiene las propiedades de GMail. Se
     * levantan las propiedades del archivo propiedadesGMail.properties
     */
    private Properties propiedadesGMail;

    /**
     * Constructor de la clase que instancia un servicio de GMail inicializando
     * las propiedades
     *
     * @throws Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado Excepción
     * que se lanza en caso de ocurrir un error al levantar el archivo de
     * propiedades de GMail
     */
    public ServicioGMail() throws ExcepcionArchivoDePropiedadesNoEncontrado {
        try {
            try (FileInputStream f = new FileInputStream(
                    "C:/Users/Dev/Desktop/Ff/Taller/taller2012jmailapp/src/Recursos/utilidades/propiedadesGMail.properties")) {
                this.propiedadesGMail = new Properties();
                this.propiedadesGMail.load(f);
            }
        } catch (FileNotFoundException ex) {
            throw new ExcepcionArchivoDePropiedadesNoEncontrado(ex, "Error al "
                    + "inicializar el archivo de propiedades de GMail");
        } catch (IOException ex) {
            throw new ExcepcionArchivoDePropiedadesNoEncontrado(ex, "Error al "
                    + "inicializar el archivo de propiedades de GMail");
        }
    }

    /**
     * Implementación del método para enviar un e-mail.
     *
     * @param mail instancia de Envio que contiene el correo a enviar.
     */
    @Override
    public boolean enviarCorreo(Envio mail) {
        try {
            Session sesion = Session.getInstance(this.propiedadesGMail);
            MimeMessage mensajeMime = new MimeMessage(sesion);
            mensajeMime.setFrom(new InternetAddress(
                    mail.getOrigenMail().getNombreCuenta()
                    + mail.getOrigenMail().getServicio().getUrlServicioCorreo()));
            mensajeMime.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(
                            mail.getDestinoMail()));
            mensajeMime.setSubject(mail.getAsuntoMail());
            mensajeMime.setText(mail.getTextoMail());
            MailSSLSocketFactory socketFactory;
            socketFactory = new MailSSLSocketFactory();
            socketFactory.setTrustAllHosts(true);
            this.propiedadesGMail.put(
                    "mail.smtp.ssl.socketFactory", socketFactory);

            Transport transport = sesion.getTransport("smtp");
            transport.connect(mail.getOrigenMail().getNombreCuenta()
                    + mail.getOrigenMail().getServicio().getUrlServicioCorreo(),
                    mail.getOrigenMail().getContrasenaCuenta());
            transport.sendMessage(mensajeMime, mensajeMime.getAllRecipients());
            transport.close();
        } catch (GeneralSecurityException | NoSuchProviderException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (MessagingException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return true;
    }

    /**
     *
     * @param cuenta
     * @return
     */
    @Override
    public List<Recepcion> recibirCorreos(CuentaDeCorreo cuenta) 
            throws ExcepcionDeServiciosCorreo{
        ArrayList<Recepcion> listaMails =  new ArrayList();
        try {
            MailSSLSocketFactory socketFactory;
            socketFactory = new MailSSLSocketFactory();
            socketFactory.setTrustedHosts(new String[] {"pop.gmail.com"});
            socketFactory.setTrustAllHosts(true);
            this.propiedadesGMail.put(
                    "mail.pop3.socketFactory", socketFactory);
            Session sesion = Session.getInstance(this.propiedadesGMail);
            sesion.setDebug(false);
            Store store = sesion.getStore("pop3");
            String mail = cuenta.getNombreCuenta()
                    + cuenta.getServicio().getUrlServicioCorreo();
            store.connect("pop.gmail.com", mail, cuenta.getContrasenaCuenta());
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Message[] mensajes = folder.getMessages();
            for (byte i = 0; i < mensajes.length; i++) {
                Recepcion mailRecibido = new Recepcion();
                mailRecibido.setOrigenMail(mensajes[i].getFrom()[0].toString());
                mailRecibido.setDestinoMail(cuenta);
                mailRecibido.setAsuntoMail(mensajes[i].getSubject());
                mailRecibido.setFechaMail(
                        new Timestamp(((MimeMessage)mensajes[i]).getSentDate().getTime()));
                String chingo = TratamientoDeCorreo.getCuerpoMensaje(mensajes[i]);
                mailRecibido.setTextoMail(chingo);
                mailRecibido.setLeido(false);
                listaMails.add(mailRecibido);
            }
        } catch (NoSuchProviderException ex) {
            throw new ExcepcionDeServiciosCorreo(
                    "Error al conectarse con la cuenta", ex);
        } catch (MessagingException ex) {
            throw new ExcepcionDeServiciosCorreo(
                    "Error al recibir los mensajes", ex);
        } catch (GeneralSecurityException ex) {
            throw new ExcepcionDeServiciosCorreo(
                    "Error de seguridad al intentar recibir los correos", ex);
        } catch (ExcepcionTratamientoCorreo ex) {
            throw new ExcepcionDeServiciosCorreo(
                    "Error al extraer el contenido del mail", ex);
        }
        return listaMails;
    }

}
