package com.veterinaria.reservas.api.client;

import com.veterinaria.reservas.api.dto.MascotaDTO;
import com.veterinaria.reservas.config.FeignClientConfitg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "duenos-mascotas",
configuration = FeignClientConfitg.class)
public interface MascotaFeignClient {

    @GetMapping("/api/mascotas/{id}")
    MascotaDTO obtenerMascotaPorId(@PathVariable("id") Integer id);
}