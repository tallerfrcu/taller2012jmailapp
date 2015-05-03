/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import Excepciones.ExcepcionArchivoDePropiedadesNoEncontrado;
import com.sun.mail.util.MailSSLSocketFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
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
    public List<Recepcion> recibirCorreos(CuentaDeCorreo cuenta) {
        //ArrayList<Recepcion> listaMails =  new ArrayList();
        try {
            Properties prop = new Properties();
            // Deshabilitamos TLS
            prop.setProperty("mail.pop3.starttls.enable", "false");
            prop.setProperty("mail.protocol.ssl.trust", "pop.gmail.com");
            prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.setProperty("mail.pop3.socketFactory.fallback", "false");
// Puerto 995 para conectarse.
            prop.setProperty("mail.pop3.port", "995");
            prop.setProperty("mail.pop3.socketFactory.port", "995");

            MailSSLSocketFactory socketFactory;
            socketFactory = new MailSSLSocketFactory();
            socketFactory.setTrustAllHosts(true);
            this.propiedadesGMail.put(
                    "mail.pop3.socketFactory.class", socketFactory);
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
                System.out.println("De: " + mensajes[i].getFrom()[i].toString());
                System.out.println("Asunto: " + mensajes[i].getSubject());
            }
        } catch (NoSuchProviderException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (MessagingException ex) {
            System.err.println("Error: " + ex.getMessage());
            ex.printStackTrace(System.err);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(ServicioGMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
