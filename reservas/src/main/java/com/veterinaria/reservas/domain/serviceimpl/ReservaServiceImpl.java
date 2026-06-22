package com.veterinaria.reservas.domain.serviceimpl;

import com.veterinaria.reservas.api.client.MascotaFeignClient;
import com.veterinaria.reservas.api.dto.MascotaDTO;
import com.veterinaria.reservas.api.dto.ReservCreadaEvent;
import com.veterinaria.reservas.api.dto.ReservaDTO;
import com.veterinaria.reservas.api.publisher.ReservaEventPublisher;
import com.veterinaria.reservas.data.entity.Reserva;
import com.veterinaria.reservas.data.repository.ReservaRepository;
import com.veterinaria.reservas.domain.mapper.ReservaMapper;
import com.veterinaria.reservas.domain.service.ReservaService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private MascotaFeignClient mascotaFeignClient;

    @Autowired
    private ReservaMapper reservaMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ReservaEventPublisher reservaEventPublisher;

    @Override
    public List<ReservaDTO> listarTodas() {
        return reservaRepository.findAll().stream()
                .map(reserva -> {
                    MascotaDTO mascota = null;
                    try {
                        mascota = mascotaFeignClient.obtenerMascotaPorId(reserva.getMascotaId());
                    } catch (Exception e) {
                        // En caso de que falle el microservicio remoto, se deja pasar en null
                    }
                    return reservaMapper.toDTO(reserva, mascota);
                })
                .toList();
    }

    @Override
    public ReservaDTO guardar(Reserva reserva) {
        MascotaDTO mascota;

        try {
            mascota = mascotaFeignClient.obtenerMascotaPorId(reserva.getMascotaId());
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException("Fallo real de OpenFeign: " + e.toString() + " -> " + e.getMessage());
        }

        if (mascota == null) {
            throw new RuntimeException("Error: La mascota con ID " + reserva.getMascotaId() + " no existe. Registro abortado.");
        }

        Reserva nuevaReserva = reservaRepository.save(reserva);

        ReservCreadaEvent evento = new ReservCreadaEvent(
                nuevaReserva.getId(),
                mascota.id(),
                mascota.nombre(),
                mascota.duenoId(),
                nuevaReserva.getFechaHora(),
                nuevaReserva.getMotivo()
        );

        reservaEventPublisher.publicarReservaCreada(evento);

        return reservaMapper.toDTO(nuevaReserva, mascota);
    }



}