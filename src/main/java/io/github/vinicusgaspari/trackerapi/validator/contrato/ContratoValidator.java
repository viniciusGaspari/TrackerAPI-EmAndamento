package io.github.vinicusgaspari.trackerapi.validator.contrato;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.AcessoNegadoException;
import io.github.vinicusgaspari.trackerapi.controller.exceptions.DadoDuplicadoException;
import io.github.vinicusgaspari.trackerapi.controller.exceptions.ContratoException;
import io.github.vinicusgaspari.trackerapi.controller.mapper.RastreadorMapper;
import io.github.vinicusgaspari.trackerapi.model.Contrato;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.repository.ContratoRepository;
import io.github.vinicusgaspari.trackerapi.repository.RastreadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContratoValidator {

    private final RastreadorRepository rastreadorRepository;
    private final ContratoRepository contratoRepository;
    private final RastreadorMapper mapper;

    public void verificarQuantidadeRastreadorContrato(Usuario usuario, Contrato contrato) {
        Integer quantidadeContrato = obterQuantidadePermitidaNoContrato(contrato);
        Integer quantidadeUsuario = obterQuantidadeRastreadoresDoUsuario(usuario);
        if (quantidadeUsuario >= quantidadeContrato) {
            throw new ContratoException(mapper.toListDTO(rastreadorRepository.findByUsuario(usuario)), quantidadeContrato);
        }
    }

    private Integer obterQuantidadeRastreadoresDoUsuario(Usuario usuario) {
        return rastreadorRepository.countByUsuario(usuario);
    }

    private Integer obterQuantidadePermitidaNoContrato(Contrato contrato) {
        return contratoRepository.findById(contrato.getId())
                .orElseThrow(() -> new EntityNotFoundException("ID DO CONTRATO"))
                .getQuantidade();
    }

    public Contrato obterContratoPorNome(String nome) {
        return contratoRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("NOME DO CONTRATO"));
    }

    public Contrato validarContrato(Usuario usuario, String nome) {
        Contrato contrato = obterContratoPorNome(nome);
        if (contrato.getConta().getUsername().equals(usuario.getConta().getUsername())) {
            return contrato;
        }
        throw new AcessoNegadoException("CONTRATO");
    }

    public Contrato obterContratoPorId(UUID id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID DO CONTRATO"));
    }

    public String validarNomeDuplicado(String nome) {
        Optional<Contrato> contrato = contratoRepository.findByNome(nome);
        if (contrato.isEmpty()) {
            return nome;
        }
        throw new DadoDuplicadoException(List.of("NOME DO CONTRATO"));
    }

    public String validarContratoPorNomeAoAtualizar(UUID id, Contrato contrato) {

        Optional<Contrato> contratoEncontrado = contratoRepository.findByNome(contrato.getNome());

        if (contratoEncontrado.isPresent()) {
            if (!contratoEncontrado.get().getId().equals(id)) {
                throw new DadoDuplicadoException(List.of("NOME DO CONTRATO"));
            }
        }
        return contrato.getNome();
    }

}
