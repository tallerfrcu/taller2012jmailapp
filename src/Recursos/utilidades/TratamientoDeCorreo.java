/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this temultiPartlate file, choose Tools | TemultiPartlates
 * and open the temultiPartlate in the editor.
 */
package Recursos.utilidades;

import Excepciones.ExcepcionTratamientoCorreo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

/**
 * Clase que se utiliza para tratar el contenido del correo.
 *
 * @author Accornero, Fontana, García, Pascal
 */
public class TratamientoDeCorreo {

    public static String getCuerpoMensaje(Message mensaje)
            throws ExcepcionTratamientoCorreo {
        try {
            String cuerpoMensaje = null;
            if (mensaje.isMimeType("text/*")) {
                cuerpoMensaje = (String) mensaje.getContent();
            } else if (mensaje.isMimeType("multipart/*")) {
                cuerpoMensaje = "Contenido no tratable";
            } else {
                cuerpoMensaje = "Contenido no tratable";
            }
            return cuerpoMensaje;
        } catch (IOException | MessagingException ex) {
            throw new ExcepcionTratamientoCorreo(
                    "Error al tratar el cuerpo del correo", ex);
        }
    }

    private static String getContenidoMensaje(Message mensaje) {
        try {
            if (mensaje.isMimeType("text/*")) {
                if (mensaje.isMimeType("text/plain")) {
                    return "text/plain";
                } else if (mensaje.isMimeType("text/html")) {
                    return "text/html";
                } else if (mensaje.isMimeType("text/rtf")) {
                    return "text/rtf";
                }
            } else if (mensaje.isMimeType("multipart/*")) {

            }
        } catch (MessagingException ex) {

        }
        return null;
    }

    private static String getMultipart(Part parte) 
            throws IOException, MessagingException {
        String cuerpoMensaje = null;
        Multipart multiPart = (Multipart) parte.getContent();
        for (byte i = 0; i < multiPart.getCount(); i++) {
            Part parteMensaje = multiPart.getBodyPart(i);
            if (parteMensaje.isMimeType("text/plain")) {
                if (cuerpoMensaje == null) {
                    cuerpoMensaje = TratamientoDeCorreo.getMultipart(parteMensaje);
                }
            } else if (parteMensaje.isMimeType("text/html")) {
                String s = TratamientoDeCorreo.getMultipart(parteMensaje);
                if (s != null) {
                    return s;
                }
            } else {
                return TratamientoDeCorreo.getMultipart(parteMensaje);
            }
        }
        return null;
    }
}
