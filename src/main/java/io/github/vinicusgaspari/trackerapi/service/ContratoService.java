package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Contrato;
import io.github.vinicusgaspari.trackerapi.repository.ContratoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final ContratoRepository repository;

    public Contrato buscarPorNome(String nome) {
        return repository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("CONTRATO"));
    }


}
