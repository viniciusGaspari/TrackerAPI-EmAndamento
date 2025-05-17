package io.github.vinicusgaspari.trackerapi.validator.rastreador;

import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import io.github.vinicusgaspari.trackerapi.repository.RastreadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RastreadorValidator {

    private final RastreadorRepository rastreadorRepository;

    public Rastreador validarRastreadorPorNome(String nome) {
        return rastreadorRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("RASTREADOR"));
    }

    public Rastreador validarRastreadorPorId(UUID id){
        return rastreadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RASTREADOR"));
    }

}
