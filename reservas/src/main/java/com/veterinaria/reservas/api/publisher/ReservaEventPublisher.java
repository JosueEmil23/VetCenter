package com.veterinaria.reservas.api.publisher;

import com.veterinaria.reservas.api.dto.ReservCreadaEvent;
import com.veterinaria.reservas.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservaEventPublisher {

    private final RabbitTemplate rabbitTemplate;



    private static final String ROUTING_KEY = "reserva.creada.routing.key";

    public void publicarReservaCreada(ReservCreadaEvent event) {
        log.info(">>>> Iniciando publicación del evento para la reserva ID: {}", event.idReserva());

        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    ROUTING_KEY,
                    event
            );
            log.info(">>>> Evento publicado exitosamente en el Exchange: {}", RabbitMQConfig.EXCHANGE_NAME);
        } catch (Exception e) {
            log.error("X Escándalo: No se pudo enviar el evento a RabbitMQ: {}", e.getMessage());
        }
    }



}
