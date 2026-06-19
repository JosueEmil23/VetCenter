package com.veterinaria.reservas.domain.serviceimpl;

import com.veterinaria.reservas.api.client.MascotaFeignClient;
import com.veterinaria.reservas.api.dto.MascotaDTO;
import com.veterinaria.reservas.api.dto.ReservaDTO;
import com.veterinaria.reservas.data.entity.Reserva;
import com.veterinaria.reservas.data.repository.ReservaRepository;
import com.veterinaria.reservas.domain.mapper.ReservaMapper;
import com.veterinaria.reservas.domain.service.ReservaService;
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
            throw new RuntimeException("Error: No se pudo verificar la mascota. Asegúrate de que el microservicio de Dueños-Mascotas esté activo.");
        }

        if (mascota == null) {
            throw new RuntimeException("Error: La mascota con ID " + reserva.getMascotaId() + " no existe. Registro abortado.");
        }

        Reserva nuevaReserva = reservaRepository.save(reserva);
        return reservaMapper.toDTO(nuevaReserva, mascota);
    }
}