package com.veterinaria.reservas.domain.service;

import com.veterinaria.reservas.api.dto.ReservaDTO;
import com.veterinaria.reservas.data.entity.Reserva;
import java.util.List;

public interface ReservaService {
    List<ReservaDTO> listarTodas();
    ReservaDTO guardar(Reserva reserva);
}