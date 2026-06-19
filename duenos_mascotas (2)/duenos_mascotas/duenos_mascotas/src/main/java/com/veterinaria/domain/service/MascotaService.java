package com.veterinaria.domain.service;


import com.veterinaria.data.entity.Mascota;
import java.util.List;
import java.util.Optional;

public interface MascotaService {
    List<Mascota> listarTodas();
    Optional<Mascota> buscarPorId(Integer id);
    Mascota guardar(Mascota mascota);
    void eliminar(Integer id);
}