package com.vetcenter.auth.controller;


import com.vetcenter.auth.dto.AuthResponse;
import com.vetcenter.auth.dto.LoginRequest;
import com.vetcenter.auth.dto.RegisterRequest;
import com.vetcenter.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody RegisterRequest request){
        String mensaje = service.registrar(request);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return service.login(request);
    }
}
