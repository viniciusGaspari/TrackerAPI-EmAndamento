package io.github.vinicusgaspari.trackerapi.validator.operadora;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.AcessoNegadoException;
import io.github.vinicusgaspari.trackerapi.controller.exceptions.DadoDuplicadoException;
import io.github.vinicusgaspari.trackerapi.model.Chip;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.repository.ChipRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChipValidator {

    private final ChipRepository chipRepository;

    public Chip buscarChipPorId(UUID id) {
        return chipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID DO CHIP"));
    }

    public Chip obterChipPorNome(String nome) {
        return chipRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("NOME DO CHIP"));
    }

    public String validarChipPorNome(String nome) {
        if (chipRepository.existsByNome(nome)) {
            throw new DadoDuplicadoException(List.of("NOME DO CHIP"));
        }
        return nome;
    }

    public String validarChipNomeAoAtualizar(UUID id, Chip chip) {
        Optional<Chip> chipEncontrado = chipRepository.findByNome(chip.getNome());
        if (chipEncontrado.isPresent()) {
            if (!chipEncontrado.get().getId().equals(id)) {
                throw new DadoDuplicadoException(List.of("NOME DO CHIP"));
            }
        }
        return chip.getNome();
    }

    public Chip validarChipPorUsuario(Usuario usuario, UUID idChip) {
        Chip chipEncontrado = buscarChipPorId(idChip);
        if (usuario.getConta().getUsername().equals(chipEncontrado.getConta().getUsername())) {
            return chipEncontrado;
        }
        throw new AcessoNegadoException("CHIP");
    }

}
