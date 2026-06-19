package com.veterinaria.api.dto;

public record MascotaCrearDTO(
        String nombre,
        String especie,
        String raza,
        Integer edad,
        Integer duenoId
) {}