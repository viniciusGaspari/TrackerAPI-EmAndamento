package io.github.vinicusgaspari.trackerapi.controller.exceptions;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.rastreador.RastreadorResponse;
import lombok.Getter;

import java.util.List;

public class ContratoException extends RuntimeException {

    @Getter
    String mensagem;
    @Getter
    List<RastreadorResponse> rastreadores;

    public ContratoException(List<RastreadorResponse> rastreadores, Integer quantidadeContrato) {
        this.mensagem = "Quantidade de rastreador excedeu o limite do contrato. " +
                "Necessário excluir até a quantidade de contrato: " + quantidadeContrato;
        this.rastreadores = rastreadores;
    }
}
