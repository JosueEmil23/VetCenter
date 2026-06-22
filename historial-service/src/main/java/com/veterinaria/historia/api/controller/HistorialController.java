package com.veterinaria.historia.api.controller;


import com.veterinaria.historia.data.entity.Historial;
import com.veterinaria.historia.data.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historiales")
public class HistorialController {

    @Autowired
    private HistorialRepository historialRepository;

    @GetMapping
    public ResponseEntity<List<Historial>> listarTodos() {
        List<Historial> lista = historialRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<Historial>> obtenerPorMascotaId(@PathVariable Integer mascotaId) {

        List<Historial> historial = historialRepository.findAll().stream()
                .filter(h -> h.getMascotaId().equals(mascotaId))
                .toList();
        return ResponseEntity.ok(historial);
    }

    @PutMapping("/{id}/atender")
    public ResponseEntity<?> atenderConsulta(
            @PathVariable Integer id,
            @RequestBody Historial datosMedicos) {

        Optional<Historial> historialOptional = historialRepository.findById(id);

        if (historialOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Historial historialExistente = historialOptional.get();


        historialExistente.setDiagnostico(datosMedicos.getDiagnostico());
        historialExistente.setTratamiento(datosMedicos.getTratamiento());

        Historial historialActualizado = historialRepository.save(historialExistente);
        return ResponseEntity.ok(historialActualizado);
    }
}
