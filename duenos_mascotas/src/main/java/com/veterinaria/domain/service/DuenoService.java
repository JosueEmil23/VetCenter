package com.veterinaria.domain.service;


import com.veterinaria.data.entity.Dueno;
import java.util.List;
import java.util.Optional;

public interface DuenoService {
    List<Dueno> listarTodos();
    Optional<Dueno> buscarPorId(Integer id);
    Dueno guardar(Dueno dueño);
    void eliminar(Integer id);
}