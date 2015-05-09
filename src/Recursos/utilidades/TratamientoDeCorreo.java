/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos.utilidades;

import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Clase que se utiliza para tratar el contenido del correo.
 * @author Accornero, Fontana, García, Pascal
 */
public class TratamientoDeCorreo {
    public String getCuerpoMensajeTextoPlano(Message mensaje){
        String textoMensaje = "";
        try {
            if(mensaje.isMimeType("text/plain")){
                
            } 
        } catch (MessagingException ex) {
            
        }
        return textoMensaje;
    }
    public String getContenidoMensaje(Message mensaje){
        try {
            if(mensaje.isMimeType("text/*")){
                if(mensaje.isMimeType("text/plain")){
                    return "text/plain";
                } else if(mensaje.isMimeType("text/html")){
                    return "text/html";
                }
            } else if(mensaje.isMimeType("multipart/*")){
                
            }
        } catch (MessagingException ex) {
            
        }
    }
}
