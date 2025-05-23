package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Chip;
import io.github.vinicusgaspari.trackerapi.repository.ChipRepository;
import io.github.vinicusgaspari.trackerapi.validator.conta.ContaValidator;
import io.github.vinicusgaspari.trackerapi.validator.operadora.ChipValidator;
import io.github.vinicusgaspari.trackerapi.validator.security.UsuarioAutenticado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChipService {

    private final UsuarioAutenticado usernameContaAutenticado = new UsuarioAutenticado();
    private final ChipRepository chipRepository;
    private final ChipValidator chipValidator;
    private final ContaValidator contaValidator;

    public Chip salvar(Chip chip) {
        chip.setConta(contaValidator.obterContaPorUsername(usernameContaAutenticado.obterUsernameUsuarioAutenticado()));
        chip.setNome(chipValidator.validarChipPorNome(chip.getNome()));
        return chipRepository.save(chip);
    }

    public Chip buscarPorId(UUID id) {
        return chipValidator.obterChipPorId(id);
    }

    public void deletarPorId(UUID id) {
        chipRepository.delete(chipValidator.obterChipPorId(id));
    }

    public Chip atualizar(UUID id, Chip chip) {
        Chip chipEncontrado = chipValidator.obterChipPorId(id);
        chipEncontrado.setNome(chipValidator.validarChipNomeAoAtualizar(id, chip));
        chipEncontrado.setOperadora(chip.getOperadora());
        chipEncontrado.setLinha(chip.getLinha());
        return chipRepository.save(chipEncontrado);
    }

}
