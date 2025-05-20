package io.github.vinicusgaspari.trackerapi.validator.conta;

import io.github.vinicusgaspari.trackerapi.model.Conta;
import io.github.vinicusgaspari.trackerapi.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContaValidator {

    private final ContaRepository contaRepository;

    public Conta obterContaPorNome(String nome) {
        return contaRepository.findByUsername(nome)
                .orElseThrow(() -> new EntityNotFoundException("CONTA"));
    }

}
