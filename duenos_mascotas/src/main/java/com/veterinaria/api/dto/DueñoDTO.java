package com.veterinaria.api.dto;

public record DueñoDTO(
        Integer id,
        String nombre,
        String apellido,
        String telefono,
        String correo
) {}