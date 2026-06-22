package com.veterinaria.reservas.api.dto;

public record MascotaDTO(
        Integer id,
        String nombre,
        String especie,
        String raza,
        Integer edad,
        Integer duenoId
) {}