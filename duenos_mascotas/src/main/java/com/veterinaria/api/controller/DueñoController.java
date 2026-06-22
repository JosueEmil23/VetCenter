package com.veterinaria.api.controller;


import com.veterinaria.api.dto.DueñoDTO;
import com.veterinaria.data.entity.Dueno;
import com.veterinaria.domain.mapper.DueñoMapper;
import com.veterinaria.domain.service.DuenoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duenos")
public class DueñoController {

    @Autowired
    private DuenoService dueñoService;

    @Autowired
    private DueñoMapper dueñoMapper;

    @GetMapping
    public List<DueñoDTO> listar() {
        return dueñoService.listarTodos().stream()
                .map(dueñoMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DueñoDTO> obtenerPorId(@PathVariable Integer id) {
        return dueñoService.buscarPorId(id)
                .map(dueño -> ResponseEntity.ok(dueñoMapper.toDTO(dueño)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DueñoDTO> crear(@RequestBody Dueno dueño) {
        Dueno nuevo = dueñoService.guardar(dueño);
        return ResponseEntity.status(HttpStatus.CREATED).body(dueñoMapper.toDTO(nuevo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        dueñoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}