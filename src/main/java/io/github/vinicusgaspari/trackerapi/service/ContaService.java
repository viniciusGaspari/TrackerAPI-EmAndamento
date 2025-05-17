package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Conta;
import io.github.vinicusgaspari.trackerapi.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public Conta salvar(Conta conta) {
        conta.setPassword(encoder.encode(conta.getPassword()));
        conta.setRole(roleService.buscarPorNome(conta.getRole().getNome()));
        return contaRepository.save(conta);
    }

    public Conta buscarPorNome(String nome) {
        return contaRepository.findByUsername(nome)
                .orElseThrow(() -> new EntityNotFoundException("Entidade conta não encontrada"));
    }

}
