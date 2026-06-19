package com.veterinaria.reservas.api.dto;

import java.time.LocalDateTime;

public record ReservaDTO(
        Integer id,
        LocalDateTime fechaHora,
        String motivo,
        Integer mascotaId,
        MascotaDTO deLaMascota
) {}