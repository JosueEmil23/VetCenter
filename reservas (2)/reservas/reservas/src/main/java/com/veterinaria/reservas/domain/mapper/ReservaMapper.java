package com.veterinaria.reservas.domain.mapper;

import com.veterinaria.reservas.api.dto.MascotaDTO;
import com.veterinaria.reservas.api.dto.ReservaDTO;
import com.veterinaria.reservas.data.entity.Reserva;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    public ReservaDTO toDTO(Reserva reserva, MascotaDTO mascotaDTO) {
        if (reserva == null) return null;
        return new ReservaDTO(
                reserva.getId(),
                reserva.getFechaHora(),
                reserva.getMotivo(),
                reserva.getMascotaId(),
                mascotaDTO
        );
    }
}