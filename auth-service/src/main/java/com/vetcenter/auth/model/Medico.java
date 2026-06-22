package com.vetcenter.auth.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Medicos")
@Getter
@Setter
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedico;

    private String nombre;
    private String telefono;
    private String email;
    private String especialidad;
    private String dni;

    @Column(unique = true)
    private String username;
    private String password;
}
