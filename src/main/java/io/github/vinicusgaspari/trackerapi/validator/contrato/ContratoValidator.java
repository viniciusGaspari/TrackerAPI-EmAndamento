package io.github.vinicusgaspari.trackerapi.validator.contrato;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.QuantidadeLimiteContratoException;
import io.github.vinicusgaspari.trackerapi.controller.mapper.RastreadorMapper;
import io.github.vinicusgaspari.trackerapi.model.Contrato;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.repository.ContratoRepository;
import io.github.vinicusgaspari.trackerapi.repository.RastreadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
            throw new QuantidadeLimiteContratoException(mapper.toListDTO(rastreadorRepository.findByUsuario(usuario)), quantidadeContrato);
        }
    }

    private Integer obterQuantidadeRastreadoresDoUsuario(Usuario usuario) {
        return rastreadorRepository.countByUsuario(usuario);
    }

    private Integer obterQuantidadePermitidaNoContrato(Contrato contrato) {
        return contratoRepository.findById(contrato.getId())
                .orElseThrow(() -> new EntityNotFoundException("CONTRATO"))
                .getQuantidade();
    }

}
