package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Conta;
import io.github.vinicusgaspari.trackerapi.repository.ContaRepository;
import io.github.vinicusgaspari.trackerapi.validator.conta.ContaValidator;
import io.github.vinicusgaspari.trackerapi.validator.role.RoleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.github.vinicusgaspari.trackerapi.validator.conta.ContaSpecs.*;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final RoleValidator roleValidator;
    private final ContaValidator contaValidator;
    private final PasswordEncoder encoder;

    public Conta salvar(Conta conta) {
        conta.setUsername(contaValidator.validarContaPorUsername(conta.getUsername()));
        conta.setPassword(encoder.encode(conta.getPassword()));
        conta.setRole(roleValidator.obterRolePorNome(conta.getRole().getNome()));
        return contaRepository.save(conta);
    }

    public Conta buscarPorId(UUID id) {
        return contaValidator.buscarContaPorId(id);
    }

    public void deletarPorId(UUID id){
        contaRepository.delete(contaValidator.buscarContaPorId(id));
    }

    public Conta atualizar(UUID id, Conta conta){
        Conta contaEncontrada = contaValidator.buscarContaPorId(id);
        contaEncontrada.setUsername(contaValidator.validarContaPorUsernameAoAtualizar(id, conta));
        contaEncontrada.setPassword(encoder.encode(conta.getPassword()));
        contaEncontrada.setRole(roleValidator.obterRolePorNome(conta.getRole().getNome()));
        return contaEncontrada;
    }

    public Page<Conta> buscarPorFiltro(UUID id, String username, UUID role, Integer pagina, Integer tamanhoPagina){

        Specification<Conta> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(id != null){
            specs = specs.and(isIdEqual(id));
        }
        if(username != null){
            specs = specs.and(isUsernameLike(username));
        }
        if(role != null){
            specs = specs.and(isRoleEqual(role));
        }

        return contaRepository.findAll(specs, PageRequest.of(pagina, tamanhoPagina));

    }


}
