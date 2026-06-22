package com.veterinaria.reservas.api.controller;

import com.veterinaria.reservas.api.dto.MascotaDTO;
import com.veterinaria.reservas.api.dto.ReservaDTO;
import com.veterinaria.reservas.data.entity.Reserva;
import com.veterinaria.reservas.domain.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<ReservaDTO> listar() {
        return reservaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Reserva reserva) {
        try {

            ReservaDTO nuevaReserva = reservaService.guardar(reserva);


            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}