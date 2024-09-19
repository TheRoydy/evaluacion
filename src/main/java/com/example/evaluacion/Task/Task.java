package com.example.evaluacion.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.evaluacion.models.entidad;
import com.example.evaluacion.service.emailService;
import com.example.evaluacion.service.entidadService;

@Component
public class Task {
    
     @Autowired
    private entidadService data;

    @Autowired
    private emailService email;


    @Scheduled(cron = "0 53 15 * * *") //cron ="0 45 15 * * *" 0 son los segundos, 45 los minutos, 15 la hora, luego es dia, mes, dia de la semana MON hasta SUN
    public void sendNotificationcron() {
        var listaEntidad=data.cambiarTipoDocumento();
        for (registro registro : listaRegistro) {
            System.out.println("usuario que requiere actualizar documento "+ 
            registro.getNombreCompleto());
            email.cambiarTipoDocumento(registro.getCorreoElectronico(),registro.getNombreCompleto());
        }
    }

    @Scheduled(cron = "0 53 15 * * *")
    public void enviarNotificacionRegistroTarea() {
        var listaRegistro=data.enviarCorreoTarea();
        for (entidad entidad : listaEntidad) {
            System.out.println("Tareas registradas: "+ 
            entidad.getTitle());
            email.enviarCorreoTarea(entidad.getAssigned_to(),entidad.getTitle());
        }
    }

    @Scheduled(cron = "0 53 15 * * *") 
    public void sendNotificationActualizarPassword() {
        var listaRegistro=data.actualizarPassword();
        for (registro registro : listaRegistro) {
            System.out.println("Usuario que requiere actualizar contraseña: "+ 
            registro.getNombreCompleto());
            email.actualizarPassword(registro.getCorreoElectronico());
        }
    }
    
    @Scheduled(cron = "0 15 13 * * *")
    public void sendIniciarSesion() {
        var listaRegistro=data.iniciosesionNotificar();
        for (registro registro : listaRegistro) {
            System.out.println("Usuario que debe iniciar sesión: "+ 
            registro.getNombreCompleto());
            email.iniciosesionNotificar(registro.getCorreoElectronico());
        }
    }
}
