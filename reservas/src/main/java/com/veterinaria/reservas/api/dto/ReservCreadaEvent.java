package com.veterinaria.reservas.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ReservCreadaEvent(
        Integer idReserva,
        Integer mascotaId,
        String nombreMascota,
        Integer duenoId,       // <-- Cambiado para acoplarse a tu DTO real
        LocalDateTime fechaHora,
        String motivo
) implements Serializable {
}
