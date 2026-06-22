package com.vetcenter.auth.dto;

public record RegisterRequest(
        String nombre,
        String telefono,
        String email,
        String especialidad,
        String dni,
        String username,
        String password

) {
}
