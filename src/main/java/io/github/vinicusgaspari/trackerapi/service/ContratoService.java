package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Contrato;
import io.github.vinicusgaspari.trackerapi.repository.ContratoRepository;
import io.github.vinicusgaspari.trackerapi.validator.conta.ContaValidator;
import io.github.vinicusgaspari.trackerapi.validator.contrato.ContratoValidator;
import io.github.vinicusgaspari.trackerapi.validator.security.UsuarioAutenticado;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

import static io.github.vinicusgaspari.trackerapi.validator.contrato.ContratoSpecs.*;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final UsuarioAutenticado usernameContaAutenticado = new UsuarioAutenticado();
    private final ContratoRepository contratoRepository;
    private final ContratoValidator contratoValidator;
    private final ContaValidator contaValidator;

    public Contrato salvar(Contrato contrato) {
        contrato.setConta(contaValidator.obterContaPorUsername(usernameContaAutenticado.obterUsernameUsuarioAutenticado()));
        contrato.setNome(contratoValidator.validarNomeDuplicado(contrato.getNome()));
        return contratoRepository.save(contrato);
    }

    public Contrato buscarPorId(UUID id) {
        return contratoValidator.obterContratoPorId(id);
    }

    public void deletarPorId(UUID id) {
        contratoRepository.delete(contratoValidator.obterContratoPorId(id));
    }

    public Contrato atualizar(UUID id, Contrato contrato) {
        Contrato contratoEncontrado = contratoValidator.obterContratoPorId(id);
        contratoEncontrado.setNome(contratoValidator.validarContratoPorNomeAoAtualizar(id, contrato));
        contratoEncontrado.setPreco(contrato.getPreco());
        contratoEncontrado.setQuantidade(contrato.getQuantidade());
        return contratoRepository.save(contratoEncontrado);
    }

    public Page<Contrato> buscarPorFiltro(UUID id, String nome, BigDecimal preco, Integer quantidade, Integer pagina, Integer tamanhoPagina) {
        Specification<Contrato> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (id != null) {
            specs = specs.and(isIdEqual(id));
        }
        if (nome != null) {
            specs = specs.and(isNomeLike(nome));
        }
        if (preco != null) {
            specs = specs.and(isPrecoLike(preco));
        }
        if (quantidade != null) {
            specs = specs.and(isQuantidadeEqual(quantidade));
        }
        return contratoRepository.findAll(specs, PageRequest.of(pagina, tamanhoPagina));
    }


}
