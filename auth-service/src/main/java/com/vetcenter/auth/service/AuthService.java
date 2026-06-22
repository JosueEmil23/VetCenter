package com.vetcenter.auth.service;


import com.vetcenter.auth.dto.AuthResponse;
import com.vetcenter.auth.dto.LoginRequest;
import com.vetcenter.auth.dto.RegisterRequest;
import com.vetcenter.auth.model.Medico;
import com.vetcenter.auth.repository.MedicoRepository;
import com.vetcenter.auth.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthService {


    private final MedicoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(MedicoRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public String registrar(RegisterRequest request){
        Medico medico = new Medico();
        medico.setNombre(request.nombre());
        medico.setTelefono(request.telefono());
        medico.setEmail(request.email());
        medico.setEspecialidad(request.especialidad());
        medico.setDni(request.dni());
        medico.setUsername(request.username());
        medico.setPassword(passwordEncoder.encode(request.password()));

        repository.save(medico);

        return "Medico registrado con exito";
    }

    public AuthResponse login(LoginRequest request){
        Medico medico = repository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean passwordValido = passwordEncoder.matches(
                request.password(),
                        medico.getPassword()
        );
        if (!passwordValido){
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token = jwtService.generarToken(medico.getUsername());

        return new AuthResponse(token);
    }
}
