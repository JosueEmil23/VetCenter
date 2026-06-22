package com.veterinaria.historia.data.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "historial-medico")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "reserva_id", nullable = false, unique = true)
    private Integer reservaId;

    @Column(name = "mascota_id", nullable = false)
    private Integer mascotaId;

    @Column(nullable = false)
    private String motivo;

    @Column(name = "fecha_atencion", nullable = false)
    private LocalDateTime fechaAtencion;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(columnDefinition = "TEXT")
    private String tratamiento;


}
