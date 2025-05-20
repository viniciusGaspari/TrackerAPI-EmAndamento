package io.github.vinicusgaspari.trackerapi.controller.exceptions;

import io.github.vinicusgaspari.trackerapi.controller.response.RastreadorResponse;
import lombok.Getter;

import java.util.List;

public class QuantidadeContratoException extends RuntimeException {

    @Getter
    String mensagem;
    @Getter
    List<RastreadorResponse> rastreadores;

    public QuantidadeContratoException(List<RastreadorResponse> rastreadores, Integer quantidadeContrato) {
        this.mensagem = "Quantidade de rastreador excedeu o limite do contrato. " +
                "Necessário excluir até a quantidade de contrato: " + quantidadeContrato;
        this.rastreadores = rastreadores;
    }
}
