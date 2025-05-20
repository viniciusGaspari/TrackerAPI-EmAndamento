package io.github.vinicusgaspari.trackerapi.validator.rastreador;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.DadoDuplicadoException;
import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import io.github.vinicusgaspari.trackerapi.repository.RastreadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RastreadorValidator {

    private final RastreadorRepository rastreadorRepository;

    public Rastreador buscarRastreadorPorNome(String nome) {
        return rastreadorRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("RASTREADOR"));
    }

    public String validarRastreadorPorNome(String nome) {
        if (rastreadorRepository.existsByNome(nome)) {
            throw new DadoDuplicadoException(List.of("rastreador com nome duplicado"));
        }
        return nome;
    }

    public Rastreador buscarRastreadorPorId(UUID id) {
        return rastreadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RASTREADOR"));
    }

    public String validarNomeUsuarioExistente(String nome, UUID id) {
        Optional<Rastreador> rastreadorEncontrado = rastreadorRepository.findByNome(nome);
        if (rastreadorEncontrado.isPresent()) {
            if (!rastreadorEncontrado.get().getId().equals(id)) {
                throw new DadoDuplicadoException(List.of("rastreador com nome duplicado"));
            }
        }
        return nome;
    }

}
