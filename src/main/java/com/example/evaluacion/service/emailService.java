package com.example.evaluacion.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String enviarCorreoTarea(String destinatario, String Title){
        try{
            TimeUnit.MINUTES.sleep(1);
            String asunto = "¡Bienvenid@ " ;
            String cuerpo = ""
                + "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\r\n"
                + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\r\n"
                + "  <h1 style=\"font-size: 24px; font-weight: bold; color: #333333;\">¡Hola!</h1>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Se te ha asignado una tarea.</p>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Tú tarea es la siguiente: " + Title +"</p>\r\n"
                + "</div>\r\n"
                + "<div style=\"text-align: center; margin-top: 20px;\">\r\n"
                + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Términos y condiciones</a> | \r\n"
                + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Política de privacidad</a>\r\n"
                + "</div>\r\n"
                + "</body>";

            

            var retorno=enviarCorreo(destinatario,asunto,cuerpo);
            if(retorno) {
                return "se envió correctamente";
            }else {
                return "No se pudo envíar";
            }

        }catch (Exception e) {
            // TODO: handle exception
            return "Error al envíar "+e.getMessage();
        }
    }

    public String tareasCercanasAlVencimiento(String destinatario){
        try{
            String asunto = "Tú tarea esta a punto de vencer !!Date prisa!!";
            String cuerpo = ""
                + "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\r\n"
                + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\r\n"
                + "  <h1 style=\"font-size: 24px; font-weight: bold; color: #333333;\">¡Hola!</h1>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Recuerda terminar tu tarea a tiempo.</p>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Asi podras evitar cualquier inconveniente.</p>\r\n"
                + "</div>\r\n"
                + "<div style=\"text-align: center; margin-top: 20px;\">\r\n"
                + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Términos y condiciones</a> | \r\n"
                + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Política de privacidad</a>\r\n"
                + "</div>\r\n"
                + "</body>";

            var retorno=enviarCorreo(destinatario,asunto,cuerpo);
            if(retorno) {
                return "se envió correctamente";
            }else {
                return "No se pudo envíar";
            }

        }catch (Exception e) {
            // TODO: handle exception
            return "Error al envíar "+e.getMessage();
        }
    }


    public String tareasVencidas(String destinatario){
        try{
            String asunto = "Tu tarea ha vencido";
            String cuerpo = ""
                + "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\r\n"
                + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\r\n"
                + "  <h1 style=\"font-size: 24px; font-weight: bold; color: #333333;\">¡Hola!</h1>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Lo sentimos, tu tarea ya se ha vencido :c.</p>\r\n"
                + "</div>\r\n"
                + "<div style=\"text-align: center; margin-top: 20px;\">\r\n"
                + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Términos y condiciones</a> | \r\n"
                + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Política de privacidad</a>\r\n"
                + "</div>\r\n"
                + "</body>";


            

            var retorno=enviarCorreo(destinatario,asunto,cuerpo);
            if(retorno) {
                return "se envió correctamente";
            }else {
                return "No se pudo envíar";
            }

        }catch (Exception e) {
            // TODO: handle exception
            return "Error al envíar "+e.getMessage();
        }
    }


    public boolean enviarCorreo(String destinatario,String asunto,String cuerpo) throws MessagingException {
            try {
                MimeMessage message=javaMailSender.createMimeMessage();
                MimeMessageHelper helper=new MimeMessageHelper(message,true);
                
                helper.setTo(destinatario);
                helper.setSubject(asunto);
                helper.setText(cuerpo,true);
                
                javaMailSender.send(message);
                return true;
            }catch (Exception e) {

                return false;
            }
            
    }

    
    
}
