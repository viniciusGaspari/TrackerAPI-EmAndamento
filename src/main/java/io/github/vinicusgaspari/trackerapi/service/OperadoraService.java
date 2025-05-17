package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Operadora;
import io.github.vinicusgaspari.trackerapi.repository.OperadoraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperadoraService {

    private final OperadoraRepository repository;

    public Operadora buscarPorId(UUID id) {
        if (repository.existsById(id)) {
            return repository.findById(id).get();
        }
        throw new EntityNotFoundException("Entidade OPERADORA não encontrada");
    }

}
