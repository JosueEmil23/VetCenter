package com.veterinaria.api.controller;


import com.veterinaria.api.dto.MascotaCrearDTO;
import com.veterinaria.api.dto.MascotaDTO;
import com.veterinaria.data.entity.Dueno;
import com.veterinaria.data.entity.Mascota;
import com.veterinaria.domain.mapper.MascotaMapper;
import com.veterinaria.domain.service.DuenoService;
import com.veterinaria.domain.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private DuenoService dueñoService;

    @Autowired
    private MascotaMapper mascotaMapper;

    @GetMapping
    public List<MascotaDTO> listar() {
        return mascotaService.listarTodas().stream()
                .map(mascotaMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> obtenerPorId(@PathVariable Integer id) {
        return mascotaService.buscarPorId(id)
                .map(mascota -> ResponseEntity.ok(mascotaMapper.toDTO(mascota)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody MascotaCrearDTO dto) {
        var duenoOpt = dueñoService.buscarPorId(dto.duenoId());
        if (duenoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: El dueño con ID " + dto.duenoId() + " no existe.");
        }

        Dueno dueno = duenoOpt.get();

        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setNombre(dto.nombre());
        nuevaMascota.setEspecie(dto.especie());
        nuevaMascota.setRaza(dto.raza());
        nuevaMascota.setEdad(dto.edad());
        nuevaMascota.setDueno(dueno);

        Mascota guardada = mascotaService.guardar(nuevaMascota);

        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaMapper.toDTO(guardada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        mascotaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}