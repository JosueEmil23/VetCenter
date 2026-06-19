package com.veterinaria.domain.mapper;


import com.veterinaria.api.dto.MascotaDTO;
import com.veterinaria.data.entity.Mascota;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapper {


    public MascotaDTO toDTO(Mascota mascota) {
        if (mascota == null) return null;
        return new MascotaDTO(
                mascota.getId(),
                mascota.getNombre(),
                mascota.getEspecie(),
                mascota.getRaza(),
                mascota.getEdad(),
                mascota.getDueno() != null ? mascota.getDueno().getId() : null
        );
    }
}