package com.vetcenter.auth.repository;

import com.vetcenter.auth.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Optional<Medico>   findByUsername(String username);
}
