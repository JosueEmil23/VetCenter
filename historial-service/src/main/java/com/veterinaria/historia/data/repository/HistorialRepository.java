package com.veterinaria.historia.data.repository;

import com.veterinaria.historia.data.entity.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialRepository extends JpaRepository<Historial, Integer> {
}
