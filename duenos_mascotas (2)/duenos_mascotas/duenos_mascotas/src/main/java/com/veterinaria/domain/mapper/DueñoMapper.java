package com.veterinaria.domain.mapper;


import com.veterinaria.api.dto.DueñoDTO;
import com.veterinaria.data.entity.Dueno;
import org.springframework.stereotype.Component;

@Component
public class DueñoMapper {

    public DueñoDTO toDTO(Dueno dueño) {
        if (dueño == null) return null;
        return new DueñoDTO(
                dueño.getId(),
                dueño.getNombre(),
                dueño.getApellido(),
                dueño.getTelefono(),
                dueño.getCorreo()
        );
    }

    public Dueno toEntity(DueñoDTO dto) {
        if (dto == null) return null;
        Dueno dueño = new Dueno();
        dueño.setId(dto.id());
        dueño.setNombre(dto.nombre());
        dueño.setApellido(dto.apellido());
        dueño.setTelefono(dto.telefono());
        dueño.setCorreo(dto.correo());
        return dueño;
    }
}