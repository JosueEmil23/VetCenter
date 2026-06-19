package com.veterinaria.reservas.api.client;

import com.veterinaria.reservas.api.dto.MascotaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "duenos-mascotas", url = "http://localhost:8081/api/mascotas")
public interface MascotaFeignClient {

    @GetMapping("/{id}")
    MascotaDTO obtenerMascotaPorId(@PathVariable("id") Integer id);
}