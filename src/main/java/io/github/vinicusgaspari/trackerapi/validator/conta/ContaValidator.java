package io.github.vinicusgaspari.trackerapi.validator.conta;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.DadoDuplicadoException;
import io.github.vinicusgaspari.trackerapi.model.Conta;
import io.github.vinicusgaspari.trackerapi.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContaValidator {

    private final ContaRepository contaRepository;

    public Conta obterContaPorUsername(String username) {
        return contaRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("USERNAME DA CONTA"));
    }

    public String validarContaPorUsername(String username) {
        Optional<Conta> contaEncontrada = contaRepository.findByUsername(username);
        if (contaEncontrada.isPresent()) {
            throw new DadoDuplicadoException(List.of("USERNAME DO CLIENT"));
        }
        return username;
    }

    public Conta buscarContaPorId(UUID id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID DA CONTA"));
    }

    public String validarContaPorUsernameAoAtualizar(UUID id, Conta conta) {
        Optional<Conta> contaEncontrada = contaRepository.findByUsername(conta.getUsername());
        if (contaEncontrada.isPresent()) {
            if (!contaEncontrada.get().getId().equals(conta.getId())) {
                throw new DadoDuplicadoException(List.of("USERNAME DA CONTA"));
            }
        }
        return conta.getUsername();
    }

}
