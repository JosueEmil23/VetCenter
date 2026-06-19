package com.veterinaria.domain.serviceimpl;

import com.veterinaria.data.entity.Mascota;
import com.veterinaria.data.repository.MascotaRepository;
import com.veterinaria.domain.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;
    @Override
    public List<Mascota> listarTodas() {
        return mascotaRepository.findAll();
    }
    @Override
    public Optional<Mascota> buscarPorId(Integer id) {
        return mascotaRepository.findById(id);
    }
    @Override
    public Mascota guardar(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }
    @Override
    public void eliminar(Integer id) {
        mascotaRepository.deleteById(id);
    }
}

