package com.hospital.hospitalapi.service.services;

import com.hospital.hospitalapi.domain.entities.Cirurgia;
import com.hospital.hospitalapi.repository.repositories.CirurgiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CirurgiaService {
    private final CirurgiaRepository repository;

    @Autowired
    public CirurgiaService(CirurgiaRepository repository) {
        this.repository = repository;
    }

    public Object ObterCirurgiaPorId(Long id){
        boolean existe = repository.existsById(id);
        if (!existe)
            throw new IllegalStateException("Cirurgia com o Id " + id + " não existe");

        return repository.findById(id);
    }

    public List<Cirurgia> ListarCirurgias(){
        return repository.findAll();
    }

    public Object CriarCirurgia(Cirurgia cirurgia) {
        Optional<Cirurgia> consultaPorId = repository.findById(cirurgia.getId());
        if (consultaPorId.isPresent())
            throw new IllegalStateException("Cirurgia com id " + consultaPorId.get().getId() + "já existe na base de dados.");

        return repository.save(cirurgia);
    }

    public void RemoverCirurgia(Long id) {
        boolean existe = repository.existsById(id);
        if (!existe)
            throw new IllegalStateException("Cirurgia com o Id" + id + " não existe.");

        repository.deleteById(id);
    }
}
