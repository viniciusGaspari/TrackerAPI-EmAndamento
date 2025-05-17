package io.github.vinicusgaspari.trackerapi.validator.operadora;

import io.github.vinicusgaspari.trackerapi.model.Operadora;
import io.github.vinicusgaspari.trackerapi.repository.OperadoraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OperadoraValidator {

    private final OperadoraRepository operadoraRepository;

    public Operadora validarOperadoraPorId(UUID id){
        return operadoraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OPERADORA"));
    }

}
