package com.veterinaria.historia.api.event;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ReservaCreadaEvent(
        Integer idReserva,
        Integer mascotaId,
        String nombreMascota,
        Integer duenoId,
        LocalDateTime fechaHora,
        String motivo
) implements Serializable {
}
