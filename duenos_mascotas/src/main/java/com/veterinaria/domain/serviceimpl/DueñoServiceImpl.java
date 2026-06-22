package com.veterinaria.domain.serviceimpl;


import com.veterinaria.data.entity.Dueno;
import com.veterinaria.data.repository.DuenoRepository;
import com.veterinaria.domain.service.DuenoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DueñoServiceImpl implements DuenoService {

    @Autowired
    private DuenoRepository dueñoRepository;

    @Override
    public List<Dueno> listarTodos() {
        return dueñoRepository.findAll();
    }

    @Override
    public Optional<Dueno> buscarPorId(Integer id) {
        return dueñoRepository.findById(id);
    }

    @Override
    public Dueno guardar(Dueno dueño) {
        return dueñoRepository.save(dueño);
    }

    @Override
    public void eliminar(Integer id) {
        dueñoRepository.deleteById(id);
    }
}