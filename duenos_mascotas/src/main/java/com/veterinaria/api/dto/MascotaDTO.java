package com.veterinaria.api.dto;

public record MascotaDTO(
        Integer id,
        String nombre,
        String especie,
        String raza,
        Integer edad,
        Integer duenoId // Solo pasamos el ID del dueño, no el objeto completo
) {}