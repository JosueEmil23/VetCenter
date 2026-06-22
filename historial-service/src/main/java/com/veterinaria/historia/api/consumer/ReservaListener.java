package com.veterinaria.historia.api.consumer;


import com.veterinaria.historia.api.event.ReservaCreadaEvent;
import com.veterinaria.historia.data.entity.Historial;
import com.veterinaria.historia.data.repository.HistorialRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservaListener {


    @Autowired
    private HistorialRepository historialRepository;

    @RabbitListener(queues = "reserva.creada.historial.queue")
    public void recibirNuevaReserva(ReservaCreadaEvent evento) {
        System.out.println("--> [RabbitMQ] Mensaje recibido con éxito para la mascota ID: " + evento.mascotaId());


        Historial historial = new Historial();
        historial.setReservaId(evento.idReserva());
        historial.setMascotaId(evento.mascotaId());
        historial.setMotivo(evento.motivo());
        historial.setFechaAtencion(evento.fechaHora());
        historial.setDiagnostico("Pendiente de consulta");
        historial.setTratamiento("Pendiente");


        historialRepository.save(historial);
        System.out.println("--> [Base de Datos] Ficha de historial clínico inicial creada de forma asíncrona.");
    }


}
